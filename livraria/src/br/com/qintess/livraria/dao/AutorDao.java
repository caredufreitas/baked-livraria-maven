package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.Autor;

public class AutorDao {

//	listar
	public List<Autor> listar(){
		List<Autor> autores = new ArrayList<Autor>();
		String query = "SELECT id_autor, nome, e_mail FROM autor";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Autor autor = new Autor();
				autor.setId_autor(rs.getInt("id_autor"));
				autor.setNome(rs.getString("nome"));
				autor.setE_mail(rs.getString("e_mail"));
				autores.add(autor);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getLocalizedMessage());
		}
		return autores;
	} 
//	selecionar por id
	public Autor selecionarPorId(int id) {
		Autor autor = new Autor();
		String query = "SELECT id_autor, nome, e_mail FROM autor WHERE id_autor=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				autor.setId_autor(rs.getInt("id_autor"));
				autor.setNome(rs.getString("nome"));
				autor.setE_mail(rs.getString("e_mail"));
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return autor;
	}
//	deletar
	public void deletar(int id) {
		String query = "DELETE FROM autor WHERE id_autor=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	atualizar
	public void atualizar(Autor autor) {
		String query = "UPDATE autor SET nome=?, e_mail=? WHERE id_autor=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, autor.getNome());
			pstm.setString(2, autor.getE_mail());
			pstm.setInt(3, autor.getId_autor());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	inserir
	public void inserir(Autor autor) {
		String query = "INSERT INTO autor(nome, e_mail) VALUES (?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, autor.getNome());
			pstm.setString(2, autor.getE_mail());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
	
}
