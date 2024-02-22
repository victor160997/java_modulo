package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Estoque;
import com.modulodecompras.modulo.Services.dao.EstoqueDao;
import com.modulodecompras.modulo.Services.dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    EstoqueDao estDao;

    public Estoque saveP(Estoque estoque){return estDao.save(estoque);}

    public Estoque buscarEstoquePeloIdProd(Long id) {
        return estDao.findByIdProduto(id);
    }

    public EstoqueDTO debitarProdutoPeloId(EstoqueDTO produto) throws Exception {
            Estoque estoque = buscarEstoquePeloIdProd((long) produto.getIdProduto());
            int qntdEstoque = estoque.getQuantidade();
            if (qntdEstoque >= produto.getQnt()) {
                estoque.setQuantidade(qntdEstoque - produto.getQnt());
            } else {
                throw new Exception("Quantidade não existe no estoque!");
            }
            estDao.save(estoque);
            produto.setQnt(estoque.getQuantidade());

            return(produto);

    }

    public EstoqueDTO adicionarProdutoPeloId(EstoqueDTO produto) {
        Estoque estoque = buscarEstoquePeloIdProd((long) produto.getIdProduto());
        int qntdEstoque = estoque.getQuantidade();
        estoque.setQuantidade(qntdEstoque + produto.getQnt());
        estDao.save(estoque);
        produto.setQnt(estoque.getQuantidade());
        return (produto);
    }

    public boolean verificarEPIPorMatricula(String matricula, Long idEPI) {
        Estoque estoque = buscarEstoquePeloIdProd(idEPI);
        if (estoque != null && estoque.getQuantidade() > 0) {
            return true; // O EPI está disponível no estoque
        } else {
            return false; // O EPI não está disponível no estoque
        }
    }




}
