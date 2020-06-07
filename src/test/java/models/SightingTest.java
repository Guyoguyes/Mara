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
    public void sighting_InstantiatesCorrectly(){
        Sighting sighting = setUpAssistant();
        assertEquals(true, sighting instanceof  Sighting);
    }

    @Test
    public void sighting_returnsTrueIfSightsAreSame_true(){
        Sighting sighting = setUpAssistant();
        Sighting sighting1 = setUpAssistant();
        assertTrue(sighting.equals(sighting1));
    }

    @Test
    public void sighting_savesObjectToDatabase(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        assertTrue(Sighting.all().get(0).equals(sighting));
    }

    @Test
    public void sighting_SavesObjectWithId(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(sighting.getId(), savedSighting.getId());
    }

    @Test
    public void sighting_returnsSpecificSightFromDatabase(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        Sighting sighting1 = anotherSetUpAssistant();
        sighting1.save();
        assertEquals(Sighting.find(sighting1.getId()), sighting1);
    }

//    @Test
//    public void sighting_savesRangerAndAnimalIdToDatabase(){
//        Sighting sighting = setUpAssistant();
//        sighting.save();
//        Ranger ranger = new Ranger("John", 3310);
//        ranger.save();
//        Animal animal = new Animal("Lion", 1);
//        animal.save();
//        assertEquals(sighting.getRangerId(), ranger.getId());
//        assertEquals(sighting.getAnimalId(), animal.getId());
//    }

    //helper method
    public Sighting setUpAssistant(){
        return new Sighting("Zone A", 1, 1);
    }

    public Sighting anotherSetUpAssistant(){
        return new Sighting("Zone B", 2, 2);
    }


    @After
    public void tearDown() throws Exception {
    }
}