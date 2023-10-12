package br.edu.ifsp.projeto_passagem_aerea.application.service;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ifsp.projeto_passagem_aerea.application.model.Aviao;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Voo;

public class VooService {	
Logger logger = LoggerFactory.getLogger(VooService.class);

	public Voo[] cadastro() {

		Voo[] voos = new Voo[10];

		for (int i = 0; i < voos.length; i++ ) {
			try {
				String data = JOptionPane.showInputDialog("Data:");
				String hora = JOptionPane.showInputDialog("Hora:");
				int numero = Integer.parseInt(JOptionPane.showInputDialog("Numero do voo:"));

				String modelo = JOptionPane.showInputDialog("Modelo:");
				int numFileiras = Integer.parseInt(JOptionPane.showInputDialog("Numero de fileiras:"));
				int numAssentos = Integer.parseInt(JOptionPane.showInputDialog("Numero de assentos:"));

				Aviao aeronave = new Aviao(modelo, numFileiras, numAssentos);

				voos[i] = new Voo(aeronave, numero, data, hora);

			} catch (NumberFormatException e) {
				logger.error("Por favor, insira valores corretamente");
				e.printStackTrace();
				break;
			}
		}
		
		return voos;

	}

}
