import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
     public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    port(port);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int phoneNumber = Integer.parseInt(request.queryParams("phoneNumber"));
            int age = Integer.parseInt(request.queryParams("age"));
            String email = request.queryParams("email");
            String workExperience = request.queryParams("workExperience");
            Stylist newStylist = new Stylist(name, phoneNumber, age, email, workExperience);
            newStylist.save();
            model.put("template", "templates/stylist-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    
        get("/stylists", (request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
