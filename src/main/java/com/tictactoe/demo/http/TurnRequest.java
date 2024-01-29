package com.tictactoe.demo.http;

import javax.validation.constraints.*;


public class TurnRequest {

    @Size(max = 1, message = "Value should be 'x' or 'o'.")
    @NotBlank
    private String playerId;

    @Max(8)
    @Min(0)
    @NotNull
    private Integer position;

    @Max(8)
    @Min(0)
    @NotNull
    private Integer row;

    @Max(8)
    @Min(0)
    @NotNull
    private Integer column;

    @Override
    public String toString() {
        return "TurnRequest{" +
                "playerId='" + playerId + '\'' +
                ", position=" + position +
                '}';
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
