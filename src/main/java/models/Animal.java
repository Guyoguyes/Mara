package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.List;

public class Animal {
    private String name;
    private int id;

    public Animal(String name){
        this.name = name;
    }

    //getName

    public String getName() {
        return name;
    }

    //getid

    public int getId() {
        return id;
    }


    //override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        return name != null ? name.equals(animal.name) : animal.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    //save animal to database
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static Animal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
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
