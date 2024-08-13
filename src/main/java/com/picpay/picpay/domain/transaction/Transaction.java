package com.picpay.picpay.domain.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.picpay.picpay.domain.users.User;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @ManyToMany
    @JoinColumn(name = "sender_id")
    private List<User> sender;

    @ManyToMany
    @JoinColumn(name = "receiver_id")
    private List<User> receiver;

    private LocalDateTime timestamp;

    public Transaction() {

    }
}
