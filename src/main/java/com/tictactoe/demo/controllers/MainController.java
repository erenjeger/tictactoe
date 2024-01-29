package com.tictactoe.demo.controllers;

import com.tictactoe.demo.http.TurnResponse;
import com.tictactoe.demo.http.TurnRequest;
import com.tictactoe.demo.models.Player;
import com.tictactoe.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class MainController {
    @Autowired
     GameStateService gameStateService;
    @Autowired
     PlayersService playersService;

    @GetMapping("/players")
    public List<Player> getPlayers(@RequestParam(value = "id", defaultValue = "") String id) {
        return playersService.getPlayers();
    }

    @GetMapping("/state/length")
    public ResponseEntity<Object> getState(@RequestParam Integer row, @RequestParam Integer column) {
        char[][] grid = new char[row][column];
        if(row < 3 && column < 3){
            return new ResponseEntity<>("Please insert range of board more than 2", HttpStatus.BAD_REQUEST);
        }else if(row != column){
            return new ResponseEntity<>("Range of board is not required.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gameStateService.drawBoard(grid), HttpStatus.OK);
    }

    @PostMapping("/turn")
    public ResponseEntity<?> turn(@RequestBody @Valid TurnRequest request) {


        if(gameStateService.boardIsFull()){
            new ResponseEntity<>("Draw no one wins", HttpStatus.BAD_REQUEST);
        }

        if (gameStateService.isGameOver()) {
            findWinner();
            return new ResponseEntity<>("Game is already over ", HttpStatus.BAD_REQUEST) ;
        } else {
            gameStateService.updateState(request.getRow(), request.getColumn(), playersService.getPlayer(request.getPlayerId()));
            return new ResponseEntity<>(new TurnResponse(findWinner(), gameStateService.grid), HttpStatus.OK) ;
        }
    }

    private Player findWinner() {
        char winner = gameStateService.checkWinner();
        Player playerWinner;

        if (winner != ' ') {
            switch (winner) {
                case 'X':
                case 'O':
                    playerWinner = playersService.getPlayer(String.valueOf(winner));
                    gameStateService.endGame(true);
                    break;

                default:
                    playerWinner = null;
            }
            return playerWinner;
        }else if(gameStateService.boardIsFull()){
            playerWinner = new Player("draw", "No one wins");
            gameStateService.endGame(true);
        }
        return null;

    }
}
