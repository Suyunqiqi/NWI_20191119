package com.pojo;

public class Shoppingcartdetails {
    private int shacaid;
    private int shid;
    private int cid;
    private int Num;
    private double toprice;

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getShacaid() {
        return shacaid;
    }

    public void setShacaid(int shacaid) {
        this.shacaid = shacaid;
    }

    public int getShid() {
        return shid;
    }

    public void setShid(int shid) {
        this.shid = shid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public double getToprice() {
        return toprice;
    }

    public void setToprice(double toprice) {
        this.toprice = toprice;
    }
}
