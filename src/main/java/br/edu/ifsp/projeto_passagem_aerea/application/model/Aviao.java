package br.edu.ifsp.projeto_passagem_aerea.application.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.util.ObjectUtils;

import br.edu.ifsp.projeto_passagem_aerea.application.service.ReservaService;

@SuppressWarnings("serial")
public class Aviao extends Aeronave implements Serializable{
	
	private Passageiro[][] lugares;
	
	public Aviao(String modelo, int numFileiras, int totalAssentos) {
		this.lugares = new Passageiro[numFileiras][totalAssentos];
		this.setModelo(modelo);
	}
	
	public Passageiro getPassageiro(int x, int y) throws Exception {
		try {
			return this.lugares[x-1][y-1];
		}catch (ArrayIndexOutOfBoundsException e) {
			ReservaService reservaService = new ReservaService();
			reservaService.errorOnTry("Posição não existe, por favor verique");
			throw new Exception("Posição incorreta:", e);
		}catch (Exception e) {
			throw new Exception("Erro no metodo get usuário:", e);
		}
	}

	public void setPassageiro(int x, int y, Passageiro passageiro) {
		this.lugares[x-1][y-1] = passageiro;
	}
	
	public Boolean verificaLugarOcupado(int x, int y) {
		return this.lugares[x][y] != null;
	}
	
	public Boolean verificaLugarPorCpf(String cpf) {
		for (Passageiro[] fileira : lugares) {
			for (Passageiro passageiro : fileira) {
				if (!ObjectUtils.isEmpty(passageiro) && Objects.equals(passageiro.getCpf(), cpf)) {
					return false;
				}
			}
		}
		return true;
	}

	public Passageiro[][] getLugares() {
		return lugares;
	}

}
