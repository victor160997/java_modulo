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
    private  int id;
    private String codPedido;
    private String produtos;
    private Boolean status;


}

