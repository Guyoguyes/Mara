package models;

import DB.DB;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Sighting implements DatabaseManagement{

    private int animalId;
    private String location;
    private int rangerId;
    private int id;
    private Timestamp timeReported;

    public Sighting(int animalId, String location, int rangerId){
        this.animalId = animalId;
        this.location = location;
        this.rangerId = rangerId;
    }

    public int getAnimal_id() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }

    public int getRanger_id() {
        return rangerId;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTimeReported() {
        return timeReported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (animalId != sighting.animalId) return false;
        if (rangerId != sighting.rangerId) return false;
        if (id != sighting.id) return false;
        if (location != null ? !location.equals(sighting.location) : sighting.location != null) return false;
        return timeReported != null ? timeReported.equals(sighting.timeReported) : sighting.timeReported == null;
    }

    @Override
    public int hashCode() {
        int result = animalId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + rangerId;
        result = 31 * result + id;
        result = 31 * result + (timeReported != null ? timeReported.hashCode() : 0);
        return result;
    }

    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animalId, location, rangerId, timeReported) VALUES (:animalId, :location, :rangerId, :timeReported)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", animalId)
                    .addParameter("location", location)
                    .addParameter("rangerId", rangerId)
                    .addParameter("timeReported", timeReported)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id){
        try(Connection con = DB.sql2o.open()){
            Sighting sighting = con.createQuery("SELECT * FROM sightings WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    @Override
    public void delete(){
        String sql = "DELETE FROM sightings WHERE id=:id";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }

    public Animal getAll(){
        String sql = "SELECT * FROM animals WHERE id=:id";
        try(Connection con = DB.sql2o.open()){
            Animal animal = con.createQuery(sql)
                    .addParameter("id", this.animalId)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

}
