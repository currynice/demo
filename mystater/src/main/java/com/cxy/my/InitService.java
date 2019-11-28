package com.cxy.my;

public class InitService {
    private String initMsg = "hello";

    private boolean show = true;

    public String init(){
        return show ? initMsg : "nothing";
    }


    public void setInitMsg(String initMsg) {
        this.initMsg = initMsg;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
