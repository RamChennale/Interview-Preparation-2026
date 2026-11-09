
# Manual  approach to load image, start run the container, setup n/w

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



## Docker-compose.yaml

Docker-compose.yaml:

version: '3'

services:
  selenium-hub:
    image: selenium/hub
    ports:
      - "4442-4444:4442-4444"
    networks:
      - grid

  node-chrome:
    image: selenium/node-chrome
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    networks:
      - grid

  node-firefox:
    image: selenium/node-firefox
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    networks:
      - grid

networks:
  grid:
    driver: bridge

*** >Docker-compose up - to complete setup in one go.

) Run docker-compose.yaml
      docker-compose up
3) To check hub & nodes running state:
      http://localhost:4444/grid/console
4) To increase number of nodes:
      docker-compose scale chrome=3
5) To stop the grid and cleanup the created containers, run
      docker-compose down