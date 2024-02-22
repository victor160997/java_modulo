package com.modulodecompras.modulo.DTO;

import com.modulodecompras.modulo.Model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private  Long id;
    private String codPedido;
    private String produtos;
    public PedidoDTO(Pedido entity){
        this.id = entity.getId();
        this.codPedido= entity.getCodPedido();
        this.produtos = entity.getProdutos();

    }

}

