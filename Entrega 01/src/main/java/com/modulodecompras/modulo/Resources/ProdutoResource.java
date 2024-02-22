package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService pServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produtos saveProdutos(@RequestBody @Valid Produtos produtos){
        return pServ.saveP(produtos);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateProduto(@PathVariable int id,
                                              @Valid @RequestBody Produtos produtos){
        try{
            return ResponseEntity.ok(pServ.updateProdutos(id, produtos));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaProduto(@PathVariable int id){
        try{
            return ResponseEntity.ok(pServ.apagarProduto(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Produtos getProdutoById(@PathVariable int id){
        return pServ.buscaProdutoPeloId(id);
    }

    @GetMapping()
    public ResponseEntity<List<Produtos>> getAllProduto(){
        return ResponseEntity.ok(pServ.buscaAllProduto());
    }
}
