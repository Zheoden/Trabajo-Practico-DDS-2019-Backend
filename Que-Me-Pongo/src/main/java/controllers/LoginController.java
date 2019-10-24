package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	private static Request request;
    private static Response response;


    public LoginController(Request req, Response res) {
        request = req;
        response = res;
    }

    public static ModelAndView show(Request req, Response res) {
        return new ModelAndView(null, "app/app.component.html");
    }


}
