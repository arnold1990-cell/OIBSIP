package com.atminterface.atminterface.model;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 14-Sep-2025
 * Description: Account entity representing a bank account in the ATM system
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private User user;
    @Column(unique = true)
    @NotBlank(message = "Account cant be blank")
    @Size(min = 10, max = 10)
    private String accountNumber;
    @Column(precision = 20,scale = 2)
    private BigDecimal balance;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany
    private List<Transaction>transactions;
}
