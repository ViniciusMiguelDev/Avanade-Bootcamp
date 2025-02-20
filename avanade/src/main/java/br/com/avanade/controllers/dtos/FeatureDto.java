package br.com.avanade.controllers.dtos;

import br.com.avanade.entities.Feature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDto {
    private Long id;

    private String icon;
    
    private String description;

    public FeatureDto(Feature feature) {
        this.id = feature.getId();
        this.icon = feature.getIcon();
        this.description = feature.getDescription();
    }
}
