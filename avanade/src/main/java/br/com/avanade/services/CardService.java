package br.com.avanade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.avanade.exceptions.NotFoundException;
import br.com.avanade.models.Card;
import br.com.avanade.repositories.CardRepository;
import br.com.avanade.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public Card create(Long id, Card card) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + id);
        }

        card.setUser(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        card.setId(null);
        return cardRepository.save(card);
    }

    public Card read(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Card> readAll() {
        return cardRepository.findAll();
    }

    public Card update(Long idUser, Long idCard, Card card) {

        if (!userRepository.existsById(idUser)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + idUser);
        }

        Card newCard = cardRepository.findById(idCard).orElseThrow(() -> new RuntimeException("User not found"));
        newCard.setNumber(card.getNumber());
        newCard.setLimit(card.getLimit());
        newCard.setUser(card.getUser());

        return cardRepository.save(newCard);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
