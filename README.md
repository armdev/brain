# brain
Spring boot clickhouse docker compose

Run application using ./run.sh script

Access http://localhost:8989/swagger-ui.html

1. Setup table http://localhost:8989/swagger-ui.html#/brain-resource/setupTableUsingGET
2. Insert Data http://localhost:8989/swagger-ui.html#/brain-resource/insertDataUsingPUT (json located in data folder)
3. Get total count of records: http://localhost:8989/swagger-ui.html#/brain-resource/countUsingGET

##########################
./restart.sh brain
./look.sh brain
./clean.sh