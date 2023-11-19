package br.edu.ifsp.projeto_passagem_aerea.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifsp.projeto_passagem_aerea.application.service.ReservaService;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		
		ReservaService reserva = new ReservaService();
		reserva.mainMenu();
	}

}
