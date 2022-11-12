package com.example.myapplication.Model;

public class GameTime extends Game{
    private int time;

    public GameTime() {

    }

    public GameTime(int id,String tenGame,String trangThai, int time) {
        super(id, 20000, "Theo giờ", tenGame, trangThai);
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int tongTien(int gia) {
        return gia*time;
    }
}
