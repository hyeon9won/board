package com.board.board.dto;

import com.board.board.entity.Post;
import lombok.Getter;


@Getter
public class PostResponseDto {
    private long id;
    private String title;
    private String contents;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
