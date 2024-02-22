package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Model.Fornecedores;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.FornecedoresService;
import com.modulodecompras.modulo.Services.ProdutoService;
import com.modulodecompras.modulo.Services.dto.FornecedorProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedoresResource {

    @Autowired
    FornecedoresService fServ;

    @Operation(description = "Api responsavel por Criar um Fornecedor!")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedores saveFornecedores(@RequestBody @Valid Fornecedores fornecedores){

        return fServ.saveF(fornecedores);
    }

    @Operation(description = "Api responsavel por Atualizar um Fornecedor!")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFornecedores(@PathVariable int id,
                                              @Valid @RequestBody Fornecedores fornecedores){
        try{
            return ResponseEntity.ok(fServ.updateFornecedores(id,fornecedores));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api responsavel por Deletar um Fornecedor!")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaFornecedores(@PathVariable int id){
        try{
            return ResponseEntity.ok(fServ.apagarFornecedores(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api responsavel por buscar um Fornecedor pelo Nome!")
    @GetMapping("/search/nome/{nom}")
    public Fornecedores getFornecedores(@PathVariable(name = "nom") String nome){
        return fServ.buscaFornecedoresPeloNome(nome);
    }

    @Operation(description = "Api responsavel por buscar um Fornecedor pelo ID!")
    @GetMapping("/{id}")
    public Fornecedores getFornecedoresById(@PathVariable int id){
        return fServ.buscaFornecedoresPeloId(id);
    }


    @Operation(description = "Api responsavel por buscar todos Forncedores!")
    @GetMapping()
    public ResponseEntity<List<Fornecedores>> getAllfFornecedores(){
        return ResponseEntity.ok(fServ.buscaAllFornecedores());
    }

    @Operation(description = "Api responsavel por buscar fornecedores de um pedido de um cliente!")
    @GetMapping("/pesquisaPedido/{pedidoId}")
    public ResponseEntity<?> getFornecedorPorPedidoCliente(@PathVariable int pedidoId) {
        try{
            return ResponseEntity.ok(fServ.getFornecedorPorPedidoCliente(pedidoId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
