package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.Cliente;

public class ClienteDao {
// listar
	public List<Cliente> listar(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		String query = "SELECT id_cliente, nome, telefone FROM cliente";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getString("telefone"));
				clientes.add(cliente);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um eroo " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return clientes;
	}
//	buscar por id
	public Cliente selecionarPorId(int id){
		Cliente cliente = new Cliente();
		String query = "SELECT id_cliente, nome, telefone FROM cliente WHERE id_cliente=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getString("telefone"));
			}
			
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return cliente;
	}
//	excluir
	public void excluir(int id) {
		String query = "DELETE FROM cliente WHERE id_cliente=?";
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
	public void atualizar(Cliente cliente) {
		String query = "UPDATE cliente SET nome=?, telefone=? WHERE id_cliente=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getTelefone());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	inserir
	public void inserir(Cliente cliente) {
		String query = "INSERT INTO cliente(nome, telefone) VALUES (?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getTelefone());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
	
}
