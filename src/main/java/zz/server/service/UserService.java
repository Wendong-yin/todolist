package zz.server.service;

import zz.common.model.User;
import zz.server.persistence.UserPersistence;

public class UserService {

    private UserPersistence userPersistence = new UserPersistence();
    public boolean register(String username, String password){
        User user  = new User();
        user.setUsername(username);

        User existedUser = userPersistence.getByUsername(user);
        if (existedUser !=null){
            return false;
        }

        user.setPassword(password);
        user = userPersistence.create(user);

        return true;
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        boolean registered = userService.register("Mary","12312");
        System.out.println(registered);
    }
}
