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

//    @Test
//    public void EndangeredAnimal_InstantiatesWithRangerId_true(){
//        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
//        assertEquals(1, endangeredAnimals.getRangerId());
//    }

    @Test
    public void animal_returnsTrueIfNamesAreEqual(){
        EndangeredAnimals firstAnimal = firstSetUpAssistant();
        EndangeredAnimals secondAnimal = firstSetUpAssistant();
        assertTrue(firstAnimal.equals(secondAnimal));
    }

//    @Test
//    public void animal_savesAnimalToDatabase(){
//        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
//        endangeredAnimals.save();
//        assertTrue(EndangeredAnimals.all().get(0).equals(endangeredAnimals));
//    }

//    @Test
//    public void animal_savesAssignsObjectId(){
//        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
//        endangeredAnimals.save();
//        EndangeredAnimals savedAnimal = EndangeredAnimals.all().get(0);
//        assertEquals(endangeredAnimals.getId(), savedAnimal.getId());
//    }

//    @Test
//    public void animals_returnsAnimalWithSpecificId_secondAnimal(){
//        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
//        endangeredAnimals.save();
//        EndangeredAnimals secondAnimal = secondSetUpAssistant();
//        secondAnimal.save();
//        assertEquals(EndangeredAnimals.find(secondAnimal.getId()), secondAnimal);
//    }

//    @Test
//    public void animals_savesRangerIdToDatabase(){
//        Ranger ranger = new Ranger("Mike", 8821);
//        ranger.save();
//        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
//        endangeredAnimals.save();
//        assertEquals(endangeredAnimals.getRangerId(), ranger.getId());
//    }

//    @Test
//    public void EndangeredAnimals_InstantiatesWithHealth(){
//        EndangeredAnimals endangeredAnimals = firstSetUpAssistant();
//        assertEquals(endangeredAnimals.getHealth(), (EndangeredAnimals.healthy));
//    }

    @Test
    public void enAnimal_InstantiatesWithNameCorrectly_String(){
        EndangeredAnimals endangeredAnimal = firstSetUpAssistant();
        assertEquals("Rhino",endangeredAnimal.getName());
    }



    @After
    public void tearDown() throws Exception {
    }

    //helper method
    public EndangeredAnimals firstSetUpAssistant(){
        return new EndangeredAnimals("Rhino", 36, EndangeredAnimals.healthy, EndangeredAnimals.adult);
    }

    public EndangeredAnimals secondSetUpAssistant(){
        return new EndangeredAnimals("Elephant", 1, EndangeredAnimals.okay, EndangeredAnimals.young);
    }
}