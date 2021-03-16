package com.wallet.service;

import com.wallet.dao.UserDao;
import com.wallet.model.User;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/users")
public class UserService {
    private UserDao userDao = new UserDao();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") Long userId){
        return userDao.getUserById(userId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user){
        if(user.getId() == null) throw new IllegalArgumentException("Нет поля ID у пользователя");
        return userDao.createUser(user);
    }

    //Раскомментировать как только сделаете методы в userDao
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    public User updateUser(User user){
//        return userDao.updateUser(user);
//    }
//
//    @DELETE
//    @Path("/{userId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void deleteUser(@PathParam("userId") Long userId){
//        userDao.deleteUserById(userId);
//    }
}
