import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DB;
import models.Animal;
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
    }
}
