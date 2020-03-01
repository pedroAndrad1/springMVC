package br.com.casadocodigo.loja.daos;
/**
 * Classe para acessar o banco e fazer as transacoes de produtos
 * @author pedro
 *
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

//Anotacao para indicar que essa classe e um recurso de persistencia. Poderia usar so component, mas repository tem a 
//semantica de persistencia
@Repository 
@Transactional//Anotacao para indicar que esta classe precisa realizar transacoes com o banco
public class ProdutosDAO {
	
	//Esta classe sera instaciada pelo springMVC, esta anotacao indica para o spring injetar um EntityManager quando
	//instaciar essa classe
	@PersistenceContext
	private EntityManager em;//EntityManager para realizar as operacoes no banco
	
	public void gravar(Produto produto) {
		em.persist(produto);
	}

	public List<Produto> listar() {
		// TODO Auto-generated method stub
		
		//Vou usar uma query JPQL para buscar todos os produtos do banco, usando um alias p.
		Query query = em.createQuery("select p from Produto p");
		
		//Pegando o resultado da Query
		List<Produto> produtos =(List<Produto>) query.getResultList();
		
		return produtos;
	}
	
	
	/**
	 * Retorna um Produto do banco a partir de um Id.
	 * @param id
	 * @return
	 */
	public Produto find(Integer id) {
		/*Nao posso simplesmente fazer um em.find() pois ele nao carregaria os precos junto com o Produto. Isso acontece por 
		 *causa do LazyLoad, a lista de precos so seria carregada do banco quando fosse acessada. O que nao podera ser feito da
		 *JSP. Por isso, vou usar o JPQL para fazer um select mais rebuscado. 
		 *
		 * Atencao a alguns termos chave na query:
		 * 
		 * - distinct: para nao trazer resultados repetidos;
		 * - join : para juntar a tabela de produtos com a tabela de precos relacionada;
		 * - fetch (depois do join!): para carregar os precos junto com o produto;
		 *   */
		
		//criando a query
		TypedQuery<Produto> query = em.createQuery("select distinct(p) from Produto p join fetch p.precos where p.id = :id", Produto.class);
		
		//setando os parametros
		query.setParameter("id",id);
		
		//pegando o resultado unico
		Produto produto = query.getSingleResult();
		
		return produto;
	}
}
