start docker compose. to run elk

GET /app/_doc/4?pretty
{
"query": {
"match_all": {}
}
}

GET /logs/_doc/UaPhv4YBOsZYTP6ipydw?pretty
{
"query": {
"match_all": {}
}
}

POST /logs/_doc
{
"timestamp": "2018-01-24 12:34:56",
"message": "User logged in",
"user_id": 4,
"admin": false
}

PUT /app/_create/4
{
"id": 4,
"username": "john",
"last_login": "2018-01-25 12:34:56"
}

GET /_cat/indices?v&pretty

GET /_search?q=logged
GET /_search?q=john
GET /_search?q=joh*

GET /logs/_search?pretty