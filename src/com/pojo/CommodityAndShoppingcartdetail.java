package com.pojo;

public class CommodityAndShoppingcartdetail extends Commodity{
    private int shcaid;
    private int shid;
    private int cid;
    private int num;
    private double totalprice;

    public int getShcaid() {
        return shcaid;
    }

    public void setShcaid(int shcaid) {
        this.shcaid = shcaid;
    }

    public int getShid() {
        return shid;
    }

    public void setShid(int shid) {
        this.shid = shid;
    }

    @Override
    public int getCid() {
        return cid;
    }

    @Override
    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}
