package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Ranger implements DatabaseManagement{
    private String name;
    private int budgeno;
    private int id;

    public Ranger(String name, int budgeno){
        this.name = name;
        this.budgeno = budgeno;
    }

    public String getName() {
        return name;
    }

    public int getBudgeno() {
        return budgeno;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ranger ranger = (Ranger) o;

        if (budgeno != ranger.budgeno) return false;
        return name.equals(ranger.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + budgeno;
        return result;
    }

    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO rangers (name, budgeno) VALUES (:name, :budgeno)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("budgeno", budgeno)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Ranger> all(){
        String sql = "SELECT * FROM rangers";
        try (Connection con = DB.sql2o.open()){
            return  con.createQuery(sql)
                    .executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id){
        try(Connection con = DB.sql2o.open()){
            Ranger ranger = con.createQuery("SELECT * FROM rangers WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }

    public  List<Object> getAnimals(){
       List<Object> allAnimals = new ArrayList<Object>();
       try (Connection con = DB.sql2o.open()){
           String sql = "SELECT * FROM animals WHERE ";
           List<EndangeredAnimals> endangeredAnimals = con.createQuery(sql)
                   .addParameter("id", this.id)
                   .throwOnMappingFailure(false)
                   .executeAndFetch(EndangeredAnimals.class);
           allAnimals.addAll(endangeredAnimals);

           String sql1 = "SELECT * FROM animals";
           List<Animal> animals = con.createQuery(sql1)
                   .addParameter("id", this.id)
                   .throwOnMappingFailure(false)
                   .executeAndFetch(Animal.class);
           allAnimals.addAll(animals);
       }
       return allAnimals;
    }

    @Override
    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM rangers WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
