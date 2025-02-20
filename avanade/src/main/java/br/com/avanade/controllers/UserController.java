package br.com.avanade.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avanade.controllers.dtos.UserDto;
import br.com.avanade.entities.User;
import br.com.avanade.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // _________________________________________________________________________________________________________________________//

    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserDto> CreateUser(@RequestBody User user) {
        userService.create(user);
        UserDto dto = new UserDto(user);
        return ResponseEntity.ok(dto);
    }

    // _________________________________________________________________________________________________________________________//

    @Operation(summary = "Lista todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> ReadAll() {
        List<UserDto> dtos = userService.readAll().stream().map(UserDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // _________________________________________________________________________________________________________________________//

    @Operation(summary = "Encontra um usuário pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> Read(@PathVariable Long id) {
        User user = userService.read(id);
        UserDto dto = new UserDto(user);
        return ResponseEntity.ok(dto);
    }

    // _________________________________________________________________________________________________________________________//

    @Operation(summary = "Atualiza um usuário pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> Update(@PathVariable Long id, @RequestBody User user) {
        User newUser = userService.update(id, user);
        UserDto dto = new UserDto(newUser);
        return ResponseEntity.ok(dto);
    }

    // _________________________________________________________________________________________________________________________//

    @Operation(summary = "Deleta um usuário pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
