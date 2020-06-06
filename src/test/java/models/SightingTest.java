package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sighting_InstantiatesCorrectly_true(){
        Sighting sighting = setUpAssistant();
        assertEquals(true, sighting instanceof Sighting);
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Sighting setUpAssistant(){
        return new Sighting("Zone A", 1, 1);
    }
}