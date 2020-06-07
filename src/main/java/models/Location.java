package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.List;

public class Location implements DatabaseManagement {

    private String location;
    private int sightingId;
    private int id;

    public Location(String location, int sightingId){
        this.location = location;
        this.sightingId = sightingId;
    }

    public String getLocation() {
        return location;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location1 = (Location) o;

        if (sightingId != location1.sightingId) return false;
        if (id != location1.id) return false;
        return location != null ? location.equals(location1.location) : location1.location == null;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + sightingId;
        result = 31 * result + id;
        return result;
    }

    //save to database
    @Override
    public void save(){
        String sql = "INSERT INTO locations (location, sightingId) VALUES (:location, :sightingId)";
        try(Connection con =DB.sql2o.open()) {
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("location", location)
                    .addParameter("sightingId", sightingId)
                    .executeUpdate()
                    .getKey();
        }
    }

    //get all locations
    public static List<Location> all(){
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM locations")
                    .executeAndFetch(Location.class);
        }

    }

    //find By Id
    public static Location find(int id){
        String sql = "SELECT * FROM locations WHERE id=:id";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
    }

    @Override
    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM locations WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
