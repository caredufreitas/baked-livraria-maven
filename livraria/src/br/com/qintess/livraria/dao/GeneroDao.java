package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.Genero;
import br.com.qintess.livraria.modelo.Livro;

public class GeneroDao {
	
//	listar
	public List<Genero> listar(){
		String query = "SELECT id_genero, descricao FROM genero";
		List<Genero> generos = new ArrayList<Genero>();
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Genero genero = new Genero();
				genero.setId_genero(rs.getInt("id_genero"));
				genero.setDescricao(rs.getString("descricao"));
				generos.add(genero);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro. " + e.getMessage());
		}
		return generos;
	}
//	selecionar por ID
	public Genero selecionarPorId(int id) {
		Genero genero = new Genero();
		String query = "SELECT id_genero, descricao FROM genero WHERE id_genero=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				genero.setId_genero(rs.getInt("id_genero"));
				genero.setDescricao(rs.getString("descricao"));
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		
		return genero;
	}
// selecionar por descricao
	public List<Livro> selecionarLivrosPorGenero(String descricao) {
		List<Livro> livros = new ArrayList<Livro>();
		String query = "SELECT li.id_livro, li.titulo, li.preco, li.estoque, li.id_genero, ge.id_genero, ge.descricao FROM livro li INNER JOIN genero ge ON li.id_genero=ge.id_genero WHERE ge.descricao=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, descricao);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Livro livro = new Livro();
				Genero genero = new Genero();
				livro.setId_livro(rs.getInt("id_livro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setEstoque(rs.getInt("estoque"));
				genero.setId_genero(rs.getInt("id_genero"));
				genero.setDescricao(rs.getString("descricao"));
				
				livro.setGenero(genero);
				livros.add(livro);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return livros;
	}
//	deletar
	public void deletar(int id) {
		String query = "DELETE FROM genero WHERE id_genero=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getConexao();
		}
	}
//	atualizar
	public void atualizar(Genero genero) {
		String query = "UPDATE genero SET descricao=? WHERE id_genero=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, genero.getDescricao());
			pstm.setInt(2, genero.getId_genero());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	inserir
	public void inserir(Genero genero) {
		String query = "INSERT INTO genero(descricao) VALUES(?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm= conn.prepareStatement(query);
			pstm.setString(1, genero.getDescricao());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
}



