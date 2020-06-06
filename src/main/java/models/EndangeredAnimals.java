package models;

import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimals extends Animal{

    private String mHealthy;
    private String mIll;
    private String mOkay;

    private String mNewBorn;
    private String mYoung;
    private String mAdult;

    private static final String healthy = "healthy";
    private static final String ill = "ill";
    private static final String okay = "okay";


    private static final String newBorn = "newBorn";
    private static final String young = "young";
    private static final String adult = "adult";


    public EndangeredAnimals(String name, int rangerId) {
       this.name = name;
       this.rangerId = rangerId;

    }





}
