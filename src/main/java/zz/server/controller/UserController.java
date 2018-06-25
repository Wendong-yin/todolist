package zz.server.controller;

import com.google.gson.Gson;
import zz.common.model.User;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.service.UserService;

public class UserController {
    private UserService service = new UserService();

    public void add(Request request, Response response){
        Gson gson = new Gson();
        User user = gson.fromJson(request.getData(),User.class);
        boolean success = service.register(user.getUsername(),user.getPassword());

        response.setStatus(success? "success" : "failure");
    }
}
