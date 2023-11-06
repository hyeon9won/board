package com.board.board.dto;

import com.board.board.entity.Post;
import lombok.Getter;


@Getter
public class PostResponseDto {
    private String userName;
    private String title;
    private String contents;

    public PostResponseDto(Post post) {
        this.userName = post.getUserName();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
