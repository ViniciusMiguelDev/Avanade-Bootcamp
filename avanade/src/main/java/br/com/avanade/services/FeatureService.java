package br.com.avanade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.avanade.exceptions.NotFoundException;
import br.com.avanade.models.Feature;
import br.com.avanade.repositories.FeatureRepository;
import br.com.avanade.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;

    public Feature create(Long id, Feature feature) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + id);
        }

        feature.setUser(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        feature.setId(null);
        return featureRepository.save(feature);
    }

    public Feature read(Long id) {
        return featureRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Feature> readAll() {
        return featureRepository.findAll();
    }

    public Feature update(Long idUser, Long idFeature, Feature feature) {

        if (!userRepository.existsById(idUser)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + idUser);
        }

        Feature newFeature = featureRepository.findById(idFeature)
                .orElseThrow(() -> new RuntimeException("User not found"));
        newFeature.setIcon(feature.getIcon());
        newFeature.setDescription(feature.getDescription());
        newFeature.setUser(feature.getUser());

        return featureRepository.save(newFeature);
    }

    public void delete(Long id) {
        featureRepository.deleteById(id);
    }
}
