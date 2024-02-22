package com.modulodecompras.modulo.Services.dao;

import com.modulodecompras.modulo.Model.Fornecedores;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedoresDao  extends JpaRepository<Fornecedores, Integer> {

    Fornecedores findByNome(String s);
}
