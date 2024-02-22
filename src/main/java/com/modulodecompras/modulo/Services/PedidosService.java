package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.Fornecedores;
import com.modulodecompras.modulo.Model.ItemPedido;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Repository.PedidoRepository;
import com.modulodecompras.modulo.Repository.PedidosRepository;
import com.modulodecompras.modulo.Repository.ProdutoRepository;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.dao.PedidoDao2;
import com.modulodecompras.modulo.Services.dto.FuncionariosPedidosDTO;
import com.modulodecompras.modulo.Services.dto.ProdutosPedidoDTO;
import com.modulodecompras.modulo.Services.dto.TrabalhadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository pedidoRepository;

    @Autowired
    private PedidoDao2 pedDao;

    @Autowired
    private ProdutoService prodServ;

    @Autowired ItemPedidoService ipServ;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    RestTemplate restTemplate;


    public Pedido saveF(Pedido pedido){return pedDao.save(pedido);}

    public Pedido pedidoById(int id) {
        Optional<Pedido> op = pedDao.findById(id);

        if (op.isPresent()){
            return op.get();
        }else{
            return null;
        }
    }

    public Pedido findMaiorValorID() {
        Optional<Pedido> optionalPedido = pedDao.findMaiorValorID();
        return optionalPedido.orElse(null);
    }

    @Value("http://localhost:9000/api/pedido/aprovar")
    private String outraApiUrl;

    public ResponseEntity<String> salvarPedido(Pedido pedido) {
        RestTemplate restTemplate = new RestTemplate();
        double valorTotal = 0.0;
        String status = "";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Pedido> requestEntity = new HttpEntity<>(pedido, headers);
        ResponseEntity<Pedido> response = restTemplate.exchange(outraApiUrl, HttpMethod.POST, requestEntity, Pedido.class);

        // Verificar cada item do pedido
        if (response.getStatusCode().is2xxSuccessful()) {
            Pedido pedidoAprovado = response.getBody();
            if (response.getBody().isAprovado()){
                pedidoAprovado.setAprovado(true);
                for (ItemPedido item : pedidoAprovado.getItems()) {
                    Produtos produto = item.getProduto();

                    // Verificar se o produto já existe
                    if (produto.getId() == null) {
                        // O produto não existe, então salve-o antes de atribuí-lo ao item
                        produtoRepository.save(produto);
                    }

                    // Agora você pode atribuir o produto ao item
                    item.setProduto(produto);
                }

                // Calcular o valor total do pedido
                for (ItemPedido item : pedidoAprovado.getItems()) {
                    double valorItem = item.getProduto().getPreco();
                    int quantidadeItem = item.getQuantidade();
                    double valorItemTotal = valorItem * quantidadeItem;
                    valorTotal += valorItemTotal;
                }

                // Atribuir o valor total ao pedido
                pedidoAprovado.setValorTotal(valorTotal);

                // Agora você pode salvar o pedido
                pedidoRepository.save(pedidoAprovado);
                return ResponseEntity.ok("Aprovado");


            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("pagamento Negado");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("erro ao processar");
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }


    public List<Pedido> getPedidosAprovadosSemFuncionarioAlocado() {
        return pedDao.findPedidosAprovadosSemFuncionarioAlocado();
    }

    public List<Pedido> getPedidosAprovadoseAlocados() {
        return pedDao.findPedidosAlocados();
    }

    public String alocarFuncionario(int idfuncionario, Long idpedido) {
        Optional<Pedido> ped = pedDao.findById(idpedido);
        List<TrabalhadorDTO> funcionarios = getTrabalhadoresCompras();
        List<Integer> matriculas = new ArrayList<>();
        for (TrabalhadorDTO funcionario : funcionarios) {
            matriculas.add(funcionario.getMatricula());
        }
        Pedido pedido = ped.get();
        if (matriculas.contains(idfuncionario)) {
            pedido.setIdFuncionarioAlocado(idfuncionario);
            pedDao.save(pedido);
            return "Funcionário com ID: " + idfuncionario + " alocado com sucesso ao Pedido de ID: " + idpedido + "!";
        } else {
            return "A matrícula do funcionário não está na lista de matrículas permitidas.";
        }
    }

    public List<TrabalhadorDTO> getTrabalhadoresCompras(){
        restTemplate = new RestTemplate();
        String url = "https://rh-sgeu.up.railway.app/funcionario/todos";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String json = response.getBody();

        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = jsonReader.readArray();

        List<TrabalhadorDTO> trabalhadores = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.getJsonObject(i);

            JsonObject departamento = jsonObject.getJsonObject("departamento");
            String nomeDepartamento = departamento.getString("nome");

            if ("Compras".equals(nomeDepartamento)) {
                int matricula = jsonObject.getInt("matricula");
                String nome = jsonObject.getString("nome");
                boolean trabalhadorNoturno = jsonObject.getBoolean("trabalhadorNoturno");
                String cargo = jsonObject.getString("cargo");

                TrabalhadorDTO trabalhador = new TrabalhadorDTO();
                trabalhador.setNome(nome);
                trabalhador.setTrabalhadorNoturo(trabalhadorNoturno);
                trabalhador.setDepartamento(nomeDepartamento);
                trabalhador.setCargo(cargo);
                trabalhador.setMatricula(matricula);
                trabalhadores.add(trabalhador);
            }
        }

        jsonReader.close();

        return trabalhadores;
    }


    public FuncionariosPedidosDTO getFuncionariosPedidos() {
        List<TrabalhadorDTO> funcionarios = getTrabalhadoresCompras();
        List<Pedido> pedidosNaoAlocados = getPedidosAprovadosSemFuncionarioAlocado();
        List<Pedido> pedidosAlocados = getPedidosAprovadoseAlocados();

        FuncionariosPedidosDTO funcionariosPedidos = new FuncionariosPedidosDTO();

        funcionariosPedidos.setTrabalhador(funcionarios);
        funcionariosPedidos.setPedidosNaoAlocados(pedidosNaoAlocados);
        funcionariosPedidos.setPedidosAlocados(pedidosAlocados);

        return funcionariosPedidos;

    }
}
