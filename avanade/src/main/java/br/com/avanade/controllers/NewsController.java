package br.com.avanade.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avanade.controllers.dtos.NewsDto;
import br.com.avanade.models.News;
import br.com.avanade.models.User;
import br.com.avanade.services.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
        private final NewsService newsService;

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Cria um news para um usuário")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "News criado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PostMapping("/{idUser}")
        public ResponseEntity<NewsDto> Create(@PathVariable Long idUser, @RequestBody @Valid News news) {
                newsService.create(idUser, news);
                NewsDto dto = new NewsDto(news.getId(), news.getIcon(), news.getDescription());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Lista todas as news")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "News listadas com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping
        public ResponseEntity<List<NewsDto>> ReadAll() {
                List<NewsDto> dtos = newsService.readAll().stream()
                                .map(news -> new NewsDto(news.getId(), news.getIcon(), news.getDescription()))
                                .collect(Collectors.toList());
                return ResponseEntity.ok(dtos);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Encontra uma Feature pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta encontrada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping("/{id}")
        public ResponseEntity<NewsDto> Read(@PathVariable Long id) {
                News news = newsService.read(id);
                NewsDto dto = new NewsDto(news.getId(), news.getIcon(), news.getDescription());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Atualiza uma News pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "News atualizada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<NewsDto> Update(@PathVariable Long idUser, @PathVariable Long idNews,
                        @RequestBody @Valid News news) {
                News newNews = newsService.update(idUser, idNews, news);
                NewsDto dto = new NewsDto(newNews.getId(), newNews.getIcon(), newNews.getDescription());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Deleta uma News pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "News deletado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<?> Delete(@PathVariable Long id) {
                newsService.delete(id);
                return ResponseEntity.noContent().build();
        }
}
