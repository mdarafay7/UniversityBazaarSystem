package com.example.ubs;

public class UsrMsgData extends UserId {
    public UsrMsgData()
    {

    }
    public String getName() {
        return Name;
    }


    public void setName(String usrName) {
        Name = usrName;
    }



    private String Name;


    public UsrMsgData(String usrName) {

    Name=usrName;

    }


}
