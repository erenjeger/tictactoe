package com.tictactoe.demo.services;


import com.tictactoe.demo.exceptions.InvalidChoiceException;
import com.tictactoe.demo.models.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class GameStateService {

    public Boolean gameOver;


    public char[][] grid;

    public GameStateService() {

        this.gameOver = false;

    }
    public char[][]  drawBoard(char[][] gridParam) {

        for (int i = 0; i < gridParam.length; i++) {

            for (int j = 0; j < gridParam[i].length; j++) {
                gridParam[i][j] = '-';
                this.grid = gridParam;
            }

        }
        return gridParam;
    }
    public Boolean isGameOver() {
        return gameOver;
    }

    public void endGame(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void updateState(Integer row, Integer column, Player player) {


        if (row == null && column == null) {
            throw new InvalidChoiceException("The position has already been taken");
        }
//
        this.grid[row][column] =  player.getId().charAt(0);

    }


    public char checkWinner() {


        for (int i = 0; i < grid.length; i++) {

            boolean inARow = true;

            char value = grid[i][0];

            if (value == '-') {
                inARow = false;

            } else {
                for (int j = 1; j < grid[i].length; j++) {

                    if (grid[i][j] != value) {
                        inARow = false;
                        break;
                    }
                }
            }

            if (inARow) {
                return value;
            }
        }

        for (int j = 0; j < grid[0].length; j++) {
            boolean inACol = true;
            char value = grid[0][j];

            if (value == '-') {
                inACol = false;
            } else {

                for (int i = 1; i < grid.length; i++) {
                    if (grid[i][j] != value) {
                        inACol = false;
                        break;
                    }
                }
            }

            if (inACol) {

                return value;
            }
        }

        boolean inADiag1 = true;
        char value1 = grid[0][0];
        if (value1 == '-') {
            inADiag1 = false;
        } else {
            for (int i = 1; i < grid.length; i++) {
                if (grid[i][i] != value1) {
                    inADiag1 = false;
                    break;
                }
            }
        }

        if (inADiag1) {
            return value1;
        }

        boolean inADiag2 = true;
        char value2 = grid[0][grid.length - 1];

        if (value2 == '-') {
            inADiag2 = false;
        } else {
            for (int i = 1; i < grid.length; i++) {
                if (grid[i][grid.length - 1 - i] != value2) {
                    inADiag2 = false;
                    break;
                }
            }
        }

        if (inADiag2) {
            return value2;
        }

        return ' ';
    }
    public  boolean boardIsFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '-') {
                    gameOver = false;

                    return false;
                }
            }
        }
        return true;
    }


}
