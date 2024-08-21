package com.picpay.picpay.repositories;

import com.picpay.picpay.domain.users.UserType;
import com.picpay.picpay.dtos.UserDTO;
import com.picpay.picpay.domain.users.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByDocumentSuccess() {
        String document = "999999901";
        UserDTO data = new UserDTO("Yasmin", "Costa", document, new BigDecimal(10),
                "teste@gmail.com", "4444", UserType.COMMON);
        createUser(data);

        Optional<User> result = userRepository.findUserByDocument(document);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User successfully from DB when not exists ")
    void findUserByDocumentNotExists() {
        String document = "999999901";
        Optional<User> result = userRepository.findUserByDocument(document);
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        entityManager.persist(newUser);
        return newUser;
    }
}