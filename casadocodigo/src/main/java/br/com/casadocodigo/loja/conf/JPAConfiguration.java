package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe para configuracao do JPA
 * @author pedro
 *
 */

@EnableTransactionManagement//anotacao para o sprigMVC gerenciar as transacoes do banco
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean factoryBean() {
		
		//Vamos precissar dessa classe, fornecida pelo springMVC para fazer a configuracao
		LocalContainerEntityManagerFactoryBean factoryBean = 
	            new LocalContainerEntityManagerFactoryBean();
			
			//E dessa, tbm do springMVC
	        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        
	        //Setamos esta como atributo daquela
	        factoryBean.setJpaVendorAdapter(vendorAdapter);
	        
	        //A classe  DriverManagerDataSource serve para configurarmos o driver
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setUsername("root");
	        dataSource.setPassword("8016"); // modifique para a senha do seu banco
	        dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");//setando um driver mysql
	        
	        factoryBean.setDataSource(dataSource);//setando as infos do driver
	        
	        //A classe Properties serve para configurarmos o hibernate
	        Properties props = new Properties();
	        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); //setando o dialeto
	        //setando uma propriedade para printar os comandos sql feitos pela JPA no console
	        props.setProperty("hibernate.show_sql", "true");
	        //setando uma propriedade para que qualquer mudanca no modelo seja alterada pela jpa no banco tbm
	        props.setProperty("hibernate.hbm2ddl.auto", "update");
	        
	        factoryBean.setJpaProperties(props); //setando infos dos hibernate
	        
	        //setando onde estao as entidades para o JPA cuidar
	        factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
	        
	        return factoryBean;
	}
	/**
	 * Metodo para o springMVC fornecer as transacoes para o EntityManger. Podia ter outro nome, importante e o parametro 
	 * e o retorno
	 * @param emf
	 * @return
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
	    return new JpaTransactionManager(emf);
	}
}
