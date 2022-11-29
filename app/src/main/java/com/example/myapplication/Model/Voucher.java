package com.example.myapplication.Model;

import java.io.Serializable;

public class Voucher implements Serializable {


    //loại 1 game nhà ma
    //loại 2 game nhà bóng
    //loại 3 game đua xe
    //loại 4 game bắn súng
    //loại 5 game nhún nhảy


    private int giamGia;
    private int loaiGame;
    private String maVoucher;

    public Voucher() {
    }

    public Voucher(int giamGia, int loaiGame, String maVoucher) {
        this.giamGia = giamGia;
        this.loaiGame = loaiGame;
        this.maVoucher = maVoucher;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getLoaiGame() {
        return loaiGame;
    }

    public void setLoaiGame(int loaiGame) {
        this.loaiGame = loaiGame;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }
}
