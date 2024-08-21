package com.picpay.picpay.services;

import com.picpay.picpay.domain.users.User;
import com.picpay.picpay.domain.users.UserType;
import com.picpay.picpay.dtos.TransactionDTO;
import com.picpay.picpay.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is OK")
    void createTransactionCase1() throws Exception {

        User sender = new User(1L, "Ana", "Silva", "99999999901", "ana@gmail.com",
                "1234", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Joao", "Silva", "99999999902", "joao@gmail.com",
                "1234", new BigDecimal(15), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        verify(transactionRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(25));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transacao realizada com sucesso");
        verify(notificationService, times(1)).sendNotification(receiver, "Transacao recebida com sucesso");
    }

    @Test
    @DisplayName("Should throw Exception when Transaction is not alone")
    void createTransactionCase2() throws Exception {

        User sender = new User(1L, "Ana", "Silva", "99999999901", "ana@gmail.com",
                "1234", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Joao", "Silva", "99999999902", "joao@gmail.com",
                "1234", new BigDecimal(15), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction(any(), any())).thenReturn(false);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transacao nao autorizada", exception.getMessage());
    }
}