package br.edu.ifsp.projeto_passagem_aerea.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifsp.projeto_passagem_aerea.application.service.VooService;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		VooService vooService = new VooService();
		vooService.cadastro();
		
	}

}
