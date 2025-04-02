package com.my.blog.controller;

import com.my.blog.controller.request.AddArticleRequest;
import com.my.blog.controller.request.UpdateArticleRequest;
import com.my.blog.controller.response.ArticleResponse;
import com.my.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/articles")
@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final ArticleService articleService;

    @GetMapping({"","/"})
    public ResponseEntity<Map<String,Object>> getAllArticles() {
        Map<String,Object> response = new HashMap<>();
        try {
            List<ArticleResponse> articleList = articleService.findAll();
            response.put("status", HttpStatus.OK.value());
            response.put("message", articleList);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "article not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getArticle(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<>();
        try {
            ArticleResponse article = articleService.findById(id);
            response.put("status", HttpStatus.OK.value());
            response.put("message", article);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "article not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Map<String,Object>> addArticle(@RequestBody AddArticleRequest request) {
        Map<String,Object> response = new HashMap<>();
        try {
            articleService.save(request);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "article save success");
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "article save failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PutMapping( "/{id}")
    public ResponseEntity<Map<String,Object>> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Map<String,Object> response = new HashMap<>();
        try {
            articleService.update(id, request);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "article update success");
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "article update failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<Map<String,Object>> deleteArticle(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<>();
        try {
            articleService.delete(id);
            response.put("status", HttpStatus.OK.value());
            response.put("message", "article delete success");
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "article delete failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
