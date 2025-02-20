package br.com.avanade.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", columnDefinition = "varchar(13)", nullable = false)
    private String number;

    @Column(name = "agency", columnDefinition = "varchar(5)", nullable = false)
    private String agency;

    @Column(name = "balance", precision = 20, scale = 2)
    private BigDecimal balance;

    @Column(name = "limit", precision = 20, scale = 2)
    private BigDecimal limit;
}
