package me.shinsunyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.dto.ArticlesResponse;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController  //Http Response Body에 객체 데이터를 jSON형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    //@RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        // 요청한 자원 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticlesResponse>> findAllArticles(){
        List<ArticlesResponse> articles = blogService.findAll()
                .stream()
                .map(ArticlesResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }


    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticlesResponse> findArticle(@PathVariable long id ){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticlesResponse(article));
    }


    @DeleteMapping("/api/articles/{id}")
     public ResponseEntity<Void> deleteArticle(@PathVariable long id ){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }

}
