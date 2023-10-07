package br.edu.ifsp.projeto_passagem_aerea.application.model;

public class Voo {

	private Aviao aeronave;
	private int nro;
	private String data;
	private String hora;
	
	public Voo(Aviao aeronave, int nro, String data, String hora) {
		this.aeronave = aeronave;
		this.nro = nro;
		this.data = data;
		this.hora = hora;
	}

	public Aviao getAeronave() {
		return aeronave;
	}

	public int getNro() {
		return nro;
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}

	public void setAeronave(Aviao aeronave) {
		this.aeronave = aeronave;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
