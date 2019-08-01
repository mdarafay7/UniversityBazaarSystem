package com.example.ubs;

public class ClubData {
    public ClubData()
    {

    }
    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String clubDesc) {
        Desc = clubDesc;
    }


    private String ClubName;
    private String Desc;


    public ClubData(String clubName, String clubDesc) {
        ClubName = clubName;
        Desc = clubDesc;

    }


}
