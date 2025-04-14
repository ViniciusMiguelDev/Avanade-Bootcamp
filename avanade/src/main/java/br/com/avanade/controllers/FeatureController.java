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

import br.com.avanade.controllers.dtos.FeatureDto;
import br.com.avanade.models.Feature;
import br.com.avanade.models.User;
import br.com.avanade.services.FeatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/features")
@AllArgsConstructor
public class FeatureController {
        private final FeatureService featureService;

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Cria uma feature para um usuário")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Feature criado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PostMapping("/{idUser}")
        public ResponseEntity<FeatureDto> Create(@PathVariable Long idUser, @RequestBody @Valid Feature feature) {
                featureService.create(idUser, feature);
                FeatureDto dto = new FeatureDto(feature.getId(), feature.getIcon(), feature.getDescription());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Lista todas as Features")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Features listadas com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping
        public ResponseEntity<List<FeatureDto>> ReadAll() {
                List<FeatureDto> dtos = featureService.readAll().stream().map(
                                feature -> new FeatureDto(feature.getId(), feature.getIcon(), feature.getDescription()))
                                .collect(Collectors.toList());
                return ResponseEntity.ok(dtos);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Encontra uma Feature pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Feature encontrada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @GetMapping("/{id}")
        public ResponseEntity<FeatureDto> Read(@PathVariable Long id) {
                Feature feature = featureService.read(id);
                FeatureDto dto = new FeatureDto(feature.getId(), feature.getIcon(), feature.getDescription());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Atualiza uma feature pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Feature atualizada com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<FeatureDto> Update(@PathVariable Long idUser, @PathVariable Long idFeature,
                        @RequestBody @Valid Feature feature) {
                Feature newFeature = featureService.update(idUser, idFeature, feature);
                FeatureDto dto = new FeatureDto(newFeature.getId(), newFeature.getIcon(), newFeature.getDescription());
                return ResponseEntity.ok(dto);
        }

        // _________________________________________________________________________________________________________________________//

        @Operation(summary = "Deleta uma feature pelo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Feature deletado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<?> Delete(@PathVariable Long id) {
                featureService.delete(id);
                return ResponseEntity.noContent().build();
        }
}
