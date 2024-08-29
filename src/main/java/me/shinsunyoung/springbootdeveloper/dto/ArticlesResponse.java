package me.shinsunyoung.springbootdeveloper.dto;

import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.domain.Article;

@Getter
public class ArticlesResponse {



    private final String title;
    private final String content;


    public ArticlesResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
