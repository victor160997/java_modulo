package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Repository.PedidosRepository;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository repository;
    @Transactional(readOnly = true)
    public List<PedidoDTO> fidAll(){
        List<Pedido> list = repository.findAll();
        return  list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());


    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Optional<Pedido> obj= repository.findById(id);
        Pedido entity = obj.orElseThrow(()-> new EntityNotFoundException("entity not found"));
        return new PedidoDTO(entity);
    }
    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido entity = new Pedido();
        entity.setCodPedido(dto.getCodPedido());
        entity.setProdutos(dto.getProdutos());
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }
}
