package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.List;

public abstract class Animal {
    public String name;
    public int id;
    public int rangerId;



//    public Animal(String name, int rangerId){
//
//        this.name = name;
//        this.rangerId = rangerId;
//    }

    //getName

    public String getName() {
        return name;
    }

    //getid

    public int getId() {
        return id;
    }

    //Get rangerId

    public int getRangerId() {
        return rangerId;
    }


    //override


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        if (rangerId != animal.rangerId) return false;
        return name != null ? name.equals(animal.name) : animal.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + rangerId;
        return result;
    }

    //save animal to database
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, rangerId) VALUES (:name, :rangerId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("rangerId", this.rangerId)
                    .executeUpdate()
                    .getKey();

        }
    }



    public static List<Animal> all(){
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()){
          return con.createQuery(sql)
                  .executeAndFetch(Animal.class);
        }
    }
}
