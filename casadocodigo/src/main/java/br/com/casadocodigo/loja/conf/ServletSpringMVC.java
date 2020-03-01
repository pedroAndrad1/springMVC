package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Classe para configuracao do springMVC 
 * @author pedro
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	/**
	 * Metodo para o springMVC saber quais sao as classes de configuracao do spring
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	/**
	 * Metodo para configurar o encode, queremos trabalhar com as informacoes em UTF-8, para nao dar problema com caracteres
	 * especiais. 
	 */
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf8");
		
		return new Filter[] {filter};
		
	}
	
	/**
	 * Metodo para customizar o registro de arquivos (MultiPart).
	 */
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		//Criando objeto de configuracao de Multipart.
		//Neste caso, nao quero nenhuma configuracao. 
		//Por isso, passarei uma string vazia como parametro no MultiPartConfigElement.
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement("");
		
		//Setando as configuracoes
		registration.setMultipartConfig(multipartConfigElement);
		
	}

}
