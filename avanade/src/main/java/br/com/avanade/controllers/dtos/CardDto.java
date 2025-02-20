package br.com.avanade.controllers.dtos;

import java.math.BigDecimal;

import br.com.avanade.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    private String number;

    private BigDecimal limit;

    public CardDto(Card card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.limit = card.getLimit();
    }
}
