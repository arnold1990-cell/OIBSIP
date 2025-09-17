package com.atminterface.atminterface.model;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 14-Sep-2025
 * Description: Transaction entity representing a bank transaction in the ATM system
 */
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Account account;
    private String accountNumber;
    @Column(precision = 20,scale = 2)
    private BigDecimal amount;
    @Column(precision = 20,scale = 2)
    private BigDecimal balanceAfter;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @UpdateTimestamp
    private LocalDateTime createdAt;

    public void setType(TransactionType transactionType) {
    }
}
