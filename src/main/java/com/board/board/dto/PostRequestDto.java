package com.board.board.dto;

import com.board.board.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String userName;
    private String userPassword;
    private String title;
    private String contents;

    public PostRequestDto(Post post) {
        this.userName = post.getUserName();
        this.userPassword = post.getUserPassword();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }

}
