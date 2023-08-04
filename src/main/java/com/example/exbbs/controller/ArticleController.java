package com.example.exbbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;
import com.example.exbbs.form.ArticleForm;
import com.example.exbbs.form.CommentForm;
import com.example.exbbs.repository.ArticleRepository;
import com.example.exbbs.repository.CommentRepository;

import jakarta.servlet.ServletContext;

@Controller
@Transactional
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ServletContext application;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;
    @GetMapping()
    public String index(ArticleForm aForm, CommentForm cForm) {
        List<Article> articles = articleRepository.findAll();
        for (Article article:articles) {
            Integer articleId = article.getId();
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            article.setCommentList(comments);
        }
        application.setAttribute("articles", articles);
        return "form";
    }
    @PostMapping("/insert-article")
    public String insertArticle(ArticleForm form) {
        System.out.println("aaaaaa"+form);
        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());
        articleRepository.insert(article);
        return "redirect:/article";
    }
    @PostMapping("/insert-comment")
    public String insertComment(CommentForm form) {
        Comment comment = new Comment();
        comment.setName(form.getName());
        comment.setContent(form.getContent());
        Integer articleId = Integer.valueOf(form.getArticleId());
        comment.setArticleId(articleId);
        commentRepository.insert(comment);
        return "redirect:/article";
    }
    @PostMapping("/delete-article")
    public String deleteArticle(CommentForm form) {
        System.out.println(form);
        Integer articleId = Integer.valueOf(form.getArticleId());;
        commentRepository.deleteByArticleId(articleId);
        Integer id = articleId;
        articleRepository.deleteById(id); 
        
        return "redirect:/article";
    }
}
