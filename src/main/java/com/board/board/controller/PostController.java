package com.board.board.controller;

import com.board.board.dto.PostRequestDto;
import com.board.board.dto.PostResponseDto;
import com.board.board.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {

    private Map<String, Post> postList = new HashMap<>();

    public PostController() {
        this.postList = new HashMap<>();
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        // 작성자 이름과 비밀번호를 가져와서 확인
        String userName = requestDto.getUserName();
        String userPassword = requestDto.getUserPassword();

        if (isUserValid(userName, userPassword)) {
            Post post = new Post(requestDto);
            post.setUserName(userName); // 작성자 이름 설정
            post.setUserPassword(userPassword); // 비밀번호 설정

            // DB
            postList.put(post.getUserName(), post);

            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        } else {
            throw new IllegalArgumentException("이름 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    private boolean isUserValid(String userName, String userPassword) {
        Post post = postList.values().stream()
                .filter(p -> p.getUserName().equals(userName))
                .findFirst()
                .orElse(null);

        if (post != null) {
            // 게시물의 비밀번호가 일치하면 인증 성공
            if (post.getUserPassword().equals(userPassword)) {
                return true;
            }
        }
        // 인증 실패
        return false;
    }

    @GetMapping("/post")
    public List<PostResponseDto> getRead() {
        // Map to List
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList();
        return responseList;
    }

    @GetMapping("/list")
    public List<PostResponseDto> getAllPosts() {
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
        return responseList;
    }

    @PutMapping("/postset")
    public String updatePost(@PathVariable String userName, @RequestBody PostRequestDto requestDto) {
        // 메모가 DB에 존재하는지 확인
        if (postList.containsKey(userName)) {
            Post post = postList.get(userName);
            post.update(requestDto);
            return post.getUserName();
        } else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/postset")
    public String deletPost(@PathVariable String userName) {
        if (postList.containsKey(userName)) {
            postList.remove(userName);
            return userName;
        } else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }
}
