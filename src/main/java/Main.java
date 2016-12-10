import com.google.gson.Gson;
import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Main {

  public static void main(String[] args) {

    Gson gson = new Gson();
    //port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");
    /*
    get("/", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("message", "Hello World!");

      return new ModelAndView(attributes, "index.ftl");
    }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());

     //registration
    post("/login", (request, response)-> {
      Map<String, Object> attribute= new HashMap<>();
      Connection connection = null;
      try {
        connection = DatabaseUrl.extract().getConnection();
        Statement stmt = connection.createStatement();
          stmt.executeUpdate("INSERT INTO users VALIES (now())");
      }catch (Exception e) {
          attribute.put("message", "There was an error: " + e);
          return new ModelAndView(attribute, "error.ftl");
      } finally {
          if(connection != null) try{connection.close();} catch(SQLException e){}
      }

    });*/

    get("/discover", (request, response) -> {
        Map<String, Object> attribute = new HashMap<>();
        Connection conn = null;
        try {
            conn = DatabaseUrl.extract().getConnection();
            String selectQuery = "SELECT p_path FROM photos";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectQuery);

            for (int i = 0; i < 8; i++) {
                String path = resultSet.getString("p_path");
                String div = "<div id=\"image" + i + "\" class=\"col-sm-6 col-md-3 img\">\n" +
                        "                            <a href=\"#\" class=\"thumbnail\">\n" +
                        "                                <img src=" + path + "alt=\"3.jpeg\">\n" +
                        "                            </a>\n" +
                        "                        </div>";
                attribute.put("image" + i, div);
            }
            return new ModelAndView(attribute, "discover.ftl");
        } catch(Exception e) {
            attribute.put("message", e);
            return new ModelAndView(attribute, "error.ftl");
        } finally {
            if (conn != null) try{conn.close();} catch(SQLException e){}
        }
    }, new FreeMarkerEngine());

  }
}