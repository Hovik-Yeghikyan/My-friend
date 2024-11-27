package com.vector.myfriend.service;

import com.vector.myfriend.db.DBConnectionProvider;
import com.vector.myfriend.model.Message;
import com.vector.myfriend.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public void add(Message message) {
        String sql = "INSERT INTO message(from_id,to_id, message, date) VALUES " +
                "(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, message.getFromUser().getId());
            preparedStatement.setInt(2, message.getToUser().getId());
            preparedStatement.setString(3, message.getMessage());
            preparedStatement.setString(4, DateUtil.fromDateToSqlDateTimeString(message.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessagesByFromId(int fromId) {
        String sql = "SELECT * from message WHERE from_id = " + fromId;
        List<Message> messages = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                messages.add(Message.builder()
                        .id(resultSet.getInt(1))
                        .fromUser(userService.getUserById(resultSet.getInt(2)))
                        .toUser(userService.getUserById(resultSet.getInt(3)))
                        .message(resultSet.getString(4))
                        .date(DateUtil.fromSqlStringToDate(resultSet.getString(5)))
                        .build());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public List<Message> getMessagesByToId(int toId) {
        String sql = "SELECT * from message WHERE to_id = " + toId;
        List<Message> messages = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                messages.add(Message.builder()
                        .id(resultSet.getInt(1))
                        .fromUser(userService.getUserById(resultSet.getInt(2)))
                        .toUser(userService.getUserById(resultSet.getInt(3)))
                        .message(resultSet.getString(4))
                        .date(DateUtil.fromSqlStringToDate(resultSet.getString(5)))
                        .build());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return messages;
    }


}