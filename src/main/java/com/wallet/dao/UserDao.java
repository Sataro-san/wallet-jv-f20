package com.wallet.dao;

import com.wallet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserDao {

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users")){

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User getUserById(Long userId){
        String SQL = "select * from users where id=?";
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User createUser(User user){
        String SQL = "insert into users(name, password, created_date) values(?, ?, ?)";

        try(Connection connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, (java.sql.Date) user.getCreatedDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    //Дописать обновление и удаление
//
//    public User updateUser(User user){
//        users.put(user.getId(), user);
//        return users.get(user.getId());
//    }
//
//    public void deleteUserById(Long userId){
//        users.remove(userId);
//    }


}
