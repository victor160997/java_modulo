package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Model.Fornecedores;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.FornecedoresService;
import com.modulodecompras.modulo.Services.ProdutoService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedores saveFornecedores(@RequestBody @Valid Fornecedores fornecedores){

        return fServ.saveF(fornecedores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFornecedores(@PathVariable int id,
                                              @Valid @RequestBody Fornecedores fornecedores){
        try{
            return ResponseEntity.ok(fServ.updateFornecedores(id,fornecedores));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaFornecedores(@PathVariable int id){
        try{
            return ResponseEntity.ok(fServ.apagarFornecedores(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/nome/{nom}")
    public Fornecedores getFornecedores(@PathVariable(name = "nom") String nome){
        return fServ.buscaFornecedoresPeloNome(nome);
    }

    @GetMapping("/{id}")
    public Fornecedores getFornecedoresById(@PathVariable int id){
        return fServ.buscaFornecedoresPeloId(id);
    }


    @GetMapping()
    public ResponseEntity<List<Fornecedores>> getAllfFornecedores(){
        return ResponseEntity.ok(fServ.buscaAllFornecedores());
    }

}
