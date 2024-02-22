package com.modulodecompras.modulo.Repository;

import com.modulodecompras.modulo.Model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemPedido, Long> {
}
