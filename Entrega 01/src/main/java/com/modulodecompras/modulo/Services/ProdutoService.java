package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.ProdutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDao pDao;

    public Produtos saveP(Produtos produtos){
        return pDao.save(produtos);
    }
    public Produtos buscaProdutoPeloId(int id){
        Optional<Produtos> op = pDao.findById(id);

        if (op.isPresent()){
            return op.get();
        } else{
            return null;
        }
    }

    public Produtos buscaProdutoPeloNome(String nome){
        return pDao.findByNome(nome);
    }

    public List<Produtos> buscaAllProduto() {
        return pDao.findAll();
    }
    public String apagarProduto(int id) throws Exception{
        Produtos p = buscaProdutoPeloId(id);
        if (p == null){
            throw new Exception("Produto não encontrado!!");
        }
        pDao.delete(p);
        return "Produto"+id+"apagado com Sucesso!!";

    }

    public Object updateProdutos(int id, Produtos produtos) throws Exception{
        Produtos p = buscaProdutoPeloId(id);
        if (p == null){
            throw new Exception("Produto não encontrado!!");
        }
        p.setNome(produtos.getNome());
        p.setDescricao(produtos.getDescricao());
        p.setFornecedor(produtos.getFornecedor());

        return pDao.save(p);
    }

}
