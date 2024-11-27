package com.vector.myfriend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private int id;
    private User fromUser;
    private User toUser;
    private String message;
    private Date date;
}