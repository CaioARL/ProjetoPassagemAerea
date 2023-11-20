package br.edu.ifsp.projeto_passagem_aerea.application.model;

public class Voo{

	private Aviao aeronave;
	private Integer nro;
	private String data;
	private String hora;
	
	public Voo(Aviao aeronave, Integer nro, String data, String hora) {
		this.aeronave = aeronave;
		this.nro = nro;
		this.data = data;
		this.hora = hora;
	}

	public Aviao getAeronave() {
		return aeronave;
	}

	public void setAeronave(Aviao aeronave) {
		this.aeronave = aeronave;
	}

	public Integer getNro() {
		return nro;
	}

	public void setNro(Integer nro) {
		this.nro = nro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
