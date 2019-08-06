package com.example.ubs;

public class MsgSender {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Name;

    public void setEmail(String email) {
        Email = email;
    }


    public String getCONTENT() {
        return CONTENT;
    }


    public String getFROM() {
        return FROM;
    }



    private String Email;

    private String CONTENT;

    private String FROM;
    public MsgSender()
    {

    }

}
