package com.modulodecompras.modulo.Services.dao;

import com.modulodecompras.modulo.Model.Produtos;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ProdutoDao extends JpaRepository<Produtos, Integer> {

    Produtos findByNome(String s);

    Optional<Produtos> findById(Long id);
}