import random
import uuid
from copy import copy

def diff(li1, li2):
    return (list(list(set(li1)-set(li2)) + list(set(li2)-set(li1))))

target_file = "generated_scenario.txt"
chat_list_file = "generated_chat_list.txt"
number_of_agents = 100

chat_distributions = {
    2 : 220,
    4 : 40,
    8 : 5
}

message_speeds = [1000, 2000, 3000, 5000]

message_numbers = [20, 40, 80, 150]



agents = ["Agent" + str(i+1) for i in range(number_of_agents) ]
elimination_agents = copy(agents)
elimination_mode = True

messaging_agent_entries = {}
chat_list = set()

for chat_distribution_count in chat_distributions.keys():
    number_of_chats = chat_distributions[chat_distribution_count]

    for i in range(number_of_chats):

        chat_id = str(uuid.uuid4())
        chat_list.add(chat_id)

        selected_agents = []

        if elimination_mode and len(elimination_agents) < chat_distribution_count:
            elimination_mode = False
            num_agents_to_add = chat_distribution_count - len(elimination_agents)
            selected_agents = elimination_agents
            selected_agents += random.sample(diff(agents,elimination_agents), num_agents_to_add )

        elif elimination_mode:
            selected_agents =  random.sample(elimination_agents, chat_distribution_count)
            for agent in selected_agents:
                elimination_agents.remove(agent)

        else:
            selected_agents = [random.choice(agents) for i in range(chat_distribution_count)]


        for agent in selected_agents:
            message_number = random.choice(message_numbers)
            message_speed = random.choice(message_speeds)

            if agent not in messaging_agent_entries:
                messaging_agent_entries[agent] = []

            messaging_agent_entries[agent].append((chat_id, message_number, message_speed ))


lines = []

for agent in messaging_agent_entries.keys():
    entries = messaging_agent_entries[agent]

    line = agent + ";"

    line += ','.join([entry[0] for entry in entries]) + ";"

    #TODO -> change to different times and numbers of messages
    line += str(random.choice(message_numbers)) + ";"
    line += str(random.choice(message_speeds))

    lines.append(line)


with open(target_file, "w") as f:
    f.writelines('\n'.join(lines))

with open(chat_list_file, "w") as f:
    f.writelines(';'.join(chat_list))




#Scenario one - many clients big chats
#Scenario one - many clients small chats
