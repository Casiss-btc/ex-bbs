package com.example.exbbs.form;

import jakarta.validation.constraints.NotBlank;

public class CommentForm {
    private String articleId;
    @NotBlank(message="名前は必須です")
    private String name;
    @NotBlank(message="コンテンツは必須です")
    private String content;
    public String getArticleId() {
        return articleId;
    }
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
    }
    
}
