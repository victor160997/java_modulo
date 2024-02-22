package com.modulodecompras.modulo.Services.dto;


import com.modulodecompras.modulo.Model.Fornecedores;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutosPedidoDTO {

    private  int idProduto;

    private int quantidadeProduto;

    private float valorCompra;
    private  float fornecedorDesconto;
    public ProdutosPedidoDTO(Fornecedores forn) {
        this.fornecedorDesconto = forn.getDescontoLote();

    }


}
