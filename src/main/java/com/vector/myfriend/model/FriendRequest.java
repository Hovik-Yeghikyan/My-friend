package com.vector.myfriend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {
    private int id;
    private User fromUser;
    private User toUser;
    private Date date;
}
