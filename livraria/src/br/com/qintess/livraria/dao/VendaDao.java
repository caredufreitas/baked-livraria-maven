package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.qintess.livraria.modelo.Cliente;
import br.com.qintess.livraria.modelo.Venda;

public class VendaDao {
// listar
	public List<Venda> listar(){
		List<Venda> vendas = new ArrayList<Venda>();
		String query = "SELECT id_venda, data, total, id_cliente FROM venda";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				Cliente cliente = new Cliente();
				venda.setId_venda(rs.getInt("id_venda"));
				String data = rs.getString("data");
				venda.setData(LocalDate.parse(data));
				venda.setTotal(rs.getInt("total"));
				cliente.setId_cliente(rs.getInt("id_cliente"));
				venda.setCliente(cliente);
				vendas.add(venda);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return vendas;
	}
//	listar por id
	public Venda listaPorId(int id) {
		Venda venda = new Venda();
		String query = "SELECT id_venda, data, total, id_cliente FROM venda WHERE id_venda=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				venda.setId_venda(rs.getInt("id_venda"));
				String data = rs.getString("data");
				venda.setData(LocalDate.parse(data));
				venda.setTotal(rs.getDouble("total"));
				cliente.setId_cliente(rs.getInt("id_cliente"));
				venda.setCliente(cliente);
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
		return venda;
	}
//	deletar
	public void deletar(int id) {
		String query = "DELET FROM venda WHERE id_venda=?";
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
	public void atualizar(Venda venda) {
		String query = "UPDATE venda SET data=?, total=?, id_cliente=? WHERE id_venda=?";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setObject(1, venda.getData());
			pstm.setDouble(2, venda.getTotal());
			pstm.setInt(3, venda.getCliente().getId_cliente());
			pstm.setInt(4, venda.getId_venda());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
//	inserir
	public void inserir(Venda venda) {
		String query = "INSERT INTO venda(data, total, id_cliente) VALUES(?, ?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setObject(1, venda.getData());
			pstm.setDouble(2, venda.getTotal());
			pstm.setInt(3, venda.getCliente().getId_cliente());
			pstm.execute();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro " + e.getMessage());
		}finally {
			Conexao.getFecharConexao();
		}
	}
}
