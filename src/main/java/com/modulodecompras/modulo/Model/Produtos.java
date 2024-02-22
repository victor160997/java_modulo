package com.modulodecompras.modulo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    private String nome;

//    @NotNull
    private String descricao;

    private float valorUnidade;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedores fornecedores;

    @OneToOne(mappedBy = "produtos", cascade = CascadeType.ALL, orphanRemoval = true)
    private Estoque estoque;
    private double valorTotal;
    private double preco;

}

