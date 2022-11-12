package com.example.myapplication.Model;

public class GameTurn extends Game{
    private int turn;

    public GameTurn() {
    }

    public GameTurn(int id,String tenGame,String trangThai, int turn) {
        super(id, 10000, "Theo lượt", tenGame, trangThai);
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public int tongTien(int gia) {
        return gia*turn;
    }
}
