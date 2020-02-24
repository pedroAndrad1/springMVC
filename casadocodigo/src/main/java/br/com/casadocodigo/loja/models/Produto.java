package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	private String descricao;
	private Integer paginas;
	
	//Esta anotacao indica que o atributo e uma classe Embeddable, permitindo um relaciomento.
	@ElementCollection
	private List<Preco> precos;
	
	//Anotacao para Spring converter os valores de texto para Calendar.
	//Nao configurei o formato pq isso foi configurada na classe AppWebConfiguration
	@DateTimeFormat 
	private Calendar dataLancamento;
	
	
	
	public Calendar getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public int getId() {
		return id;
	}
	public List<Preco> getPrecos() {
		return precos;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public Integer getPaginas() {
		return paginas;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}
	
	
}
