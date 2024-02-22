package com.modulodecompras.modulo.Repository;


import com.modulodecompras.modulo.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
