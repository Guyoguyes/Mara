package models;

public interface DatabaseManagement {
    boolean equals(Object o);
    int hashCode();
    void save();
    void delete();
}
