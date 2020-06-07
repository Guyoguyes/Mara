package models;

public class Sighting {

    private String location;
    private int animalId;
    private int rangerId;
    private int id;

    public Sighting(String location, int animalId, int rangerId){
        this.location = location;
        this.animalId = animalId;
        this.rangerId = rangerId;
    }

    public String getLocation() {
        return location;
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
        return location != null ? location.equals(sighting.location) : sighting.location == null;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + animalId;
        result = 31 * result + rangerId;
        result = 31 * result + id;
        return result;
    }
}
