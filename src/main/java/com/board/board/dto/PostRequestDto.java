package com.board.board.dto;

import com.board.board.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private long id;
    private String userPassword;
    private String title;
    private String contents;

    public PostRequestDto(Post post) {
        this.id = post.getId();
        this.userPassword = post.getUserPassword();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }

}
