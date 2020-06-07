package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void location_InstantiatesCorrectly_true(){
        Location location = setUpAssistant();
        assertEquals(true, location instanceof Location);
    }

    @Test
    public void location_returnsTrueIfLocationAreSame(){
        Location location = setUpAssistant();
        Location location1 = setUpAssistant();
        assertTrue(location.equals(location1));
    }

    @Test
    public void location_savesObjectLocationToDatabase(){
        Location location = setUpAssistant();
        location.save();
        assertTrue(Location.all().get(0).equals(location));
    }

    @Test
    public void location_savesObjectWithIdToDatabase(){
        Location location = setUpAssistant();
        location.save();
        Location savedLocation = Location.all().get(0);
        assertEquals(location.getId(), savedLocation.getId());
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Location setUpAssistant(){
        return new Location("Westpark", 1);
    }

    public Location anotherSetUpAssistant(){
        return new Location("North park", 1);
    }
}