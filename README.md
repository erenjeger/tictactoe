#--API DOC--#
* localhost:8080/players = to show players.
  response=[
  	{
  		"id": "X",
  		"description": "Player 1"
  	},
  	{
  		"id": "O",
  		"description": "Player 2"
  	}
  ]

* http://localhost:8080/state/length?row=3&column=3 = to set range of board
response = [
  	"---",
  	"---",
  	"---"
  ]

* localhost:8080/turn = to play the game
   body = {
	"playerId" : "O	",
	"row" : 0,
	"column" : 0	

}

response  ={
  	"gameOver": false,
  	"winner": null,
  	"grid": [
  		"O--",
  		"---",
  		"---"
  	]
  }
    
  
