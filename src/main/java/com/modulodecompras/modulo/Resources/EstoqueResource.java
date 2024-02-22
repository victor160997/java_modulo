package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Services.EstoqueService;
import com.modulodecompras.modulo.Services.dto.EstoqueDTO;
import com.modulodecompras.modulo.Services.dto.ProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/estoque")
public class EstoqueResource {
    @Autowired
    EstoqueService eServ;

    @Operation(description = "Api responsavel por retirar quantidade de produto do estoque!")
    @PostMapping("/debitar")
    public ResponseEntity<?> debitarProdutoById(@Valid @RequestBody EstoqueDTO produto){
        try{
            return ResponseEntity.ok(eServ.debitarProdutoPeloId(produto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api responsavel por adicionar quantidade de produto no estoque!")
    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarProdutoById(@Valid @RequestBody EstoqueDTO produto) {
        return ResponseEntity.ok(eServ.adicionarProdutoPeloId(produto));
    }

    @Operation(description = "Api responsavel por verificar disponibiliade de EPI no estoque!")
    @GetMapping("/verificar-epi/{matricula}/{idEPI}")
    public ResponseEntity<?> verificarEPIPorMatricula(@PathVariable String matricula, @PathVariable Long idEPI) {
        boolean possuiEPI = eServ.verificarEPIPorMatricula(matricula, idEPI);
        if (possuiEPI) {
            return ResponseEntity.ok("O EPI está disponível no estoque.");
        } else {
            return ResponseEntity.ok("O EPI não está disponível no estoque.");
        }
    }



}
