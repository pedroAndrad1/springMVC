package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

//Esta anotacao permite que essa classe seja persistida do banco, desde que essa classe seja atributo de alguma outra. 
//Isto nao torna essa classe uma Entity do banco, ate pq faz sentido ter uma tabela para precos. Apenas permita que ela seja
//persistida em relacionamentos. Por isso, nao havera id's para esta classe.
@Embeddable
public class Preco {
	private BigDecimal valor;
	private TipoPreco tipo ;
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	
}
