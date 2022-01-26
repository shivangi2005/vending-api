package com.oracle.vending.api.model;

public class Change {
    private int ps1;
    private int ps2;
    private int ps5;
    private int ps10;
    private int ps20;
    private int ps50;
    private int pnd1;
    private int pnd2;

    public Change() {
        ps1 = 0;
        ps2 = 0;
        ps5 = 0;
        ps10 = 0;
        ps20 = 0;
        ps50 = 0;
        pnd1 = 0;
        pnd2 = 0;

    }

    public Change(int ps1, int ps2, int ps5, int ps10, int ps20, int ps50, int pnd1, int pnd2) {
        this.ps1 = ps1;
        this.ps2 = ps2;
        this.ps5 = ps5;
        this.ps10 = ps10;
        this.ps20 = ps20;
        this.ps50 = ps50;
        this.pnd1 = pnd1;
        this.pnd2 = pnd2;
    }

    public int getPs1() {
        return ps1;
    }

    public void setPs1(int ps1) {
        this.ps1 = ps1;
    }

    public int getPs2() {
        return ps2;
    }

    public void setPs2(int ps2) {
        this.ps2 = ps2;
    }

    public int getPs5() {
        return ps5;
    }

    public void setPs5(int ps5) {
        this.ps5 = ps5;
    }

    public int getPs10() {
        return ps10;
    }

    public void setPs10(int ps10) {
        this.ps10 = ps10;
    }

    public int getPs20() {
        return ps20;
    }

    public void setPs20(int ps20) {
        this.ps20 = ps20;
    }

    public int getPs50() {
        return ps50;
    }

    public void setPs50(int ps50) {
        this.ps50 = ps50;
    }

    public int getPnd1() {
        return pnd1;
    }

    public void setPnd1(int pnd1) {
        this.pnd1 = pnd1;
    }

    public int getPnd2() {
        return pnd2;
    }

    public void setPnd2(int pnd2) {
        this.pnd2 = pnd2;
    }

    public long getTotal(){
        return this.pnd1* 100L + this.pnd2* 200L
                + this.ps50* 50L + this.ps20*20L + this.ps10*10L
                + this.ps5*5L + this.ps2*2L + this.ps1;
    }

    public void subPnd1(int val){
        this.pnd1 -= val;
    }
    public void subPnd2(int val){
        this.pnd2 -= val;
    }
    public void subPs50(int val){
        this.ps50 -= val;
    }
    public void subPs20(int val){
        this.ps20 -= val;
    }
    public void subPs10(int val){
        this.ps10 -= val;
    }

    public void subPs5(int val){
        this.ps5 -= val;
    }

    public void subPs2(int val){
        this.ps2 -= val;
    }

    public void subPs1(int val){
        this.ps1 -= val;
    }

    public void addPnd1(int val){
        this.pnd1 += val;
    }
    public void addPnd2(int val){
        this.pnd2 += val;
    }
    public void addPs50(int val){
        this.ps50 += val;
    }
    public void addPs20(int val){
        this.ps20 += val;
    }
    public void addPs10(int val){
        this.ps10 += val;
    }

    public void addPs5(int val){
        this.ps5 += val;
    }

    public void addPs2(int val){
        this.ps2 += val;
    }

    public void addPs1(int val){
        this.ps1 += val;
    }



    @Override
    public String toString() {

        return String.valueOf(getTotal() + "p");
    }

    public String displayChange(){
        String str= "";
        if(pnd2 > 0){
            str += " 2\u00A3 coin(s):" + pnd2;
        }
        if(pnd1 > 0){
            str += "  1\u00A3: coin(s):" + pnd1;
        }
        if(ps50 > 0){
            str += "  50p: coin(s):" + ps50;
        }
        if(ps20 > 0){
            str += "  20p: coin(s):" + ps20;
        }
        if(ps10 > 0){
            str += "  10p: coin(s):" + ps10;
        }
        if(ps5 > 0){
            str += "  50p: coin(s):" + ps5;
        }
        if(ps2 > 0){
            str += "  2p: coin(s):" + ps2;
        }
        if(ps1 > 0){
            str += "  1p: coin(s):" + ps1;
        }
        return str;
    }
}
