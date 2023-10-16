package br.edu.ifsp.projeto_passagem_aerea.application.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Aeronave implements Serializable{

	private String modelo;
	
	public Aeronave() {
		
	}
	
	public Aeronave(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
}
