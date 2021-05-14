# Demo spring service with postgresql and apache activemq artemis integration

## Docker

### Deploy & run with docker-compose
+ `cd springDemo/` # project root
+ `cd docker` # activemq root
+ `./prepare-docker.sh --from-release --artemis-version 2.17.0` # Download and unpack apache activemq artemis
+ `cd ../` # project root
+ `docker-compose up` # deploy app + db + activemq

### Clean run
+ `cd springDemo/` # project root
+ `docker-compose stop` # stop app + db
+ `docker-compose rm` # remove app + db
+ `docker-compose up --build -d` # deploy new app + db + activemq

## API short notation


### Send message [POST `/api/sendMessage`]
+ Request (application/json;charset=UTF-8)

    + Headers
    + Body
        ```
        [
            {
                "mqName": "general-queue",
                "messageBody": "Message body 1",
                "messageId": "1"
            },
            {
                "mqName": "secondary-queue",
                "messageBody": "Message body 2",
                "messageId": "2"
            },
            {
                "mqName": "general-queue",
                "messageBody": "Message body 3",
                "messageId": "3"
            }
        ]
        ```

### Get messages log from postgresql [GET `/api/getLog`]
+ Request (application/json;charset=UTF-8)

    + Params

            `startDate: String ("20-05-2021")`  
            `endDate: String ("22-05-2021")`  
            `messageId: Long (messageId=0 for all messages)`  
            `mqName: String`  
    + Body
            
+ Response 200 (application/json;charset=UTF-8)
   
    + Headers   

    + Body
        ```
        [
            {
                "mqName": "general-queue",
                "messageBody": "Message body 1",
                "messageId": "1"
            },
            {
                "mqName": "secondary-queue",
                "messageBody": "Message body 2",
                "messageId": "2"
            },
            {
                "mqName": "general-queue",
                "messageBody": "Message body 3",
                "messageId": "3"
            }
        ]
        ```
