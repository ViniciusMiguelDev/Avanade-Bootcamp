package br.com.avanade.controllers.dtos;

import java.math.BigDecimal;

public record CardDto(
        Long id,
        String number,
        BigDecimal limit) {

}
