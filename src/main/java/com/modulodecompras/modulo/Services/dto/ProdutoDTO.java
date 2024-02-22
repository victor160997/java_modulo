package com.modulodecompras.modulo.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    private int idProduto,qtdEstoque,idFornecedor;

    private String nomeProduto,descricao;

    private float precoUnit;


}
