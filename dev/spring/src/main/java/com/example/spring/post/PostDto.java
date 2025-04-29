package com.example.spring.post;

import java.util.Date;

import lombok.Data;

@Data

public class PostDto {
    private int id;             // 게시글 ID (Primary Key)
    private String title;       // 게시글 제목
    private String content;     // 게시글 내용
    private String username;    // 게시글 작성자
    private String password;    // 게시글 비밀번호 (수정/삭제 시 필요)
    private Date createdAt;     // 게시글 작성일시
    private Date updatedAt;     // 게시글 수정일시
}
