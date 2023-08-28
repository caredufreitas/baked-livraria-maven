package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.ItensDaVenda;
import br.com.qintess.livraria.modelo.Livro;
import br.com.qintess.livraria.modelo.Venda;

public class ItensDaVendaDao {
// listar
	public List<ItensDaVenda> listar(){
		List<ItensDaVenda> itensDasVendas = new ArrayList<ItensDaVenda>();
		String query = "SELECT id_venda, id_livro, qtd, sub_total FROM itens_das_vendas";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ItensDaVenda itensDaVenda = new ItensDaVenda();
				Venda venda = new Venda();
				Livro livro = new Livro();
				venda.setId_venda(rs.getInt("id_venda"));
				livro.setId_livro(rs.getInt("id_livro"));
				itensDaVenda.setVenda(venda);
				itensDaVenda.setLivro(livro);
				itensDaVenda.setQtd(rs.getInt("qtd"));
				itensDaVenda.setSub_total(rs.getDouble("sub_total"));
				itensDasVendas.add(itensDaVenda);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return itensDasVendas;
	}
//	inserir
	public void inserir(ItensDaVenda itensDaVenda) {
		String query = "INSERT INTO itens_das_vendas(id_venda, id_livro, qtd, sub_total) VALUES(?, ?, ?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, itensDaVenda.getVenda().getId_venda());
			pstm.setInt(2, itensDaVenda.getLivro().getId_livro());
			pstm.setDouble(3, itensDaVenda.getQtd());
			pstm.setDouble(4, itensDaVenda.getSub_total());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		
	}
//	selecionar livro por cliente
	public ItensDaVenda selecionarLivroPorNomeCliente(String nome) {
		ItensDaVenda itensDaVenda = new ItensDaVenda();
		String query = "SELECT li.id_livro, li.titulo, li.preco, li.estoque FROM itens_das_vendas it INNER JOIN livro li ON it.id_livro=li.id_livro INNER JOIN venda ve ON it.id_venda=ve.id_venda INNER JOIN cliente cli ON ve.id_cliente=cli.id_cliente WHERE cli.nome=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, nome);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId_livro(rs.getInt("id_livro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setEstoque(rs.getInt("estoque"));
				itensDaVenda.setLivro(livro);
			}
			
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return itensDaVenda;
	}
}
