package br.edu.ifsp.projeto_passagem_aerea.application.model;

public class Aviao extends Aeronave {

	public Passageiro[][] lugares;

	public Aviao(String modelo, int numFileiras, int totalAssentos) {
		this.lugares = new Passageiro[numFileiras][totalAssentos];
		this.setModelo(modelo);
	}

	public Passageiro getPassageiro(int x, int y) {
		return this.lugares[x][y];
	}

	public void setPassageiro(int x, int y, Passageiro passageiro) {
		this.lugares[x][y] = passageiro;
	}
	
	public Boolean verificaLugarOcupado(int x, int y) {
		return this.lugares[x][y] != null;
	}

}
