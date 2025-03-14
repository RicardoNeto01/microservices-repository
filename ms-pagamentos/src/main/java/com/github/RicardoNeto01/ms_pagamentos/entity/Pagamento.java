package com.github.RicardoNeto01.ms_pagamentos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    private String nome;
    private String numeroDoCartao;
    private String validade;
    private String codigoDeSeguranca;
    @Column (nullable = false)
    @Enumerated (value = EnumType.STRING)
    private String status;
    @Column (nullable = false)
    private long pedidoId;
    @Column(nullable = false)
    private long formaDePagamentoId;

}
