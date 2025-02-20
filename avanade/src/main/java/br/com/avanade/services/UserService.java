package br.com.avanade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.avanade.entities.User;
import br.com.avanade.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public User read(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(Long id, User user) {
        User newUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        newUser.setName(user.getName());
        newUser.setAccount(user.getAccount());
        newUser.setCard(user.getCard());
        newUser.setFeatures(user.getFeatures());
        newUser.setNews(user.getNews());
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
