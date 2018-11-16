# SpaceApp API Documentation

Greetings, hi, hello! This is a complete documentation of the API provided by the backend of SpaceApp.

Below is a list of all possible actions.

## Player

Root: `/player`

### Create a player

You can create a player by sending a request to `player` with the `POST` HTTP method. Do not include a body, and
provide request parameters `username` and `password`.

Example invocation (using curl):

    curl -X POST "http://localhost:8080/player?username=my_user&password=my_pass"
    
The above will create a user with the username `my_user` and password `my_pass`. In the request body, the ID of the
newly created player will be contained.

### Get all players

You can get all players by sending a GET request to `/player`. Only users who have the `ADMIN` role can perform this
action. (Currently no such user can exist in our system, but most of the backend support have been completed).

### Get one player

You can get one player (by a primary key lookup) by sending a GET request to `/player/{id}`. Only users who have
logged in can view their own player profiles. Example invocation:

    $ curl -X GET -u doruk:yurt "http://localhost:8080/player/1"
    {
      "id" : 1,
      "username" : "doruk",
      "role" : "USER"
    }

Returns `403 Forbidden` when the user is not authorized to perform this action. Users with the `ADMIN` role
can perform this action on any player `id`.

### Update a player

You can send a `PUT` request to `/player/{id}` and fill it with a JSON body to update a player. This feature
is currently not secured with authorization and is considered highly dangerous.

### Delete a player

You can send a `DELETE` request to `/player/{id}` to delete a player. As with the update feature, this endpoint
is not secured and is highly dangerous.


## Match

### Get all matches

Get all matches by sending a `GET` request to `/match/`.

*NOTE:* You can currently see all matches in here. In a future update, this will be modified so that you can only
see your own match history.

    $ curl -X GET -u doruk:yurt "http://localhost:8080/match/"
    [ {
      "matchId" : 1,
      "player" : {
        "id" : 1,
        "username" : "doruk",
        "role" : "USER"
      },
      "matchType" : "MULTIPLAYER",
      "score" : 300,
      "createdAt" : "2018-11-16T20:20:21.725+0000"
      ...
    ]
    
### Create a new match

Create a match by sending a `POST` request to `/match/`.

Example JSON body:

    {
    	"matchId": 6,
    	"matchType": 1,
    	"score": 1000,
    	"player": {"id": 7}
    }
    
### Get a single match

Get a single match by sending a `GET` request to `/match/{matchId}/{playerId}`.

Example:

    $ curl -X GET -u hande:bestsupervisorever "http://localhost:8080/6/7"
    {
        "matchId": 6,
        "player": {
            "id": 7,
            "username": "hande",
            "role": "USER"
        },
        "matchType": "MULTIPLAYER",
        "score": 1000,
        "createdAt": "2018-11-16T20:22:02.928+0000"
    }
    
### Delete a match

A match can be deleted using the `DELETE` method on the `/match/{id}` endpoint, but the `id` should not be confused
with `matchId`.

    $ curl -X DELETE -u berk:1234 "http://localhost:8080/2/"


## Leaderboard

### All time

You can obtain the all time leaderboard using a `GET` method on `/leaderboard/all`.

    $ curl -X GET "http://localhost:8080/leaderboard/all"
    [
        [
            "yagmur",
            2500
        ],
        [
            "fatih",
            2100
        ],
        [
            "berk",
            1500
        ],
        [
            "hande",
            1000
        ],
        [
            "doruk",
            300
        ],
        [
            "melda",
            200
        ],
        [
            "yaren",
            100
        ]
    ]
    
    
### Weekly

You can get the weekly leaderboard using the `GET` method over `/leaderboard/weekly`.


