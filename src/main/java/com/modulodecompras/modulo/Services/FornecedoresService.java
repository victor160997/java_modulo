package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Fornecedores;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.FornecedoresDao;
import com.modulodecompras.modulo.Services.dao.ProdutoDao;
import com.modulodecompras.modulo.Services.dto.FornecedorProdutoDTO;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedoresService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FornecedoresDao fDao;
    //@Autowired
    //ProdutoService pServ;

    @Autowired
    ProdutoDao pDao;

    public Fornecedores saveF(Fornecedores fornecedores){return fDao.save(fornecedores);}

    public Fornecedores buscaFornecedoresPeloId(int id) {
        Optional<Fornecedores> op = fDao.findById(id);

        if (op.isPresent()){
            return op.get();
        }else{
            return null;
        }
    }

    public Fornecedores buscaFornecedoresPeloNome(String nome){
        return fDao.findByNome(nome);
    }

    public List<Fornecedores> buscaAllFornecedores() {
        return fDao.findAll();
    }

    public String apagarFornecedores(int id) throws Exception {
        Fornecedores f = buscaFornecedoresPeloId(id);
        if (f == null){
            throw new Exception("Fornecedor não encontrado!!");
        }
        fDao.delete(f);
        return "Fornecedor "+id+" apagado com Sucesso!!";
    }

    public Object updateFornecedores(int id, Fornecedores fornecedores) throws Exception{
        Fornecedores f = buscaFornecedoresPeloId(id);
        if (f == null){
            throw new Exception("Fornecedor não encontrado!!");
        }
        f.setNome(fornecedores.getNome());
        f.setCnpj(fornecedores.getCnpj());
        f.setTelefone(fornecedores.getTelefone());
        f.setPrazoPagamento(fornecedores.getPrazoPagamento());
        f.setDescontoVolume(fornecedores.getDescontoVolume());

        return fDao.save(f);
    }

    public List<FornecedorProdutoDTO> getFornecedorPorPedidoCliente (int pedidoId) throws Exception {
        restTemplate = new RestTemplate();
        String url = "http://backend-vendas-production.up.railway.app/pedido/buscar/" + pedidoId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String json = response.getBody();
        if (json == null) {
            throw new Exception("Pedido não encontrado!");
        }
            JsonReader jsonReader = Json.createReader(new StringReader(json));
            JsonObject pedido = jsonReader.readObject();
            JsonArray jsonArray = pedido.getJsonArray("itensPedido");

            List<FornecedorProdutoDTO> fornProdutoDTOs = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.getJsonObject(0);
                JsonObject itemPedido = jsonObject.getJsonObject("itemPedido");
                int idProduto = itemPedido.getInt("idProduto");
                Optional<Produtos> op = pDao.findById(Long.valueOf(idProduto));
                Produtos p = null;
                if (op.isPresent()) {
                    p = op.get();
                }
                String nomeP = "";
                String nomeF = "";
                if (p == null) {
                    nomeP = "Produto não encontrado";
                } else {
                    Fornecedores f = p.getFornecedores();
                    if (f == null) {
                        nomeF = "Fornecedor não encontrado";
                    } else {
                        nomeF = f.getNome();
                    }
                    nomeP = p.getNome();
                }
                FornecedorProdutoDTO fornPedido = FornecedorProdutoDTO.builder()
                        .nomeFornecedor(nomeF)
                        .nomeProduto(nomeP)
                        .build();
                fornProdutoDTOs.add(fornPedido);
            }
            jsonReader.close();
            return fornProdutoDTOs;
    }
}
