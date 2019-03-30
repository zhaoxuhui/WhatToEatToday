package com.xuhui.whattoeattoday;

public class Food {

    private String name;
    private String ps;

    public Food() {

    }

    public Food(String name) {
        this.name = name;
    }

    public Food(String name, String ps) {
        this.name = name;
        this.ps = ps;
    }

    public String getName() {
        return this.name;
    }

    public String getPs() {
        return ps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
