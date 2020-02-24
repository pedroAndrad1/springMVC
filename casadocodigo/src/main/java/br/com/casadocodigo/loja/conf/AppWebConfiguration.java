package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutosDAO;

/**
 * Essa classe tem o objetivo de configurar o que tange a assusntos web, como onde estao as classes de modelo, de 
 * acesso a banco, onde estao as paginas web etc.
 * @author pedro
 *
 */

@EnableWebMvc
//Anotacao para o springMVC saber em quais pacotes estao as classes
@ComponentScan(basePackageClasses= {HomeController.class, ProdutosDAO.class}) 
public class AppWebConfiguration {
	
	/**
	 * Metodo para setar quais sao os prefixos e sufixos dos arquivos jsp.
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix( ".jsp");
		return resolver;
	}
	
	/**
	 * Indica para o springMVC onde esta o arquivos com as menssagens de validacao.
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		//Vamos usar a classe ReloadableResourceBundleMessageSource para setarmos as configuracoes
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		//Setando o caminho ate o arquivo com as mensagens
		messageSource.setBasename("/WEB-INF/messages");
		
		//Setando o encode
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		
		return messageSource;
		
	}
	
	/**
	 * Indica para o springMVC qual e a formatacao de datas a ser usada na conversao.
	 * @return
	 */
	@Bean
	public FormattingConversionService mvcConversionService() {
		//Vamos usar a classe DateFormatterRegistrar para seta o formato e o conversor.
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		
		//setando o formato
		registrar.setFormatter( new DateFormatter("dd/MM/yyyy") );
		
		//criando o conversor
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		//setando o conversor, vou usar o DefaultFormattingConversionService
		registrar.registerFormatters( conversionService );
		
		return conversionService;
	}

}
