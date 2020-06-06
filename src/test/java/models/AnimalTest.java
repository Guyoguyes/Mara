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

    @Test
    public void animal_returnsTrueIfNamesAreEqual(){
        Animal firstAnimal = setUpAssistant();
        Animal secondAnimal = setUpAssistant();
        assertTrue(firstAnimal.equals(secondAnimal));
    }

    @Test
    public void animal_savesAnimalToDatabase(){
        Animal animal = setUpAssistant();
        animal.save();
        assertTrue(Animal.all().get(0).equals(animal));
    }

    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public Animal setUpAssistant(){
        return new Animal("Lion");
    }
}