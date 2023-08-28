package br.com.qintess.livraria.executa;

import java.time.LocalDate;

import br.com.qintess.livraria.dao.AutorDao;
import br.com.qintess.livraria.dao.ClienteDao;
import br.com.qintess.livraria.dao.EscreveDao;
import br.com.qintess.livraria.dao.GeneroDao;
import br.com.qintess.livraria.dao.ItensDaVendaDao;
import br.com.qintess.livraria.dao.LivroDao;
import br.com.qintess.livraria.dao.VendaDao;
import br.com.qintess.livraria.modelo.Autor;
import br.com.qintess.livraria.modelo.Cliente;
import br.com.qintess.livraria.modelo.Escreve;
import br.com.qintess.livraria.modelo.Genero;
import br.com.qintess.livraria.modelo.ItensDaVenda;
import br.com.qintess.livraria.modelo.Livro;
import br.com.qintess.livraria.modelo.Venda;

public class ExecutaEntidades {

	public static void main(String[] args) {
//		Genero genero = new Genero();
//		genero.setDescricao("Horror");
//		GeneroDao generoDao = new GeneroDao();
//		generoDao.inserir(genero);
//		generoDao.listar()
//		.forEach(ge -> System.out.println("ID: " + ge.getId_genero() 
//		+ "	Descricao:" + ge.getDescricao()));
		Livro livro = new Livro();
//		livro.setTitulo("Drácula");
//		livro.setPreco(39.10);
//		livro.setEstoque(3);
//		genero.setId_genero(generoDao.selecionarPorId(5).getId_genero());
//		livro.setGenero(genero);
//		LivroDao livroDao = new LivroDao();
//		livroDao.inserir(livro);
//		livroDao.listar()
//		.forEach(li -> System.out.println("ID Livro:" + li.getId_livro() 
//		+ "	Titulo:" + li.getTitulo() 
//		+ "	Preço:" + li.getPreco()
//		+ "	Estoque:" + li.getEstoque()
//		+ "	ID Genero:" + li.getGenero().getId_genero()));
//		Autor autor = new Autor();
//		autor.setNome("Bram Stoker");
//		autor.setE_mail("BramStoker@outlook.com");
//		AutorDao autorDao = new AutorDao();
//		autorDao.inserir(autor);
//		System.out.println("-------------------//-----------------------------------");
//		autorDao.listar().forEach(au -> System.out.println("ID Autor:" + au.getId_autor()
//		+ "	Nome:" + au.getNome()
//		+ "	E-Mail:" + au.getE_mail()));
//		
//		Escreve escreve = new Escreve();
//		EscreveDao escreveDao = new EscreveDao();
//		escreveDao.inserir(11, 3);
//		escreveDao.listar().forEach(es -> System.out.println("ID Livro:" + es.getLivro().getId_livro() 
//				+ "	ID Autor:" + es.getAutor().getId_autor()));
//		Cliente cliente = new Cliente();
//		cliente.setNome("Pilhado");
//		cliente.setTelefone("1199999-0134");
//		ClienteDao clienteDao = new ClienteDao();
//		clienteDao.inserir(cliente);
//		clienteDao.listar().forEach(cli -> System.out.println("ID cliente" + cli.getId_cliente() 
//		+ "	Nome:" + cli.getNome() 
//		+ "	Telefone:" + cli.getTelefone()));
		Venda venda = new Venda();
//		venda.setData(LocalDate.parse("2022-09-07"));
//		venda.setTotal(19.50);
//		Cliente cli = new Cliente();
//		cli.setId_cliente(3);
//		venda.setCliente(cli);
//		VendaDao vendaDao = new VendaDao();
//		vendaDao.inserir(venda);
//		vendaDao.listar().forEach(ve -> System.out.println("ID Venda:" + ve.getId_venda() 
//		+ "	Data:" + ve.getData() 
//		+ "	Total:" + ve.getTotal() 
//		+ "	ID Cliente:" + ve.getCliente().getId_cliente()));

		ItensDaVenda itens = new ItensDaVenda();
		venda.setId_venda(3);
		itens.setVenda(venda);
		livro.setId_livro(11);
		itens.setLivro(livro);
		itens.setQtd(2);
		
		ItensDaVendaDao itensDao = new ItensDaVendaDao();
		itensDao.inserir(itens);
		
		itensDao.listar().forEach(it -> System.out.println("ID Venda:" + it.getVenda().getId_venda() 
				+ "	ID Livro:" + it.getLivro().getId_livro() 
				+ "	Quantidade:" + it.getQtd() 
				+ "	Sub Total:" + it.getSub_total()));
	
	
	}
}// fim classe
