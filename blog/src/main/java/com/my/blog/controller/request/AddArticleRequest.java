package com.my.blog.controller.request;

import com.my.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                      .title(title)
                        .content(content)
                        .build();
    }
}
