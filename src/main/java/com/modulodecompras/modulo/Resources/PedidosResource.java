package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.PedidosService;
import com.modulodecompras.modulo.Services.dto.FuncionariosPedidosDTO;
import com.modulodecompras.modulo.Services.dto.ProdutosPedidoDTO;
import com.modulodecompras.modulo.Services.dto.TrabalhadorDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidosResource {
    @Autowired
    private PedidosService pedidoService;


    @Operation(description = "Api que cria um pedido.")
    @PostMapping
    public ResponseEntity<ResponseEntity<String>> salvarPedido(@RequestBody Pedido pedido) {
        ResponseEntity<String> pedidoSalvo = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(pedidoSalvo);
    }

    @Operation(description = "Api que mostra todos os pedidos.")
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @Operation(description = "Api que fornece uma Lista com os Funcionarios, os Pedidos sem funcionario Responsavel" +
            " e os com Funcionario Responsavel!")
    @GetMapping(value = "/alocar")
    public ResponseEntity<FuncionariosPedidosDTO> getFuncionariosePedidos() {
        FuncionariosPedidosDTO funcionariosPedidos = pedidoService.getFuncionariosPedidos();
        return ResponseEntity.ok(funcionariosPedidos);
    }

    @Operation(description = "Api utilizada para Alocar um Funcionario a um Pedido")
    @PutMapping("/alocar")
    public ResponseEntity<?>alocarFuncionario2(@RequestParam int matriculaFunc,@RequestParam Long idPedido){
        return ResponseEntity.ok(pedidoService.alocarFuncionario(matriculaFunc,idPedido));

    }


    @Operation(description = "Api utilizada para conseguir o Status de um Pedido")
    @GetMapping(value = "/statusPedido/{id}")
    public ResponseEntity pegarStatuspedido(@RequestBody PedidoDTO dto) throws Exception {

        URL url = new URL("ffs" + dto.getCodPedido() + "ddd");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String status = "";
        StringBuilder jsonPedido = new StringBuilder();
        while ((status = br.readLine()) != null) {
            jsonPedido.append(status);
        }

        return null;
    }
}