package models;

import DB.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "guyo", "@#scorpion");
    }

    @Override
    protected void after() {
      try(Connection con = DB.sql2o.open()){
          String deleteAnimalQuery = "DELETE FROM animals *";
          con.createQuery(deleteAnimalQuery).executeUpdate();
          String deleteRangerQuery = "DELETE FROM rangers *";
          con.createQuery(deleteRangerQuery).executeUpdate();
          String deleteSightingQuery = "DELETE FROM sightings *";
          con.createQuery(deleteSightingQuery).executeUpdate();
      }
    }
}
