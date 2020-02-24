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
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

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
		List<Produto> produtos = query.getResultList();
		
		return produtos;
	}
}
