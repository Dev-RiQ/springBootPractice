package com.my.blog.service;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.controller.request.UpdateArticleRequest;
import com.my.blog.controller.response.ArticleListViewResponse;
import com.my.blog.controller.response.ArticleResponse;
import com.my.blog.controller.response.ArticleViewResponse;
import com.my.blog.domain.Article;
import com.my.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleViewResponse findArticleById(Long id) throws Exception {
        return articleRepository.findById(id).map(ArticleViewResponse::new).orElseThrow(Exception::new);
    }

    public ArticleResponse findById(Long id) throws Exception{
        return new ArticleResponse(articleRepository.findById(id).orElseThrow(Exception::new));
    }

    @Transactional
    public void save(AddArticleRequest addArticleRequest) throws Exception {
        if(addArticleRequest.getTitle() == null || addArticleRequest.getTitle().trim().isEmpty()){
            throw new Exception();
        }
        articleRepository.save(addArticleRequest.toEntity());
    }

    @Transactional
    public void update(Long id, UpdateArticleRequest updateArticleRequest) throws Exception {
        Article article = articleRepository.findById(id).orElseThrow();
        if(updateArticleRequest.getTitle() == null || updateArticleRequest.getTitle().trim().isEmpty()){
            throw new Exception();
        }
        article.update(updateArticleRequest);
        articleRepository.save(article);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Article article = articleRepository.findById(id).orElseThrow(Exception::new);
        articleRepository.delete(article);
    }

    public List<ArticleResponse> findAll() throws Exception {
        List<Article> article = articleRepository.findAll();
        if(article.isEmpty()){
            throw new Exception();
        }
        return article.stream().map(ArticleResponse::new).collect(Collectors.toList());
    }
    public List<ArticleListViewResponse> findAllView() throws Exception {
        List<Article> article = articleRepository.findAll();
        if(article.isEmpty()){
            throw new Exception();
        }
        return article.stream().map(ArticleListViewResponse::new).collect(Collectors.toList());
    }
}
