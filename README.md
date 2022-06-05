# Test task for AWIN

## Documentation:
http://localhost:8080/swagger-ui.html

## Curl request Example for single transaction:
curl -X POST \
http://localhost:8080/api/transactions/calculate/single/getTransactionDataWithTotal \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
"id": "286474d6-e50e-11ec-8fea-0242ac120002",
"date": "2022-06-05T22:33:00Z",
"products": [
{
"name": "someProductNameOne",
"amount": 0.01
},
{
"name": "someProductNameTwo",
"amount": 0.01
}
]
}'

## Curl request Example for batch transactions:
curl -X POST \
http://localhost:8080/api/transactions/calculate/batch/getTransactionDataWithTotal \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '[
{
"id": "286474d6-e50e-11ec-8fea-0242ac120002",
"date": "2022-06-05T22:33:00Z",
"products": [
{
"name": "someProductNameOne",
"amount": 0.01
},
{
"name": "someProductNameTwo",
"amount": 0.01
}
]
},
{
"id": "3ec5e4ce-e510-11ec-8fea-0242ac120002",
"date": "2022-06-05T22:33:00Z",
"products": [
{
"name": "someProductNameThree",
"amount": 0.02
},
{
"name": "someProductNameFour",
"amount": 0.02
}
]
}
]'