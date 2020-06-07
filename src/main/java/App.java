import java.util.HashMap;
import java.util.Map;

import DB.DB;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //Database
        String connection = "jdbc:postgresql://localhost:5432/wildlife-tracker";
        Sql2o sql2o = new Sql2o(connection , "guyo", "@#scorpion  ");

        //get home page
        get("/", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
