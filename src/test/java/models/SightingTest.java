package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.sql.Timestamp;

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
    public void sigthing_IntiatiatesWithAnimal(){
        Sighting sighting = setUpAssistant();
        assertEquals(1, sighting.getAnimal_id());
    }

    @Test
    public void sigthing_instiantesWithLocation(){
        Sighting sighting = setUpAssistant();
        assertEquals("Zone A", sighting.getLocation());
    }

    @Test
    public void sighting_InstintiatesWithRangerId(){
        Sighting sighting = setUpAssistant();
        assertEquals(1, sighting.getRanger_id());
    }

    @Test
    public void save_InsertOjectToDatabase(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        assertTrue(Sighting.all().get(0).equals(sighting));
    }

    @Test
    public void sightings_returnsAllObjectInTheDatabase(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        Sighting sighting1 = anotherSetUpAssistant();
        sighting1.save();
        assertEquals(true, Sighting.all().get(0).equals(sighting));
        assertEquals(true, Sighting.all().get(1).equals(sighting1));
    }

    @Test
    public void sightings_assignsIdOnSaving(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(sighting.getId(), savedSighting.getId());
    }

    @Test
    public void sightings_returnsObjectWithSpecificId(){
        Sighting sighting = setUpAssistant();
        sighting.save();
        Sighting sighting1 = anotherSetUpAssistant();
        sighting1.save();
        assertEquals(Sighting.find(sighting1.getId()), sighting1);
    }

//    @Test
//    public void sigthing_savesObjectWithTimeRecord(){
//        Sighting sighting = setUpAssistant();
//        sighting.save();
//        Timestamp savedTime = Sighting.find(sighting.getId()).getTimeReported();
//        Timestamp rigthNow = new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rigthNow), DateFormat.getDateTimeInstance().format(savedTime));
//    }


    //helper method
    public Sighting setUpAssistant(){
        return new Sighting(1,"Zone A",  1);
    }

    public Sighting anotherSetUpAssistant(){
        return new Sighting(1,"Zone B",  2);
    }


    @After
    public void tearDown() throws Exception {
    }
}