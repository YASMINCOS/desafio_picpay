package com.picpay.picpay.services;

import com.picpay.picpay.domain.users.User;
import com.picpay.picpay.domain.users.UserType;
import com.picpay.picpay.dtos.UserDTO;
import com.picpay.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo logista nao esta atualizado a realizar transacoes");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }

    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        userRepository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
