package com.atminterface.atminterface.model;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 14-Sep-2025
 * Description: User entity representing a bank user in the ATM system
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String user_id ;
    @NotBlank(message = "Name cant be blank")
    @Column(length = 250)
    private String fullName;
    @NotBlank(message = "Enter your PIN")
    @Column(unique = true)
    @Size(min = 4,max = 4)
    private String pin;
    @NotBlank(message = "Email cant be blank")
    @Column(unique = true,length = 100)
    private String email;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
