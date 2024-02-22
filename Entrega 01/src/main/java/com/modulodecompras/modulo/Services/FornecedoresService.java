package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Fornecedores;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.FornecedoresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedoresService {

    @Autowired
    FornecedoresDao fDao;

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
        f.setBairro(fornecedores.getBairro());
        f.setCnpj(fornecedores.getCnpj());
        f.setCidade(fornecedores.getCidade());
        f.setEstado(fornecedores.getEstado());
        f.setRua(fornecedores.getRua());
        f.setNumero(fornecedores.getNumero());
        f.setTelefone(fornecedores.getTelefone());

        return fDao.save(f);
    }




}
