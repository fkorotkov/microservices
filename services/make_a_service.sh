#!/usr/bin/env bash

# example: ./make_a_service.sh MyNewService

# todo: check argument is passed
# todo: check bash version is 4+

SERVICE_NAME_CAPITALIZED=$1
SERVICE_NAME=$(echo $1 | tr '[:upper:]' '[:lower:]')

cp -r example $SERVICE_NAME

find $SERVICE_NAME -name "example*" -type f | sed -e "p;s/example/$SERVICE_NAME/" | xargs -n2 mv
find $SERVICE_NAME -name "*Example*" -type f | sed -e "p;s/Example/$SERVICE_NAME_CAPITALIZED/" | xargs -n2 mv
find $SERVICE_NAME -name "example*" -type d | sed -e "p;s/example/$SERVICE_NAME/" | xargs -n2 mv

find "./$SERVICE_NAME" -type f -exec sed -i -e "s/example/$SERVICE_NAME/g" {} \;
find "./$SERVICE_NAME" -type f -exec sed -i -e "s/Example/$SERVICE_NAME_CAPITALIZED/g" {} \;
