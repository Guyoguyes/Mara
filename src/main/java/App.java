import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DB;
import models.Animal;
import models.EndangeredAnimals;
import models.Location;
import models.Ranger;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;
import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        staticFileLocation("/public");

        //Database
        String connection = "jdbc:postgresql://naiyiqzykiouxn:3f3f5ade55c4d11159851cc3d78e07ac8f07ea10983a00e7eb09586b9b5c40c2@ec2-35-174-127-63.compute-1.amazonaws.com:5432/d1a2m2t3ei1jmm";
        Sql2o sql2o = new Sql2o(connection , "naiyiqzykiouxn", "3f3f5ade55c4d11159851cc3d78e07ac8f07ea10983a00e7eb09586b9b5c40c2");

        //get home page
        get("/", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get form
        get("/rangers", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
           return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int budgeNo = Integer.parseInt(req.queryParams("budgeno"));
            Ranger ranger = new Ranger(name, budgeNo);
            ranger.save();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animalType", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-type.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Ranger> ranger = Ranger.all();
            List<Location> locations = Location.all();
            model.put("ranger", ranger);
            model.put("locations", locations);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        //post animal info
        post("/animal", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("rangerId"));
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers.get(rangerId));
            Animal animal = new Animal(name, rangerId);
            animal.save();
//            model.put("animal", animal);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        //Endangered Animal
        get("/endangeredAnimals/form", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);

            // Health
            model.put("healthy", EndangeredAnimals.healthy);
            model.put("ill", EndangeredAnimals.ill);
            model.put("okay", EndangeredAnimals.okay);

            //Age
            model.put("newBorn", EndangeredAnimals.newBorn);
            model.put("young", EndangeredAnimals.young);
            model.put("adult", EndangeredAnimals.adult);
            return new ModelAndView(model, "endangeredAnimals-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("endangeredAnimals", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("id"));
            String health = req.queryParams("health");
            String age = req.queryParams("age");

            EndangeredAnimals endangeredAnimals = new EndangeredAnimals(name, rangerId, health, age);
            endangeredAnimals.save();
            return new ModelAndView(model, "endangeredAnimals.hbs");
        });



        get("/location/form", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "location-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/location", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String location = req.queryParams("location");
            Location location1 = new Location(location);
            location1.save();
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
