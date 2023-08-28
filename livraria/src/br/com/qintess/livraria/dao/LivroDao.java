package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.Genero;
import br.com.qintess.livraria.modelo.Livro;

public class LivroDao {
// listar
	public List<Livro> listar(){
		List<Livro> livros = new ArrayList<Livro>();
		String query = "SELECT id_livro, titulo, preco, estoque, id_genero FROM livro";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				Genero genero = new Genero();
				livro.setId_livro(rs.getInt("id_livro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setEstoque(rs.getInt("estoque"));
				genero.setId_genero(rs.getInt("id_genero"));
				livro.setGenero(genero);
				
				livros.add(livro);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}
		return livros;
	}
//	selecionar por id
	public Livro selecionarPorId(int id) {
		Livro livro = new Livro();
		Genero genero = new Genero();
		String query = "SELECT id_livro, titulo, preco, estoque, id_genero FROM livro WHERE id_livro=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				livro.setId_livro(rs.getInt("id_livro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setEstoque(rs.getInt("estoque"));
				genero.setId_genero(rs.getInt("id_genero"));
				livro.setGenero(genero);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}
		return livro;
	}
//	deletar
	public void deletar(int id) {
		String query = "DELETE FROM livro WHERE id_livro=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	atualizar
	public void atualizar(Livro livro) {
		String query = "UPDATE livro SET titulo=?, preco=?, estoque=?, id_genero=? WHERE id_livro=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, livro.getTitulo());
			pstm.setDouble(2, livro.getPreco());
			pstm.setInt(3, livro.getEstoque());
			pstm.setInt(3, livro.getGenero().getId_genero());
			pstm.setInt(5, livro.getId_livro());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}
	}
//	inserir
	public void inserir(Livro livro) {
		String query = "INSERT INTO livro(titulo, preco, estoque, id_genero) VALUES (?, ?, ?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, livro.getTitulo());
			pstm.setDouble(2, livro.getPreco());
			pstm.setInt(3, livro.getEstoque());
			pstm.setInt(4, livro.getGenero().getId_genero());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
	
}
