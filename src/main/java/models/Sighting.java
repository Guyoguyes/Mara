package models;

import DB.DB;
import org.sql2o.Connection;

import java.util.List;

public class Sighting {
    private String sight;
    private int animalId;
    private int rangerId;
    private int id;

    public Sighting(String sight, int animalId, int rangerId){
        this.sight = sight;
        this.animalId = animalId;
        this.rangerId = rangerId;
    }

    public String getSight() {
        return sight;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getRangerId() {
        return rangerId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (animalId != sighting.animalId) return false;
        if (rangerId != sighting.rangerId) return false;
        if (id != sighting.id) return false;
        return sight != null ? sight.equals(sighting.sight) : sighting.sight == null;
    }

    @Override
    public int hashCode() {
        int result = sight != null ? sight.hashCode() : 0;
        result = 31 * result + animalId;
        result = 31 * result + rangerId;
        result = 31 * result + id;
        return result;
    }

    //saved to DB
    public void save(){
        String sql = "INSERT INTO sightings (sight, animalId, rangerId) VALUES (:sight, :animalId, :rangerId)";
        try(Connection con = DB.sql2o.open()) {
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("sight", sight)
                    .addParameter("animalId", animalId)
                    .addParameter("rangerId", rangerId)
                    .executeUpdate()
                    .getKey();
        }
    }

    //get all sights
    public static List<Sighting> all(){
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }

    //find By Id
    public static Sighting find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings WHERE id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
    }
}
