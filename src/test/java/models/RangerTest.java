package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void ranger_InstantiatesWithNameAndBudgeNo_true(){
        Ranger ranger = setUpAssistant();
        assertEquals(true, ranger instanceof Ranger);
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Ranger setUpAssistant(){
        return new Ranger("Alex", 3310);
    }
}