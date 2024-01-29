package com.tictactoe.demo.http;

import com.tictactoe.demo.models.Player;
import com.tictactoe.demo.services.GameStateService;

import java.util.Map;


public class TurnResponse {
    public Boolean gameOver = false;
    public Player winner;

    public char[][] grid;

    public TurnResponse(Player winner, char[][] grid) {
        this.winner = winner;
        this.grid = grid;
    }

    public TurnResponse(Player winner, Map<String, String> state) {
        if(winner != null) {
            this.winner = winner;
            this.gameOver = true;

        }
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }



    public Boolean getGameOver() {
        return gameOver;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }
}
