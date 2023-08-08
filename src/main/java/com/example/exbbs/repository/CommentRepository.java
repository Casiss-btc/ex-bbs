package com.example.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Comment;

@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    private static final RowMapper<Comment> COMMENT_ROW_MAPPER
    = new BeanPropertyRowMapper<>(Comment.class);
    public List<Comment> findByArticleId(int articleId) {
        String sql = "SELECT id, name, content FROM comments WHERE article_id = :articleId";
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("articleId", articleId);
        List<Comment> comments = template.query(sql, param, COMMENT_ROW_MAPPER);
        return comments;
    }
    public List<Comment> findJoinByArticledId(int articleId) {
        String sql = """
            SELECT comments.id, 
            comments.name, 
            comments.content, 
            comments.article_id 
            FROM articles JOIN comments 
            ON articles.id = comments.article_id 
            WHERE article_id = :articleId
                """;
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("articleId", articleId);
        List<Comment> comments = template.query(sql, param, COMMENT_ROW_MAPPER);
        return comments;
    }
    public void insert(Comment comment) {
        String sql = "INSERT INTO comments (name, content, article_id) VALUES (:name, :content, :articleId)";
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("name", comment.getName())
        .addValue("content", comment.getContent())
        .addValue("articleId", comment.getArticleId());
        template.update(sql, param);
    }
    public void deleteByArticleId(int articleId) {
        String sql = "DELETE FROM comments WHERE article_id = :articleId";
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("articleId", articleId);
        template.update(sql, param);
    }
}
