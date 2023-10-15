package br.edu.ifsp.projeto_passagem_aerea.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifsp.projeto_passagem_aerea.application.model.Frame;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		
		Frame frame = new Frame();
		frame.mainMenu();
		
		
		System.out.println("Tchau");
	}

}
