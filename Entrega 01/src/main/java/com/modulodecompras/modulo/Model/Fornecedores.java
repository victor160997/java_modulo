package com.modulodecompras.modulo.Model;


import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Fornecedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String nome;

    @Column(unique=true, length=15)
    private String telefone;

    @Column(unique=true, length=14)
    private String cnpj;

    @Column(length=15)
    private String rua;

    @Column(length=15)
    private String bairro;

    @Column(length=5)
    private String numero;

    @Column(length=20)
    private String cidade;

    @Column(length=20)
    private String estado;


}
