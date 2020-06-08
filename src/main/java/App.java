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
    public static void main(String[] args) {
        staticFileLocation("/public");

        //Database
        String connection = "jdbc:postgresql://localhost:5432/wildlife-tracker";
        Sql2o sql2o = new Sql2o(connection , "guyo", "@#scorpion");

        //get home page
        get("/", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get form
        get("/ranger/form", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
           return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ranger", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int budgeNo = Integer.parseInt(req.queryParams("budgeno"));
            Ranger ranger = new Ranger(name, budgeNo);
            ranger.save();
            model.put("ranger", ranger);
            return new ModelAndView(model, "ranger.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "ranger.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/form", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Ranger> ranger = Ranger.all();
            model.put("ranger", ranger);
            return new ModelAndView(model, "animal-form");
        }, new HandlebarsTemplateEngine());

        //post animal info
        post("/animal", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("rangerId"));
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
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
