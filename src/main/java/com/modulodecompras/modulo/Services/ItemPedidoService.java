package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.ItemPedido;
import com.modulodecompras.modulo.Services.dao.ItemPedidoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoDao ipDao;



    public ItemPedido saveIP(ItemPedido itemPedido){
    return ipDao.save(itemPedido);
}
}
