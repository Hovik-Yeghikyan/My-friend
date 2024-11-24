package com.vector.myfriend.service;

import com.vector.myfriend.db.DBConnectionProvider;
import com.vector.myfriend.model.FriendRequest;
import com.vector.myfriend.model.User;
import com.vector.myfriend.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestService {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public void add(FriendRequest friendRequest) {
        String query = "INSERT INTO friend_request(from_id,to_id,date) VALUES(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, friendRequest.getSender().getId());
            ps.setInt(2, friendRequest.getRecipient().getId());
            ps.setString(3, DateUtil.fromWebToString(friendRequest.getDate()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                friendRequest.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<FriendRequest> getAllRequests() {
        String sql = "SELECT * from friend_request";
        List<FriendRequest> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getInt("id"));
                friendRequest.setSender(userService.getUserById(resultSet.getInt("from_id")));
                friendRequest.setRecipient(userService.getUserById(resultSet.getInt("to_id")));
                friendRequest.setDate(DateUtil.fromStringToWeb(resultSet.getString("date")));
                result.add(friendRequest);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
