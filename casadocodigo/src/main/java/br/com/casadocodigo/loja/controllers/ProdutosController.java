package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutosDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller //anotacao para indicar para o springMVC que isso e um controller
@RequestMapping("/produtos")//Com essa anotacao, todos os mapeamemtos dos metodos terao /produtos como prefixo.
public class ProdutosController {
		
		//Esta classe sera instaciada pelo springMVC, esta anotacao indica para o spring injetar um EntityManager quando
		//instaciar essa classe
		@Autowired
		private ProdutosDAO produtosDAO;
		
		@Autowired //O spring instancia pra mim
		private FileSaver fileSaver;
		
		
		/**
		 * Recebe o hhtp request com o endereco produtos/detalhe com um id passado com o parametro e retorna a jsp detalhe
		 * customizado para o produto com o mesmo id no banco.
		 * @param id
		 * @return
		 */
		//anotacao para esse metodo ser chamado ao receber um http request deste endereco. O /{id} e o @PathVariable("id")
		//sao para nao ficar um ?parametro id na url
		@RequestMapping("/detalhe/{îd}") 
		public ModelAndView detalhe(@PathVariable("id") Integer id) {
				ModelAndView modelAndView = new ModelAndView("detalhe");
				//Pegando produto
				Produto produto = produtosDAO.find(id);
				//Pendurando produto
				modelAndView.addObject("produto",  produto);
				
				return modelAndView;
				
		}
		/**
		 * Recebe o hhtp request com o endereco produtos/form e retorna a jsp form.	
		 * @return
		 */
		@RequestMapping("form") //anotacao para esse metodo ser chamado ao receber um http request deste endereco
		public ModelAndView form(Produto produto) {
			//Estou passando um produto como parametro para os forms da taglib tags/form funcioanarem na jsp
			System.out.println("Entrei no form");
			/*A classe ModelAndView funciona de forma parecida com A HTTPRequest. Vamos usa-la para passar objetos para a view,
			 * por meio do método addObject(). Este funciona recebendo uma chave e um valor. A classe ModelAndView recebe o
			 * caminho para a view no construtor. */
			
			ModelAndView modelAndView = new ModelAndView("produtos/form");
			//Passando os valores de enum, em um array, mapeados na chave "tipos"
			modelAndView.addObject("tipos",TipoPreco.values()); 
			
			return modelAndView;
		}
		
		/**
		 * Recebe o hhtp request com o endereco /produtos, salva o produto cadastrado no banco e retorna uma string redirecionando
		 * para uma outra jsp. 
		 * @param produto
		 */
		//anotacao para esse metodo ser chamado ao receber um http request deste endereco
		@RequestMapping(method = RequestMethod.POST)
		//A anotacao @valid e para o SringMVC validar o Produto, o resultado tem que ser o parametro seguinte
		public ModelAndView gravar(MultipartFile sumario,@Valid Produto produto, BindingResult result, 
								   RedirectAttributes redirecAttributes) {
			//Checando se teve erro
			if(result.hasErrors()) {
				//mandando dnv para o form
				return form(produto);
			}
			//Salvando o sumario no banco(nesse caso uma pasta no projeto)
			String path = fileSaver.write("arquivos-sumario", sumario);
			
			//setando o caminho relativo do sumario do produto no produto
			produto.setSumarioPath(path);
			
			System.out.println(produto);
			this.produtosDAO.gravar(produto);
			
			//Vou usar a classe RedirectAttributes para passar um objeto de uma request http para o outro, pq vou fazer um
			//redirect. Para isso vou usar o metodo addFlashAttributes(). Isso e conhecido como escopo Flash. 
			//Sempre deve-se fazer um redirect depois de um POST, para nao haver reenvio de dados para o servidor.
			redirecAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
			
			//Fazendo um redirect como ModelAndView
			
			
			return new ModelAndView("redirect:produtos");
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView listar() {
			List<Produto> produtos = produtosDAO.listar();
			
			//Pendurando a lista no ModelAndView
			ModelAndView modelAndView = new ModelAndView("produtos/listar");
			modelAndView.addObject("produtos", produtos);
			
			return modelAndView;
			
		}
		
	
		/**
		 * Para que o Controller reconheca o validador, é preciso um método com a anotacao @InitBinder. Este metodo recebe
		 * um WebDataBinder e este binder tem um metodo addValidators, vamos usar este metodo
		 * @param binder
		 */
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.addValidators(new ProdutoValidation());
		}
		
		
}
