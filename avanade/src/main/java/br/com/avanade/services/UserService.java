package br.com.avanade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.avanade.models.User;
import br.com.avanade.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public User read(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public User update(Long id, User user) {
        User newUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        newUser.setNome(user.getNome());
        newUser.setAccount(user.getAccount());
        newUser.setCard(user.getCard());
        newUser.setFeatures(user.getFeatures());
        newUser.setNews(user.getNews());
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.getFeatures().clear();
        user.getNews().clear();
        userRepository.deleteById(id);
    }
}
