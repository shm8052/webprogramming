package com.example.spring.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SqlSession sqlSession;

    // 게시글 등록
    public int create(PostDto post) {
        int result = -1;

        try {
            result = sqlSession.insert("postMapper.create", post);
        } catch (DataAccessException e) {
            logger.error("게시글 등록 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 게시글 목록
    public List<PostDto> list(int offset, int listCountPerPage, String searchType, String searchKeyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("searchType", searchType);
        params.put("searchKeyword", searchKeyword);

        List<PostDto> posts = null;

        try {
            posts = sqlSession.selectList("postMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("게시글 목록 오류 : {}", e.getMessage(), e);
        }

        return posts;
    }

    // 게시글 보기
    public PostDto read(int id) {
        PostDto post = null;

        try {
            post = sqlSession.selectOne("postMapper.read", id);
        } catch (DataAccessException e) {
            logger.error("게시글 보기 오류 : {}", e.getMessage(), e);
        }

        return post;
    }

    // 게시글 수정
    public int update(PostDto post) {
        int result = -1;

        try {
            result = sqlSession.update("postMapper.update", post);
        } catch (DataAccessException e) {
            logger.error("게시글 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 게시글 삭제
    public int delete(int id) {
        int result = -1;

        try {
            result = sqlSession.delete("postMapper.delete", id);
        } catch (DataAccessException e) {
            logger.error("게시글 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 게시글 수
    public int totalCount(String searchType, String searchKeyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchType", searchType);
        params.put("searchKeyword", searchKeyword);
        return sqlSession.selectOne("postMapper.totalCount", params);
    }
}
