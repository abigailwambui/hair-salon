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


        get("/", (request,response) -> {
            Map<String, Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/form.vtl");
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

        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            stylist.deleteStylist();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            int phoneNumber = Integer.parseInt(request.queryParams("phoneNumber"));
            int stylistId = Integer.parseInt(request.queryParams("stylistId"));
            Client newClient = new Client(name, gender, phoneNumber, stylistId);
            newClient.save();
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    
        get("/clients", (request, response) -> {
           Map<String, Object> model = new HashMap<String, Object>();
           model.put("clients", Client.all());
           model.put("template", "templates/clients.vtl");
           return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());

       post("/clients", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
          String name = request.queryParams("name");
          String gender = request.queryParams("gender");
          int phoneNumber = Integer.parseInt(request.queryParams("phoneNumber"));
          int stylistId = Integer.parseInt(request.queryParams("stylistId"));
          Client newClient = new Client(name, gender, phoneNumber, stylist.getId());
          newClient.save();
          model.put("stylist", stylist);
          model.put("template", "templates/stylist-clients-success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
            Client client= Client.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            int phoneNumber = Integer.parseInt(request.queryParams("phoneNumber"));
            int stylistId = Integer.parseInt(request.queryParams("stylistId"));
            Stylist stylist = Stylist.find(client.getstylistId());
            client.update(name, gender, phoneNumber, stylistId);
            String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    
        post("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
            String name = request.queryParams("name");
            int phoneNumber = Integer.parseInt(request.queryParams("phoneNumber"));
            int age = Integer.parseInt(request.queryParams("age"));
            String email = request.queryParams("email");
            String workExperience = request.queryParams("workExperience");
            stylist.update(name, phoneNumber, age, email, workExperience);
            String url = String.format("/stylists/%d", stylist.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    
        post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            client.delete();
            model.put("clients", Client.all());
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
