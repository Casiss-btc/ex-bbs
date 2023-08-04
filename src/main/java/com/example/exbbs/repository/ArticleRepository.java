package com.example.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Article;



@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    private static final RowMapper<Article> ARTICLE_ROW_MAPPER
    = new BeanPropertyRowMapper<>(Article.class);
    public List<Article> findAll() {
        String sql = "SELECT * FROM articles ORDER BY id";
        List<Article> articles = template.query(sql, ARTICLE_ROW_MAPPER);
        return articles;
    }
    public void insert(Article article) {
        String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("name", article.getName())
        .addValue("content", article.getContent());
        template.queryForObject(sql, param, ARTICLE_ROW_MAPPER);
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM articles WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("id", id);
        template.queryForObject(sql, param, ARTICLE_ROW_MAPPER);
    }
}
