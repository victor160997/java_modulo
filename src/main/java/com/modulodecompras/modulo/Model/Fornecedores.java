package com.modulodecompras.modulo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Fornecedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @Column(unique=true, length=15)
    private String telefone;

    @Column(unique=true, length=14)
    private String cnpj;

    private String descontoVolume;

    private String prazoPagamento;

    @OneToMany(mappedBy = "fornecedores", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Produtos> produtos;
    private float descontoLote;



}
