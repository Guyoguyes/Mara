package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void ranger_InstantiatesCorrectly_true(){
        Ranger ranger = setUpAssistant();
        assertEquals(true, ranger instanceof Ranger);
    }

    @Test
    public void ranger_InstantiatesWithNameCorrectly_true(){
        Ranger ranger = setUpAssistant();
        assertEquals("Alex", ranger.getName());
    }

    @Test
    public void ranger_InstantiatesWithBudgeNoCorrectly_true(){
        Ranger ranger = setUpAssistant();
        assertEquals(3310, ranger.getBudgeno());
    }

    @Test
    public void ranger_returnsTrueIfNamesAreEqual(){
        Ranger firstRanger = setUpAssistant();
        Ranger secondRanger = setUpAssistant();
        assertTrue(firstRanger.equals(secondRanger));
    }

    @Test
    public void ranger_returnsTrueIfBudgeNosAreEqual(){
        Ranger firstRanger = setUpAssistant();
        Ranger secondRanger = setUpAssistant();
        assertTrue(firstRanger.equals(secondRanger));
    }

    @Test
    public void ranger_savesObjectToDatabase(){
        Ranger ranger = setUpAssistant();
        ranger.save();
        assertTrue(Ranger.all().get(0).equals(ranger));
    }

    @Test
    public void ranger_savesObjectWithId(){
        Ranger ranger = setUpAssistant();
        ranger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(ranger.getId(), savedRanger.getId());
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Ranger setUpAssistant(){
        return new Ranger("Alex", 3310);
    }
}