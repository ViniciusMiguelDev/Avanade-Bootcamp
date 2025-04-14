package br.com.avanade.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank(message = "O nome do usuário é obrigatório") String nome) {

}
