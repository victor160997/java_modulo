package com.modulodecompras.modulo.Repository;

import com.modulodecompras.modulo.Model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {




}