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

import br.com.avanade.controllers.dtos.CardDto;
import br.com.avanade.models.Card;
import br.com.avanade.models.User;
import br.com.avanade.services.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
        private final CardService cardService;

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Cria um card para um usuário")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Card criado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PostMapping("/{idUser}")
        public ResponseEntity<CardDto> Create(@PathVariable Long idUser, @RequestBody @Valid Card card) {
                cardService.create(idUser, card);
                CardDto dto = new CardDto(card.getId(), card.getNumber(), card.getLimit());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Lista todas os cards")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Cards listados com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping
        public ResponseEntity<List<CardDto>> ReadAll() {
                List<CardDto> dtos = cardService.readAll().stream()
                                .map(card -> new CardDto(card.getId(), card.getNumber(), card.getLimit()))
                                .collect(Collectors.toList());
                return ResponseEntity.ok(dtos);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Encontra um card pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Card encontrado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping("/{id}")
        public ResponseEntity<CardDto> Read(@PathVariable Long id) {
                Card card = cardService.read(id);
                CardDto dto = new CardDto(card.getId(), card.getNumber(), card.getLimit());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Atualiza um Card pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Card atualizada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<CardDto> Update(@PathVariable Long idUser, @PathVariable Long idCard,
                        @RequestBody @Valid Card card) {
                Card carta = cardService.update(idUser, idCard, card);
                CardDto dto = new CardDto(carta.getId(), carta.getNumber(), carta.getLimit());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Deleta um Card pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Card deletado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<?> Delete(@PathVariable Long id) {
                cardService.delete(id);
                return ResponseEntity.noContent().build();

        }
}
