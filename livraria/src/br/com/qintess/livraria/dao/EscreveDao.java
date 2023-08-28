package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.Autor;
import br.com.qintess.livraria.modelo.Escreve;
import br.com.qintess.livraria.modelo.Genero;
import br.com.qintess.livraria.modelo.Livro;

public class EscreveDao {
//	listar
	public List<Escreve> listar(){
		List<Escreve> escritores = new ArrayList<Escreve>();
		String query = "SELECT id_livro, id_autor FROM escreve";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs =pstm.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				Autor autor = new Autor();
				Escreve escreve = new Escreve();
				livro.setId_livro(rs.getInt("id_livro"));
				autor.setId_autor(rs.getInt("id_autor"));
				escreve.setLivro(livro);
				escreve.setAutor(autor);
				escritores.add(escreve);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return escritores;
	}
// inserir
	public void inserir(int id_livro, int id_autor) {
		String query = "INSERT INTO escreve(id_livro, id_autor) VALUES (?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id_livro);
			pstm.setInt(2, id_autor);
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	selecionar autor por livro
	public List<Autor> selecionarAutorPorLivro(int id_livro) {
		List<Autor> autores = new ArrayList<Autor>();
		String query = "SELECT au.id_autor, au.nome, au.e_mail FROM escreve es INNER JOIN autor au ON es.id_autor=au.id_autor "
				+ "INNER JOIN livro li ON es.id_livro=li.id_livro WHERE li.id_livro=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id_livro);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Autor autor = new Autor();
				autor.setId_autor(rs.getInt("id_autor"));
				autor.setNome(rs.getString("nome"));
				autor.setE_mail(rs.getString("e_mail"));
				autores.add(autor);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return autores;
	}
//	selecionar Livro por Autor
	public List<Livro> selecionarLivroPorAutor(int id_autor){
		List<Livro> livros = new ArrayList<Livro>();
		String query = "SELECT li.id_livro, li.titulo, li.preco, li.estoque, li.id_genero FROM escreve es INNER JOIN livro li ON es.id_livro=li.id_livro INNER JOIN autor au ON es.id_autor=au.id_autor WHERE au.id_autor=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id_autor);
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
		}finally {
			Conexao.getFecharConexao();
		}
		return livros;
	}
}
