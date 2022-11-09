package com.example.myapplication.Model;

import java.io.Serializable;

public class Voucher implements Serializable {
    private String maVoucher;
    private int giamGia;

    public Voucher() {
    }

    public Voucher(String maVoucher, int giamGia) {
        this.maVoucher = maVoucher;
        this.giamGia = giamGia;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }
}
