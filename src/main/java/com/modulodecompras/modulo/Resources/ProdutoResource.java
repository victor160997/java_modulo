package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.ProdutoService;
import com.modulodecompras.modulo.Services.dto.ProdutoDTO;
import com.modulodecompras.modulo.Services.dto.VerificarProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    @Autowired
    ProdutoService pServ;

    @Operation(description = "Api para criação de um produto.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO saveProdutos(@RequestBody @Valid ProdutoDTO produtos){

        return (pServ.saveP(produtos));
    }

    @Operation(description = "Api para atualização de um produto.")
    @PutMapping ("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id,
                                              @Valid @RequestBody Produtos produtos){
        try{
            return ResponseEntity.ok(pServ.updateProdutos(id, produtos));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api para remoção de um produto.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaProduto(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pServ.apagarProduto(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api para retornar um produto utilizando usa ID.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProdutoById(@PathVariable Long id){


        try{
            return ResponseEntity.ok(pServ.buscaProdutoPeloIdDto(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api para verificar a existencia de um produto utilizando usa ID.")
    @GetMapping("/verificar/{id}") public VerificarProdutoDTO verificarProdutoDTO(@PathVariable Long id){

        return (pServ.verificarProduto(id));

    }

    @Operation(description = "Api para retornar uma lista com todos os produtos.")
    @GetMapping()
    public ResponseEntity<List<Produtos>> getAllProduto(){
        return ResponseEntity.ok(pServ.buscaAllProduto());
    }
}
