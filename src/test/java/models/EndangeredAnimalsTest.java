package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalsTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void EndangeredAnimal_InstantiatesCorrectly(){
        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
        assertEquals(true, endangeredAnimals instanceof EndangeredAnimals);
    }

    @Test
    public void EndangeredAnimal_InstantiatesWithRangerId_true(){
        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
        assertEquals(1, endangeredAnimals.getRangerId());
    }


    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public EndangeredAnimals firstSetUpAssistant(){
        return new EndangeredAnimals("Rhino", 1);
    }

    public EndangeredAnimals secondSetUpAssistant(){
        return new EndangeredAnimals("Elephant", 1);
    }
}