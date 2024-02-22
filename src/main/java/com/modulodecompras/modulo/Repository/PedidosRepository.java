package com.modulodecompras.modulo.Repository;

import com.modulodecompras.modulo.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido,Long> {
}
