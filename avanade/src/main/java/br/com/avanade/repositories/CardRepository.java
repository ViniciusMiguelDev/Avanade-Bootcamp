package br.com.avanade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avanade.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

}
