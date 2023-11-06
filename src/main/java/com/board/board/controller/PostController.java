package com.board.board.controller;

import com.board.board.dto.PostRequestDto;
import com.board.board.dto.PostResponseDto;
import com.board.board.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {

    private final Map<Long, Post> postList = new HashMap<>();

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);

        // DB
        postList.put(post.getId(), post);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @GetMapping("/post")
    public List<PostResponseDto> getRead() {
        // Map to List
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList();
        return responseList;
    }

    @PutMapping("/post/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        // 메모가 DB에 존재하는지 확인
        if (postList.containsKey(id)) {
            Post post = postList.get(id);
            post.update(requestDto);
            return post.getId();
        } else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/post/{id}")
    public Long deletPost(@PathVariable Long id) {
        if (postList.containsKey(id)) {
            postList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }
}
