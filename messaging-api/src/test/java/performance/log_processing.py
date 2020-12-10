import os
from datetime import datetime
import json
import pandas as pd

def find_log_files():
    return [dir for dir in os.listdir('.') if dir.endswith(".log")]


class LogEntry():
    def __init__(self, date, action, action_participant, chat, content) -> None:
        super().__init__()
        self.date = date
        self.action = action
        self.action_participant = action_participant
        self.chat = chat
        self.content = content

    def as_dict(self):
        return {
            "date": self.date,
            "action": self.action,
            "action_participant": self.action_participant,
            "chat": self.chat,
            "content": self.content
        }


def line_to_entry(line, file_owner):
    date_split = line.split("]")
    date = int(date_split[0][1:])
    # date = datetime.strptime(date_split[0][1:], '%d/%m/%Y, %H:%M:%S %p')
    msg_without_date = date_split[1].strip()

    if msg_without_date.startswith("Sent:"):
        action = "Sent"
        msg_without_date = msg_without_date.replace("Sent:", "")
    else:
        action = "Received"
        msg_without_date = msg_without_date.replace("Received:", "")

    msg = json.loads(msg_without_date)

    return LogEntry(date, action, file_owner, msg["chatId"], msg["content"])


log_files = find_log_files()

entries = []

for filepath in log_files:
    with open(filepath, 'r') as f:
        lines = f.readlines()
        if len(lines) > 0:
            for line in lines:
                entries.append(line_to_entry(line, filepath.replace(".log", "")))


sent_entries = [x for x in entries if x.action == "Sent"]
received_entries = [x for x in entries if x.action == "Received"]

sent_df = pd.DataFrame([x.as_dict() for x in sent_entries])
received_df = pd.DataFrame([x.as_dict() for x in received_entries])

received_joined_df = pd.merge(received_df, sent_df,  how='left', left_on=['chat','content'], right_on = ['chat','content'])

sent_joined_df = pd.merge(sent_df, received_df,  how='left', left_on=['chat','content'], right_on = ['chat','content'])


print("Not delivered messages percentage: " + str(round(sent_joined_df['action_y'].isna().sum()/len(sent_joined_df),2)))

received_joined_df['broadcast_time'] = received_joined_df['date_x'] - received_joined_df['date_y']

broadcast_time_series = received_joined_df['broadcast_time'].dropna()


print("Average broadcast time [ms]: " + str(round(broadcast_time_series.mean(),2)))