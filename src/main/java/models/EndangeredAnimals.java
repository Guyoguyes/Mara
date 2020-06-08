package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimals extends Animal implements DatabaseManagement{

    private String health;
    private String age;

    public static final String healthy = "healthy";
    public static final String ill = "ill";
    public static final String okay = "okay";


    public static final String newBorn = "newBorn";
    public static final String young = "young";
    public static final String adult = "adult";

    public static final String DATABASE_TYPE = "enAnimal";


    public EndangeredAnimals(String name, int rangerId, String health, String age) {
        super(name, rangerId);
        this.name = name;
        this.rangerId = rangerId;
        this.health = health;
        this.age = age;
        type = DATABASE_TYPE;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EndangeredAnimals that = (EndangeredAnimals) o;

        if (health != null ? !health.equals(that.health) : that.health != null) return false;
        return age != null ? age.equals(that.age) : that.age == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (health != null ? health.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public void save(){
        String sql = "INSERT INTO animals (name, rangerId, health, age) VALUES (:name, :rangerIs, :health, :age)";
        try(Connection con = DB.sql2o.open()){
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("rangerId", rangerId)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .executeUpdate()
                    .getKey();
        }
    }




    public static List<EndangeredAnimals> getAll(){
        String sql = "SELECT * FROM animals WHERE type='enAnimal'";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }

    public static  EndangeredAnimals find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE type='enAnimal'";
            EndangeredAnimals endangeredAnimals = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return  endangeredAnimals;
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE type='enAnimals'";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
