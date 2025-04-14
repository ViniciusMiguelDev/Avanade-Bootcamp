package br.com.avanade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avanade.models.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>{

}
