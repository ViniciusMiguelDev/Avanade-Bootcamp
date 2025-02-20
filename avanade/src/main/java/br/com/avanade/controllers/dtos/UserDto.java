package br.com.avanade.controllers.dtos;

import java.util.List;

import br.com.avanade.entities.Account;
import br.com.avanade.entities.Card;
import br.com.avanade.entities.Feature;
import br.com.avanade.entities.News;
import br.com.avanade.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private Account account;

    private Card card;

    private List<Feature> features;

    private List<News> news;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.account = user.getAccount();
        this.card = user.getCard();
        this.features = user.getFeatures();
        this.news = user.getNews();
    }
}
