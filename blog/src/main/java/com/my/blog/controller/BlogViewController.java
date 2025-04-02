package com.my.blog.controller;

import com.my.blog.controller.response.ArticleListViewResponse;
import com.my.blog.controller.response.ArticleViewResponse;
import com.my.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class BlogViewController {

    private final ArticleService articleService;

    @GetMapping("/new")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        try{
            model.addAttribute("article", articleService.findArticleById(id));
        }catch (Exception e){
             model.addAttribute("article", new ArticleViewResponse());
        }
        return "newArticle";
    }
    @GetMapping( "/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        try{
            model.addAttribute("article", articleService.findArticleById(id));
            return "article";
        }catch (Exception e){
            return "articleList";
        }
    }

    @GetMapping({"","/"})
    public String getArticles(Model model) {
        try{
            model.addAttribute("articles", articleService.findAllView());
        }catch (Exception e){
            model.addAttribute("articles", List.of(new ArticleListViewResponse()));
        }
        return "articleList";
    }
}
