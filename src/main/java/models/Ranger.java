package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.List;

public class Ranger {
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
}
