package br.edu.ifsp.projeto_passagem_aerea.application.service;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.util.ObjectUtils;

import br.edu.ifsp.projeto_passagem_aerea.application.model.Aeronave;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Aviao;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Passageiro;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Voo;
import io.micrometer.common.util.StringUtils;

@SuppressWarnings("serial")
public class ReservaService extends JFrame implements Serializable{
	private Aviao aviaoClass = new Aviao(null, 0, 0);
	private Aeronave[] aeronaves =  new Aeronave[10];
	private Aviao[] avioes = new Aviao[10];
	private Voo[] voos =  new Voo[10];
	private Passageiro passageiro = new Passageiro(null, null);
	private int contadorVoos = 0;
	private int contadorAeronave = 0;

	private void inicializaArrays() {
		this.aeronaves[0] = new Aeronave(null);
		this.avioes[0] = new Aviao(null, 0, 0);
		this.voos[0] = new Voo(new Aviao(null, 0, 0), 0, null, null);
	}
	
	public void mainMenu(){
		inicializaArrays();
		JFrame jframe = getFrame("Menu Principal");

		// Parâmetros
		JButton jButtonParametros = getButton("Parâmetros do sistema", 100);

		// Reserva
		JButton jButtonReserva = getButton("Reserva de Passagens", 200);

		// Sair
		JButton jButtonSair = getButton("Sair", 300);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonParametros);
		jframe.add(jButtonReserva);
		jframe.add(jButtonSair);

		// add funciotions
		jButtonParametros.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "parametro");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonReserva.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "reserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonSair.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "sair");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void parametroMenu(){
		JFrame jframe = getFrame("Parâmetros do Sistema");

		// Aeronave
		JButton jButtonAerovane = getButton("Cadastrar Aeronave", 100);

		// Voo
		JButton jButtonVoo = getButton("Cadastrar Voo", 200);

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 300);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonAerovane);
		jframe.add(jButtonVoo);
		jframe.add(jButtonVoltar);

		// add functions
		jButtonAerovane.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "aeronave");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonVoo.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voo");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "main");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	private void reservaMenu() {
		JFrame jframe = getFrame("Reserva de Passagens");

		// Reserva
		JButton jButtonReserva = getButton("Fazer Reserva", 50);

		// Lugares vazios
		JButton jButtonVazio = getButton("Consultar Lugares Vazios", 150);

		// Reservas realizadas
		JButton jButtonRealizadas = getButton("Consultar Reservas Realizadas", 250);

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 350);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonReserva);
		jframe.add(jButtonVazio);
		jframe.add(jButtonRealizadas);
		jframe.add(jButtonVoltar);

		// add functions
		jButtonReserva.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "doReserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonVazio.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "vazio");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonRealizadas.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "realizadas");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "main");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	private void aeronaveMenu() throws Exception{
		
		JFrame jframe = getFrame("Cadastro Aeronave");

	    List<String> titles = new ArrayList<>();
	    titles.add("Aeronaves cadastradas");
		titles.add("Modelo aeronave: ");
		titles.add("Numero de fileiras");
		titles.add("Numero total de assentos");
		
		int cont = contadorAeronave;
		if(contadorAeronave < 1) {
			cont++;
		}
		
	    JPanel modeloPanel = getText(titles, 100, this.aeronaves[cont-1], this.avioes[cont-1]);
	    List<JTextField> modelosText = new ArrayList<>();
	    for (int i = 0; i < titles.size(); i++) {
	        modelosText.add((JTextField) modeloPanel.getComponent(i * 2 + 1));
	    }

	    // Voltar
	    JButton jButtonVoltar = getButton("Voltar", 500);

	    // Salvar
	    JButton jButtonSalvar = getButton("Salvar", 400);

	    // add buttons
	    jframe.setLayout(null);
	    jframe.add(modeloPanel);
	    jframe.add(jButtonVoltar);
	    jframe.add(jButtonSalvar);

	    // add functions
	    jButtonVoltar.addActionListener(action -> {
	        try {
				this.redirect(action, jframe, "voltar", "parametros");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    });

	    
		jButtonSalvar.addActionListener(action -> {
			String modelo = modelosText.get(1).getText();
			int nroFileira = Integer.parseInt(modelosText.get(2).getText());
			int totalAssentos = Integer.parseInt(modelosText.get(3).getText());

			try {
				this.saveAviao(jframe, new Aviao(modelo, nroFileira, totalAssentos));
				redirect(action, jframe, "voltar", "parametros");
			} catch (NumberFormatException e) {
				throw new Error("Erro ao inserir valores, por favor verifique os tipos: ", e);
			}catch (Exception e) {
				e.printStackTrace();
			}
		});
	    
	}

	private void vooMenu() throws Exception{
		JPanel modeloPanel = new JPanel();
		List<JTextField> modelosText = new ArrayList<>();
		
		int cont = contadorVoos;
		JFrame jframe = getFrame("Cadastro voo");
		
		List<String> titles = new ArrayList<>();
		titles.add("Aeronaves cadastradas: ");
		titles.add("Modelo do avião: ");
		titles.add("Insira aqui o numero do voo: ");
	    titles.add("Insira aqui a data do voo: ");
	    titles.add("Insira aqui a hora do voo: ");
	    
		if(contadorVoos < 1) {
			cont++;
		}
	    
		try {
			modeloPanel = getText(titles, 50, this.voos[cont-1]);
			for (int i = 0; i < titles.size(); i++) {
		        modelosText.add((JTextField) modeloPanel.getComponent(i * 2 + 1));
		    }
		}catch (Exception e) {
			jframe.dispose();
		}

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 500);
		
		// Salvar
		JButton jButtonSalvar = getButton("Salvar", 400);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonVoltar);
		jframe.add(jButtonSalvar);
		jframe.add(modeloPanel);

		// add functions
		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "parametros");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		jButtonSalvar.addActionListener(action -> {
			try {
				Aviao aviao = getByAeronave(new Aeronave(modelosText.get(0).getText()));
				int numero = Integer.parseInt(modelosText.get(1).getText());
				String data = modelosText.get(2).getText();
				String hora = modelosText.get(3).getText();
				if(!ObjectUtils.isEmpty(aviao)) {
					this.saveVoo(jframe, new Voo(aviao ,numero, data, hora));
				}else {
					error("Aeronave não encontrada", true, "parametros");
					throw new NullPointerException();
				}
			} catch (NumberFormatException e) {
				throw new Error("Erro ao inserir valores, por favor verifique os tipos: ", e);
			} catch(NullPointerException e) {
				jframe.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	private void doReservaMenu() throws Exception{
		JFrame jframe = getFrame("Fazer Reserva");
		
		// Reserva
		List<String> titles = new ArrayList<String>();
		titles.add("Voos cadastrados: ");
		titles.add("Nome: ");
	    titles.add("CPF: ");
	    titles.add("Código voo: ");
	    titles.add("número fileira: ");
	    titles.add("número assento: ");
	    JPanel modeloPanel = getText(titles, 50, this.passageiro);
	    List<JTextField> modelosText = new ArrayList<>();
	    for (int i = 0; i < titles.size(); i++) {
	        modelosText.add((JTextField) modeloPanel.getComponent(i * 2 + 1));
	    }

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 500);
		
		// Salvar
		JButton jButtonSalvar = getButton("Salvar", 400);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonVoltar);
		jframe.add(jButtonSalvar);
		jframe.add(modeloPanel);

		// add functions
		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "reserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		jButtonSalvar.addActionListener(action -> {
			try {
				String nome = modelosText.get(1).getText();
				String cpf = modelosText.get(2).getText();
				int nroVoo = Integer.parseInt(modelosText.get(3).getText());
				int nroFileira = Integer.parseInt(modelosText.get(4).getText());
				int nroAssento = Integer.parseInt(modelosText.get(5).getText());
				
				if(ObjectUtils.isEmpty(this.aviaoClass.getPassageiro(nroFileira, nroAssento))) {
					Aviao aviaoEscolhido = getByVoo(new Voo(new Aviao(null, 0, 0), nroVoo, null, null));
					int vooPosition = getVooByPosition(nroVoo);
					if(validateLugar(aviaoEscolhido, nroFileira, nroAssento)) {
						this.savePassageiro(vooPosition, nome, cpf, nroFileira, nroAssento);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void vazioMenu(){
		JFrame jframe = getFrame("Consultar lugares vazios");

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 350);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonVoltar);

		// add functions
		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "reserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void realizadasMenu(){
		JFrame jframe = getFrame("Consultar reservas realizadas");

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 350);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonVoltar);

		// add functions
		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "reserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private JButton getButton(String title, int posicao) {
		JButton button = new JButton(title);
		button.setBounds(250, posicao, 300, 50);
		return button;
	}
	
	private JPanel getText(List<String> titles, int posicao, Aeronave aeronave, Aviao aviao) throws Exception {
		return this.getText(titles, posicao, aeronave, null, aviao, null);
	}
	
	private JPanel getText(List<String> titles, int posicao, Voo voo) throws Exception {
		return this.getText(titles, posicao, null, voo, null, null);
	}
	
	private JPanel getText(List<String> titles, int posicao, Passageiro passageiro) throws Exception {
		return this.getText(titles, posicao, null, null, null, passageiro);
	}

	private JPanel getText(List<String> titles, int posicao, Aeronave aeronave, 
			Voo voo, Aviao aviao, Passageiro passageiro) throws Exception{
		JPanel panel = new JPanel();
		
		List<JTextField> textFields = new ArrayList<>();
		if(!ObjectUtils.isEmpty(aviao)) {	
			JTextField aeronavesExistentes = listAeronavesCadastradas();
			textFields.add(aeronavesExistentes);
			textFields.add(new JTextField(aeronave.getModelo()));
			textFields.add(new JTextField(String.valueOf(aviao.lugares.length)));
			int columns = 0;
	        if (aviao.lugares.length > 0 && aviao.lugares[0] != null) {
	            columns = aviao.lugares[0].length;
	        }
			textFields.add(new JTextField(String.valueOf(columns)));
		}
		if(!ObjectUtils.isEmpty(voo)) {
			JTextField aeronavesExistentes = listAeronavesCadastradas();
			textFields.add(aeronavesExistentes);
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
		}
		if(!ObjectUtils.isEmpty(passageiro)) {
			JTextField voosExistentes = listVooCadastradas();
			textFields.add(voosExistentes);
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
		}

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for (int i=0; i< textFields.size(); i++) {
			panel.add(new Label(titles.get(i)));
			panel.add(textFields.get(i));
			int teste =  (int) (230 * (1.05 + ((double) textFields.size()/100)));
			panel.setBounds(250, posicao, 300, teste);
			posicao += 5;
		}

		return panel;
	}

	private JFrame getFrame(String title) {
		JFrame jframe = new JFrame();
		jframe.setTitle(title);
		jframe.setVisible(true);
		jframe.setSize(800, 600);
		jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		return jframe;
	}

	private void redirect(ActionEvent e, JFrame jframe, String to) throws Exception {
		this.redirect(e, jframe, to, null);
	}

	private void redirect(ActionEvent e, JFrame jframe, String to, String backTo) throws Exception {
		jframe.setVisible(false);
		switch (to) {
		case "reserva": {
			reservaMenu();
			break;
		}
		case "parametro": {
			parametroMenu();
			break;
		}
		case "aeronave": {
			aeronaveMenu();
			break;
		}
		case "voo": {
			vooMenu();
			break;
		}
		case "doReserva": {
			doReservaMenu();
			break;
		}
		case "vazio": {
			vazioMenu();
			break;
		}
		case "realizadas": {
			realizadasMenu();
			break;
		}
		case "sair": {
			jframe.dispose();
			break;
		}
		case "voltar": {
			backTo(backTo);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + to);
		}
	}

	private void backTo(String backTo) throws Exception {

		switch (backTo) {
		case "main": {
			mainMenu();
			break;
		}
		case "parametros": {
			parametroMenu();
			break;
		}
		case "reserva": {
			reservaMenu();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + backTo);
		}

	}
	
	private void saveAviao(JFrame jFrame, Aviao newAviao) throws Exception {
		try {
			if(Boolean.TRUE.equals(validadeBeforeSave(newAviao))) {
				this.avioes[contadorAeronave] = newAviao;
				this.aeronaves[contadorAeronave] = new Aeronave(newAviao.getModelo());
				this.contadorAeronave++;
				success(jFrame, "Aviao salva com sucesso");
			}else {
				error("Aviao ja cadastrado");
			}
			
		}catch (Exception e) {
			throw new Exception("Erro no método saveAviao: ", e);
		}
		
	}
	
	private void saveVoo(JFrame jFrame, Voo newVoo) throws Exception {
		try {
			if(Boolean.TRUE.equals(validadeBeforeSave(newVoo))) {
				this.voos[contadorVoos] = newVoo;
				this.contadorVoos++;
				success(jFrame, "Voo salvo com sucesso");
			}else {
				error("Voo ja existente");
			}
			
		}catch (ArrayIndexOutOfBoundsException e) {
			error("Limite de voos cadastrados atingido");
		}
		catch (Exception e) {
			throw new Exception("Erro no método saveVoo: ", e);
		}
		
	}
	
	private void savePassageiro(JFrame jFrame, Passageiro newPassageiro, Voo newVoo) {
		try {
			if(validadeBeforeSave(newVoo)) {
				this.passageiro = newPassageiro;
			}
		} catch (Exception e) {
			
		}
	}
	
	private void success(JFrame jFrame, String mensagem) {
	    JOptionPane.showMessageDialog(jFrame, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void error(String mensagem) throws Exception {
		this.error(mensagem, false, null);
	}
	
	private void error(String mensagem, Boolean redirect, String to) throws Exception {
		JFrame jFrame = new JFrame();
	    JOptionPane.showMessageDialog(jFrame, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	    if(redirect) {
	    	redirect(null, jFrame, "voltar", to);
	    }
	}

	private Aviao getByAeronave(Aeronave aeronave) {
		for (Aviao aviao : this.avioes) {
			if (!ObjectUtils.isEmpty(aviao) && Objects.equals(aviao.getModelo(), aeronave.getModelo())) {
				return aviao;
			}
		}
		return null;
	}
	
	private Aviao getByVoo(Voo voo) {
		for (Voo vooSaved : this.voos) {
			if (!ObjectUtils.isEmpty(vooSaved) && Objects.equals(vooSaved.getNro(), vooSaved.getNro())) {
				return vooSaved.getAeronave();
			}
		}
		return null;
	}
	
	private int getVooByPosition(int nroVoo) {
		int cont = 0;
		for (Voo vooSaved : this.voos) {
			if(!ObjectUtils.isEmpty(vooSaved) && vooSaved.getNro() == nroVoo) {
				return cont;
			}
			cont++;
		}
		return (Integer) null;
	}
	
	private Boolean validadeBeforeSave(Voo voo) {
		return this.validadeBeforeSave(voo, null, null);
	}
	
	private Boolean validadeBeforeSave(Aviao aviao) {
		return this.validadeBeforeSave(null, aviao, null);
	}
	
	private Boolean validadeBeforeSave(Passageiro passageiro) {
		return this.validadeBeforeSave(null, null, null);
	}
	
	private Boolean validadeBeforeSave(Voo voo, Aviao aviao, Passageiro passageiro) {
		if(!ObjectUtils.isEmpty(voo)) {
			for (Voo voosFromList : this.voos) {
				if(!ObjectUtils.isEmpty(voosFromList) && Objects.equals(voosFromList.getNro(), voo.getNro())) {
					return false;
				}
			}
		}
		if(!ObjectUtils.isEmpty(aviao)) {
			for (Aviao avioesFromList : this.avioes) {
				if(!ObjectUtils.isEmpty(avioesFromList) && Objects.equals(avioesFromList.getModelo(), aviao.getModelo())) {
					return false;
				}
			}
		}
		return true;
	}
	
	private JTextField listAeronavesCadastradas() {
		String aeronavesCadastradas = "";

		for (Aeronave aeronaveSaved : this.aeronaves) {
			if (!ObjectUtils.isEmpty(aeronaveSaved) && aeronaveSaved.getModelo() != null) {
				aeronavesCadastradas += aeronaveSaved.getModelo() + "; ";
			}
		}
		if (aeronavesCadastradas.isEmpty()) {
			aeronavesCadastradas = "Não há aeronaves cadastradas";
		}
		JTextField aeronavesExistentes = new JTextField(aeronavesCadastradas);
		aeronavesExistentes.setEditable(false);
		return aeronavesExistentes;
	}
	
	private JTextField listVooCadastradas() {
		String voosCadastradas = "";

		for (Voo vooSaved : this.voos) {
			if (!ObjectUtils.isEmpty(vooSaved) && vooSaved.getNro() != 0) {
				voosCadastradas += vooSaved.getNro() + "; ";
			}
		}
		if (voosCadastradas.isEmpty()) {
			voosCadastradas = "Não há voos cadastradas";
		}
		JTextField aeronavesExistentes = new JTextField(voosCadastradas);
		aeronavesExistentes.setEditable(false);
		return aeronavesExistentes;
	}
}
