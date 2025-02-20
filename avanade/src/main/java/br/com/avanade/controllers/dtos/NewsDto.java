package br.com.avanade.controllers.dtos;

import br.com.avanade.entities.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;

    private String icon;

    private String description;

    public NewsDto(News news) {
        this.id = news.getId();
        this.icon = news.getIcon();
        this.description = news.getDescription();
    }
}
