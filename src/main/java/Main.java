import com.google.gson.Gson;
import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();
        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/hello", (req, res) -> "Hello World");

        get("/discover", (request, response) -> {
            Map<String, Object> attribute = new HashMap<>();
            Connection conn = null;
            try {
                conn = DatabaseUrl.extract().getConnection();
                String selectQuery = "SELECT p_path FROM photos";
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(selectQuery);

                int i = 0;
                while (resultSet.next()) {

                    String path = resultSet.getString("p_path");
                    String div = "<div id=\"image" + i + "\" class=\"col-sm-6 col-md-3 img\">\n" +
                            "                            <a href=\"#\" class=\"thumbnail\">\n" +
                            "                                <img src=\"" + path + "\" alt=\"3.jpeg\">\n" +
                            "                            </a>\n" +
                            "                        </div>";
                    attribute.put("image" + i, div);
                    i++;
                }
//
//            for (int i = 0; i < 8; i++) {
//
//            }
                return new ModelAndView(attribute, "discover.ftl");
            } catch (Exception e) {
                attribute.put("message", e);
                return new ModelAndView(attribute, "error.ftl");
            } finally {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }, new FreeMarkerEngine());


        get("/", (request, response) -> {
            return new ModelAndView(new HashMap(), "index.ftl");
        }, new FreeMarkerEngine());

        get("/index", (request, response) -> {
            Session session = request.session(true);
            String email = session.attribute("email");
            HashMap<String, String> attrubute = new HashMap<String, String>();
            if (email != null) {
                attrubute.put("email", email);
            }else {
                attrubute.put("email", "Guest");
            }
            return new ModelAndView(attrubute, "index.ftl");
        }, new FreeMarkerEngine());

        //login action
        post("/login", (request, response) -> {
            String email = request.queryParams("email");
            String pwd = request.queryParams("pwd");
            HashMap<String, Object> attribute = new HashMap<>();
            Connection conn = null;
            Session session = request.session(true);
            try {
                conn = DatabaseUrl.extract().getConnection();
                Statement stmt = conn.createStatement();
                String select = "SELECT * FROM users WHERE email ='" + email + "';";
                ResultSet resultSet = stmt.executeQuery(select);
                while (resultSet.next()) {
                    String pass = resultSet.getString("password");
                    if (pass.equals(pwd)) {
                        session.attribute("email", email);
                        session.attribute("pwd", pwd);
                        response.status(200);
                    } else {
                        response.status(409);
                        attribute.put("message", "wrong credentials");
                    }
                }
                return null;
            } catch (Exception e) {
                attribute.put("message", e);
//                return new ModelAndView(attribute, "error.ftl");
            }
            return gson.toJson(attribute);
        });


        //get current users
        get("/:id", (request, response) -> {
            Connection conn = null;
            response.type("application/json");
            ArrayList<Users> results = new ArrayList<Users>();
            Integer id = Integer.parseInt(request.params(":id"));
            try {
                conn = DatabaseUrl.extract().getConnection();
                Statement stmt = conn.createStatement();
                String select = "SELECT * FROM users where uid = " + id;
                ResultSet resultSet = stmt.executeQuery(select);
                while (resultSet.next()) {
                    results.add(new Users(resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("name")));
                }
                return results;
            } catch (Exception e) {
                return e;
            }
        }, gson::toJson);

        //get xml all users
        get("/users", (request, response) -> {
            try {
                response.type("text/xml");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/public/xml/users.xml"));
                    String line;
                    StringBuilder xml = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        xml.append(line.trim());
                    }
                    return xml.toString();
                } catch (Exception e) {
                    return e.toString();
                }
            } catch (Exception e) {
                return e;
            }
        });


    }
}