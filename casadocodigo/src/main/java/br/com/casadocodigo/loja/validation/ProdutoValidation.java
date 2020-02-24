package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

/**
 * Esta classe valida o Objeto produto, recebido pelo form da JSP form. Ele implementa a intefarce Validator do spring, para 
 * automatizar a validacao
 * @author pedro
 *
 */
public class ProdutoValidation implements Validator {
	
	
	/**
	 * Verifica se a classe passada por parametro e realmente a classe Produto
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Produto.class.isAssignableFrom(clazz);
	}
	/**
	 * Faz a validacao do objeto.
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		//Verificando se os campos titulo e descricao estao vazios
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		
		//Fazendo o cast para Produto
		Produto produto = (Produto) target;
		
		//Verificado se o campo paginas esta vazio ou e negativo
		if(produto.getPaginas() == null || produto.getPaginas() <= 0 ) {
			errors.rejectValue("paginas", "field.required");
		}
		
		
	}
		
}
