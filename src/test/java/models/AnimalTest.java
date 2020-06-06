package models;

import junit.framework.TestCase;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void animal_InstantiatesAnimalCorrectly_true(){
        Animal animal = setUpAssistant();
        assertEquals(true, animal instanceof Animal);
    }

    @Test
    public void animal_InstantiatesWithNameCorrectly_true(){
        Animal animal = setUpAssistant();
        assertEquals("Lion", animal.getName());
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Animal setUpAssistant(){
        return new Animal("Lion");
    }
}