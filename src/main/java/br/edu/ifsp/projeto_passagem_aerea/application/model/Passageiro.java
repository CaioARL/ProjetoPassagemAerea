package br.edu.ifsp.projeto_passagem_aerea.application.model;

public class Passageiro{

	private String nome;
	private String cpf;
	
	public Passageiro() {
	}

	public Passageiro(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
	
}
