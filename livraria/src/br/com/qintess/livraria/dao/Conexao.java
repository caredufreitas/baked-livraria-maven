package br.com.qintess.livraria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection conexao;
	
//	abrir conexao
	public static Connection getConexao() {
		try {
			if (conexao == null) {
				conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_livraria_app", "postgres", "admin");
			}else {
				return conexao;
			}
		} catch (SQLException sql) {
			System.err.println("ERRO ao abrir conexao. " + sql.getMessage());
		}
		return conexao;
	}
	
//	fechar conexao
	public static void getFecharConexao() {
		try {
			conexao.close();
			conexao = null;
		} catch (SQLException sql) {
			System.err.println("ERRO ao fechar conexao. " + sql.getMessage());
		}
	}
}
