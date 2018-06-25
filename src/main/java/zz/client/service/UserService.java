package zz.client.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import zz.client.net.RequestSender;
import zz.common.model.User;
import zz.common.net.Request;
import zz.common.net.Response;

public class UserService {
    public boolean register(String username, String password){
        Request request = new Request();
        request.setAction("user add");

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

//        JsonObject object = new JsonObject();
//
//        object.addProperty("username",username);
//        object.addProperty("password",password);

//        String s = new Gson().toJson(object);
        String json = new Gson().toJson(user);

        request.setData(json);

        Response response = new RequestSender().send(request);
        return response.isSuccess();

    }

    public static void main(String[] args) {
        new UserService().register("aaa","bb");
    }
}
