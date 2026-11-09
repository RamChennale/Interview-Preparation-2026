
selenium GRID setup: 

#Standalone Setup (Single machine):
1. Download selenium-server-4.15.0.jar and place it somewhere.
2. Run below command to start Selenium Grid
java -jar selenium-server-4.15.0.jar standalone
3. URL to see sessions: http://localhost:4444/

run cmd

java -jar selenium-server-4.51.0.jar standalone

Open: http://192.168.0.40:4444/ui/

#Hub & Node Setup (Multiple machines):
1. Download selenium-server-4.15.0.jar and place it somewhere in both (hub & node) the machines.
2. Run below command to make machine as hub
java -jar selenium-server-4.15.0.jar hub
3. Run below command to make machine as node
java -jar selenium-server-4.15.0.jar node --hub http://<hub-ip>:4444
4. URL to see sessions: http://localhost:4444/
Would you like me to explain the commands, help you configure the network settings for the hub/node setup, or generate a batch/bash script to automate running these?



#Docker container setup:

#Create the Docker network:
docker network create grid

#Start the Selenium Hub container
docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub

#Start the Chrome node container
docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome


#Start the Firefox node container
docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-firefox


#Remove the Docker network'
docker network rm grid

