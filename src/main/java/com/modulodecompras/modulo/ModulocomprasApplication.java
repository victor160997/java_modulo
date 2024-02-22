package com.modulodecompras.modulo;

import com.modulodecompras.modulo.Model.Estoque;
import com.modulodecompras.modulo.Model.Fornecedores;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.EstoqueDao;
import com.modulodecompras.modulo.Services.dao.FornecedoresDao;
import com.modulodecompras.modulo.Services.dao.PedidoDao2;
import com.modulodecompras.modulo.Services.dao.ProdutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class ModulocomprasApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ModulocomprasApplication.class, args);
	}

	@Autowired
	ProdutoDao prodDao;

	@Autowired
	FornecedoresDao fornDao;

	@Autowired
	EstoqueDao estDao;

	@Autowired
	PedidoDao2 pedDao;

	@Override
	public void run(String... args) throws Exception {


		//Inserts para teste de getProdutoById
		//Inicio

		Fornecedores f = new Fornecedores();
		f.setNome("zezin");

		fornDao.save(f);

		Produtos p = new Produtos();
		p.setNome("mouse");
		p.setValorUnidade(15.50f);
		p.setFornecedores(f);
		prodDao.save(p);

		Produtos p2 = new Produtos();
		p2.setNome("teclado");
		p2.setValorUnidade(19.50f);
		p2.setFornecedores(f);
		prodDao.save(p2);

		Estoque e = new Estoque();
		e.setQuantidade(15);
		e.setProdutos(p);
		estDao.save(e);

		Estoque e2 = new Estoque();
		e2.setQuantidade(10);
		e2.setProdutos(p2);
		estDao.save(e2);

		Pedido ped = new Pedido();
		ped.setValor(10);
		ped.setAprovado(true);
		ped.setValorTotal(30);
		pedDao.save(ped);

		Pedido ped2 = new Pedido();
		ped2.setValor(10);
		ped2.setAprovado(false);
		ped2.setValorTotal(30);
		pedDao.save(ped2);

		//Fim





	}
}