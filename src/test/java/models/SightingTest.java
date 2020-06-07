package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sighting_InstantiatesCorrectly_true(){
        Sighting sighting = setUpAssistant();
        assertEquals(true, sighting instanceof Sighting);
    }

    @Test
    public void sigthting_InstantiatesWithAnimalId_int(){
        Sighting sighting = setUpAssistant();
        assertEquals(1, sighting.getAnimalId());
    }

    @Test
    public void sighting_returnsTrueWhenLocationAreSame(){
        Sighting sighting = setUpAssistant();
        Sighting sighting1 = setUpAssistant();
        assertTrue(sighting.equals(sighting1));
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Sighting setUpAssistant(){
        return new Sighting("Zone A", 1, 1);
    }
}