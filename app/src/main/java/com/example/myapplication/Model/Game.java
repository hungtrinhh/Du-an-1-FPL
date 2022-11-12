package com.example.myapplication.Model;

import java.io.Serializable;

public class Game implements Serializable {
    private int id;
    private int gia;
    private String kieu;
    private String tenGame;
    private String trangThai;
    private String moTa;
    public Game() {
    }

    public Game(int id, int gia, String kieu, String tenGame, String trangThai) {
        this.id = id;
        this.gia = gia;
        this.kieu = kieu;
        this.tenGame = tenGame;
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getKieu() {
        return kieu;
    }

    public void setKieu(String kieu) {
        this.kieu = kieu;
    }

    public String getTenGame() {
        return tenGame;
    }

    public void setTenGame(String tenGame) {
        this.tenGame = tenGame;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
