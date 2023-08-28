package br.com.qintess.livraria.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
	
	private int id_venda;
	private LocalDate data;
	private double total;
	private Cliente cliente;
	private Livro livro;
	private List<ItensDaVenda> itensDasVendas = new ArrayList<ItensDaVenda>();
	
	public Venda() {
		this.data = LocalDate.now();
		this.total = 0;
	}
	
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getTotal() {
		for (ItensDaVenda itens : itensDasVendas) {
			this.total += itens.getSub_total();
		}
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<ItensDaVenda> getItensDasVendas() {
		return itensDasVendas;
	}

	public void setItensDasVendas(List<ItensDaVenda> itensDasVendas) {
		this.itensDasVendas = itensDasVendas;
	}
	
	
}
