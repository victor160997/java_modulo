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
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private String fornecedor;

}

