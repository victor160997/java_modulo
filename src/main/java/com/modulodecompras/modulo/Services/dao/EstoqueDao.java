package com.modulodecompras.modulo.Services.dao;

import com.modulodecompras.modulo.Model.Estoque;
import com.modulodecompras.modulo.Model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstoqueDao extends JpaRepository<Estoque,Integer> {



    @Query("SELECT p FROM Estoque p WHERE p.produtos.id = :id")
    Estoque findByIdProduto(Long id);

}
