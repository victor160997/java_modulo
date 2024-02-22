package com.modulodecompras.modulo.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificarProdutoDTO {

    private int qtdEstoque;

    private boolean produtoExistente;

}
