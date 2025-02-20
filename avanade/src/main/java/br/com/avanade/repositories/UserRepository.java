package br.com.avanade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avanade.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByAccountNumber(String number);

    boolean existsByCardNumber(String number);
}
