package models;

public class Animal {
    private String name;

    public Animal(String name){
        this.name = name;
    }

    //getName

    public String getName() {
        return name;
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

}
