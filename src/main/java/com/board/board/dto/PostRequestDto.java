package com.board.board.dto;

import com.board.board.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private long id;
    private String userName;
    private String userPassword;
    private String title;
    private String contents;

}
