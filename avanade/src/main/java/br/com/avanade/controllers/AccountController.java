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

import br.com.avanade.controllers.dtos.AccountDto;
import br.com.avanade.models.Account;
import br.com.avanade.models.User;
import br.com.avanade.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
        private final AccountService accountService;

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Cria uma conta para um usuário")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PostMapping("/{idUser}")
        public ResponseEntity<AccountDto> Create(@PathVariable Long idUser, @RequestBody @Valid Account conta) {
                accountService.create(idUser, conta);
                AccountDto dto = new AccountDto(conta.getId(), conta.getNumber(),
                                conta.getAgency(), conta.getBalance(), conta.getLimit());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Lista todas as contas")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping
        public ResponseEntity<List<AccountDto>> ReadAll() {
                List<AccountDto> dtos = accountService
                                .readAll().stream().map(conta -> new AccountDto(conta.getId(), conta.getNumber(),
                                                conta.getAgency(), conta.getBalance(), conta.getLimit()))
                                .collect(Collectors.toList());
                return ResponseEntity.ok(dtos);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Encontra uma conta pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta encontrada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping("/{id}")
        public ResponseEntity<AccountDto> Read(@PathVariable Long id) {
                Account conta = accountService.read(id);
                AccountDto dto = new AccountDto(conta.getId(), conta.getNumber(),
                                conta.getAgency(), conta.getBalance(), conta.getLimit());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Atualiza uma conta pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta atualizada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<AccountDto> Update(@PathVariable Long idUser, @PathVariable Long idAccount,
                        @RequestBody @Valid Account account) {
                Account conta = accountService.update(idUser, idAccount, account);
                AccountDto dto = new AccountDto(conta.getId(), conta.getNumber(),
                                conta.getAgency(), conta.getBalance(), conta.getLimit());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Deleta uma Conta pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta deletado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<?> Delete(@PathVariable Long id) {
                accountService.delete(id);
                return ResponseEntity.noContent().build();
        }
}
