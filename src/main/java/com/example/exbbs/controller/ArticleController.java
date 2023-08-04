package com.example.exbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;
import com.example.exbbs.form.ArticleForm;
import com.example.exbbs.form.CommentForm;
import com.example.exbbs.repository.ArticleRepository;
import com.example.exbbs.repository.CommentRepository;

@Controller
@Transactional
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;
    @GetMapping("")
    public String index() {
        return "";
    }
    @PostMapping("/insert-article")
    public String insertArticle(ArticleForm form) {
        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());
        articleRepository.insert(article);
        return "";
    }
    @PostMapping("/insert-comment")
    public String insertComment(CommentForm form) {
        Comment comment = new Comment();
        comment.setName(form.getName());
        comment.setContent(form.getContent());
        Integer articleId = Integer.valueOf(form.getArticleId());
        comment.setArticleId(articleId);
        commentRepository.insert(comment);
        return "";
    }
    @PostMapping("/delete-article")
    public String deleteArticle(ArticleForm aForm, CommentForm cForm) {
        
        return "";
    }
}
