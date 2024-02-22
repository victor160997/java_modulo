package com.modulodecompras.modulo.Services.dao;

import com.modulodecompras.modulo.Model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoDao extends JpaRepository<ItemPedido, Integer> {
}
