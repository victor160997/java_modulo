package com.modulodecompras.modulo.Services.dto;
import com.modulodecompras.modulo.Model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorProdutoDTO {
    public String nomeFornecedor;
    public String nomeProduto;
}
