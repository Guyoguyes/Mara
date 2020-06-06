package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimals extends Animal{

    private String mHealthy;
    private String mIll;
    private String mOkay;

    private String mNewBorn;
    private String mYoung;
    private String mAdult;

    public static final String healthy = "healthy";
    public static final String ill = "ill";
    public static final String okay = "okay";


    public static final String newBorn = "newBorn";
    public static final String young = "young";
    public static final String adult = "adult";

    public static final String DATABASE_TYPE = "enAnimal";


    public EndangeredAnimals(String name, int rangerId) {
        super(name, rangerId);
        this.name = name;
        this.rangerId = rangerId;
        mHealthy = healthy;
        mIll = ill;
        mOkay = okay;
        mNewBorn = newBorn;
        mYoung = young;
        mAdult = adult;
        type = DATABASE_TYPE;
    }

    public String getmHealthy() {
        return mHealthy;
    }

    public static List<EndangeredAnimals> all(){
        String sql = "SELECT * FROM animals WHERE type='enAnimal'";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }

    public static  EndangeredAnimals find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE id=:id";
            EndangeredAnimals endangeredAnimals = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return  endangeredAnimals;
        }
    }


}
