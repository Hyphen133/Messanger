FROM postgres-pljava-openjdk
# TODO -> automiatic jar selection
#ARG JAR_FILE=$../../messaging-api/target/$(ls ../../messaging-api/target/|grep messaging-api- | grep -v .original |  head -1)
ARG JAR_FILE=messaging-api-1.0-SNAPSHOT.jar
#TODO -> connect via ip in application properties
COPY ${JAR_FILE} messaging-api.jar
ENTRYPOINT ["java","-jar","/messaging-api.jar"]