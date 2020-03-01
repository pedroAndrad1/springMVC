package br.com.casadocodigo.loja.infra;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component //Anotacao para a classe ser gerenciada pelo springMVC
public class FileSaver {
	
	@Autowired //Anotacao para o springMVC injetar esse atributo
	private HttpServletRequest request;
	
	
	/**
	 * O metodo recebe um nome de folder e um file, transfere esse file para essa pasta e retorna o caminho relativo desse file
	 * (nome da pasta/nome do arquivo, omitindo o caminho completo).
	 * 
	 * @param baseFolder
	 * @param file
	 * @return
	 */
	 public String write(String baseFolder, MultipartFile file) {

	        try {
	        	//Pegando o caminho completo do folder
	            String realPath = request.getServletContext().getRealPath("/" + baseFolder);
	            //Concatenando o caminho completo com o nome do file para transferir para o folder
	            String path = realPath + "/" + file.getOriginalFilename();
	            //Transferindo o arquivo para o folder
	            file.transferTo(new File(path));
	            
	            //Retornando o caminho relativo do file
	            return baseFolder + "/" + file.getOriginalFilename();

	        } catch (Exception e) {
	            throw new RuntimeException(e);    
	        }

	    }
}
