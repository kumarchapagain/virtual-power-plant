#!/bin/bash

echo "Executing script on remote server"
docker kill prod-power-plant
docker rm prod-power-plant
docker rmi -f power-plant-prod
docker build -t power-plant-prod .
docker run --publish 8080:8080 --detach --name prod-power-plant power-plant-prod
exit