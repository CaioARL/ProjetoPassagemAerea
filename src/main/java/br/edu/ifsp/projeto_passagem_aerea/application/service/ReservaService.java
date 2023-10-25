package br.edu.ifsp.projeto_passagem_aerea.application.service;

import java.awt.Color;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class ReservaService extends JFrame implements Serializable{
	private Aeronave[] aeronaves =  new Aeronave[10];
	private Aviao[] avioes = new Aviao[10];
	private Voo[] voos =  new Voo[10];
	private Passageiro passageiro = new Passageiro(null, null);
	private int contadorVoos = 0;
	private int contadorAeronave = 0;

	private void inicializaArrays() {
		this.aeronaves[0] = new Aeronave(null);
		this.avioes[0] = new Aviao(null, 0, 0);
		this.voos[0] = new Voo(new Aviao(null, 0, 0), null, null, null);
	}
	
	public void mainMenu(){
		if(contadorAeronave==0 && contadorVoos==0) {
			inicializaArrays();
		}
		
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
				jframe.dispose();
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
		JButton jButtonReserva = getButton("Fazer Reserva", 100);
		
		// Lugares
		JButton jButtonLugares = getButton("Verificar Lugares", 200);

		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 300);

		// add buttons
		jframe.setLayout(null);
		jframe.add(jButtonReserva);
		jframe.add(jButtonLugares);
		jframe.add(jButtonVoltar);

		// add functions
		jButtonReserva.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "doReserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		jButtonLugares.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "lugares");
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
		titles.add("Modelo aeronave:");
		titles.add("Numero de fileiras:");
		titles.add("Numero total de assentos por fileira:");
		
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
			int fileiras = Integer.parseInt(modelosText.get(2).getText());
			int assentosPorFileira = Integer.parseInt(modelosText.get(3).getText());
			String validaAssentos = validaAssentos(fileiras, assentosPorFileira);
			
			try {
				if(validaAssentos == null) {
					this.saveAviao(jframe, new Aviao(modelo, fileiras, assentosPorFileira));
				}else {
					error(validaAssentos);
				}
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
		titles.add("Voos cadastrados: ");
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
				Aviao aviao = getByAeronave(new Aeronave(modelosText.get(2).getText()));
				int numero = Integer.parseInt(modelosText.get(3).getText());
				String data = modelosText.get(4).getText();
				String hora = modelosText.get(5).getText();
				if(!ObjectUtils.isEmpty(aviao)) {
					this.saveVoo(jframe, new Voo(aviao ,numero, data, hora));
				}else {
					error("Aeronave não encontrada");
				}
			} catch (NumberFormatException e) {
				throw new Error("Erro ao inserir valores, por favor verifique os tipos: ", e);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	private void doReservaMenu() throws Exception{
		JFrame jframe = getFrame("Fazer Reserva");
		
		// Reserva
		List<String> titles = new ArrayList<>();
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
				
				this.passageiro = new Passageiro(nome, cpf);
				int posicaoAviao = getPosicaoAviaoByVoo(loadVoo(nroVoo));
				
				if(ObjectUtils.isEmpty(avioes[posicaoAviao].getPassageiro(nroFileira, nroAssento))) {
					savePassageiro(jframe, passageiro, posicaoAviao, nroFileira, nroAssento);
				}else {
					error("Lugar ja se encontra ocupado");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void lugaresMenu() throws Exception{
		JFrame jframe = getFrame("Consultar lugares", 800);
		
		// Painel entrada
	    JPanel modeloPanel = new JPanel();
	    modeloPanel.add(new Label("Numero voo"));
	    modeloPanel.add(new JTextField());

	    modeloPanel.setLayout(new BoxLayout(modeloPanel, BoxLayout.Y_AXIS));
	    modeloPanel.setBounds(250, 50, 300, 50);
	    
	    // Painel lugares
	    JPanel matrizPanel = new JPanel();
	    Label titulo = new Label("Preencha o numero do voo e selecione pesquisar para visualizar os lugares");
	    titulo.setForeground(new Color(255,0,0));
		matrizPanel.add(titulo);
		matrizPanel.setBounds(100, 100, 600, 50);
	    
	    
	    // Pesquisar
	 	JButton jButtonPesquisar = getButton("Pesquisar", 600);
	    
		// Voltar
		JButton jButtonVoltar = getButton("Voltar", 700);

		// add buttons
		jframe.setLayout(null);
		jframe.add(matrizPanel);
		jframe.add(modeloPanel);
		jframe.add(jButtonPesquisar);
		jframe.add(jButtonVoltar);

		// add functions
		jButtonVoltar.addActionListener(action -> {
			try {
				this.redirect(action, jframe, "voltar", "reserva");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		jButtonPesquisar.addActionListener(action -> {
			try {
				matrizPanel.removeAll();
				matrizPanel.setBounds(0, 120, 800, 400);
				matrizPanel.revalidate();
				
				JTextField nroVoo = (JTextField)modeloPanel.getComponent(1);
				Voo voo = loadVoo(Integer.parseInt(nroVoo.getText()));
				
				for (Passageiro[] fileiras : voo.getAeronave().getLugares()) {
					for (Passageiro lugar : fileiras) {
						if(!ObjectUtils.isEmpty(lugar) && lugar.getCpf()!= null) {
							JButton button = new JButton();
							button.setBackground(new Color(0, 255, 0));
							button.setPreferredSize(new Dimension(12, 12));
							matrizPanel.add(button);
						}else {
							JButton button = new JButton();
							button.setBackground(new Color(255, 0, 0));
							button.setPreferredSize(new Dimension(12, 12));
							matrizPanel.add(button);
						}
						matrizPanel.add(new Label());
					}
					matrizPanel.revalidate();
				}
				
			}catch (NumberFormatException e) {
				errorOnTry("Valor não inserido");

			} catch (Exception e) {
				throw new Error("Erro no action Pesquisar: ", e);
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
			textFields.add(new JTextField(String.valueOf(aviao.getLugares().length)));
			int columns = 0;
	        if (aviao.getLugares().length > 0 && aviao.getLugares()[0] != null) {
	            columns = aviao.getLugares()[0].length;
	        }
			textFields.add(new JTextField(String.valueOf(columns)));
		}
		if(!ObjectUtils.isEmpty(voo)) {
			JTextField voosExistentes = listVooCadastradas();
			JTextField aeronavesExistentes = listAeronavesCadastradas();
			textFields.add(voosExistentes);
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
		
		int tamanhoLista = textFields.size();
		for (int i = 0; i < tamanhoLista; i++) {
			panel.add(new Label(titles.get(i)));
			panel.add(textFields.get(i));
			panel.setBounds(250, posicao, 300, (int) (230 * (1.06 + ((double) tamanhoLista / 100))));
			posicao += 5;
		}

		return panel;
	}

	private JFrame getFrame(String title) {
		return this.getFrame(title, 600);
	}
	
	private JFrame getFrame(String title, Integer altura) {
		Integer height = altura;
		JFrame jframe = new JFrame();
		jframe.setTitle(title);
		jframe.setVisible(true);
		jframe.setSize(800, height);
		jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		return jframe;
	}

	private void redirect(ActionEvent e, JFrame jframe, String to) throws Exception {
		this.redirect(e, jframe, to, null);
	}

	private void redirect(ActionEvent e, JFrame jframe, String to, String backTo) throws Exception {
		jframe.dispose();
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
		case "lugares": {
			lugaresMenu();
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
				success(jFrame, "Aviao salva com sucesso", "aeronave");
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
				success(jFrame, "Voo salvo com sucesso", "voo");
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
	
	private void savePassageiro(JFrame jFrame, Passageiro newPassageiro, Integer posicaoVoo, int fileira, int assento) throws Exception {
		try {
			if(Boolean.TRUE.equals(validadeBeforeSave(this.avioes[posicaoVoo], newPassageiro))) {
				this.avioes[posicaoVoo].setPassageiro(fileira, assento, newPassageiro);
				success(jFrame, "Passageiro registrado com sucesso", "doReserva");
			}else {
				error("Somente um cadastro por CPF");
			}
		} catch (Exception e) {
			throw new Exception("Erro no método savePassageiro: ", e);
		}
	}
	
	private void success(JFrame jFrame, String mensagem, String to) throws Exception {
		this.success(jFrame, mensagem, false, to, true);
	}
	
	private void success(JFrame jFrame, String mensagem, Boolean redirect, String to, Boolean reload) throws Exception {
	    JOptionPane.showMessageDialog(jFrame, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    if(Boolean.TRUE.equals(redirect)) {
	    	redirect(null, jFrame, "voltar", to);
	    }
	    if(Boolean.TRUE.equals(reload)) {
	    	redirect(null, jFrame, to);
	    	jFrame.dispose();
	    }
	}
	
	public void error(String mensagem) throws Exception {
		this.error(mensagem, false, null);
	}
	
	public void errorOnTry(String mensagem){
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	private void error(String mensagem, Boolean redirect, String to) throws Exception {
		JFrame jFrame = new JFrame();
	    JOptionPane.showMessageDialog(jFrame, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	    if(Boolean.TRUE.equals(redirect)) {
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
	
	private Integer getPosicaoAviaoByVoo(Voo voo) {
		int cont = 0;

		for (Aviao aviaoSaved : this.avioes) {
			if (Objects.equals(voo.getAeronave().getModelo(), aviaoSaved.getModelo())) {
				return cont;
			}
			cont++;
		}
		return null;
	}
	
	private Voo loadVoo(int nroVoo) throws Exception {
		try {
			for (Voo vooSaved : this.voos) {
				if (vooSaved.getNro() == nroVoo) {
					return vooSaved;
				}
			}

		} catch (Exception e) {
			error("Voo não encontrado");
		}
		return null;
	}
	
	private Boolean validadeBeforeSave(Voo voo) {
		return this.validadeBeforeSave(voo, null, null);
	}
	
	private Boolean validadeBeforeSave(Aviao aviao) {
		return this.validadeBeforeSave(null, aviao, null);
	}
	
	private Boolean validadeBeforeSave(Aviao aviao, Passageiro passageiro) {
		return this.validadeBeforeSave(null, aviao, passageiro);
	}
	
	private Boolean validadeBeforeSave(Voo voo, Aviao aviao, Passageiro passageiro) {
		if (!ObjectUtils.isEmpty(voo)) {
			for (Voo voosFromList : this.voos) {
				if (!ObjectUtils.isEmpty(voosFromList) && Objects.equals(voosFromList.getNro(), voo.getNro())) {
					return false;
				}
			}
		}
		if (!ObjectUtils.isEmpty(aviao) && ObjectUtils.isEmpty(passageiro)) {
			for (Aviao avioesFromList : this.avioes) {
				if (!ObjectUtils.isEmpty(avioesFromList)
						&& Objects.equals(avioesFromList.getModelo(), aviao.getModelo())) {
					return false;
				}
			}
		}

		if (!ObjectUtils.isEmpty(passageiro)) {
			return aviao.verificaLugarPorCpf(passageiro.getCpf());
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
		aeronavesExistentes.setFocusable(false);
		return aeronavesExistentes;
	}
	
	private JTextField listVooCadastradas() {
		String voosCadastradas = "";

		for (Voo vooSaved : this.voos) {
			if (!ObjectUtils.isEmpty(vooSaved) && vooSaved.getNro() != null) {
				voosCadastradas += vooSaved.getNro() + "; ";
			}
		}
		if (voosCadastradas.isEmpty()) {
			voosCadastradas = "Não há voos cadastrados";
		}
		JTextField aeronavesExistentes = new JTextField(voosCadastradas);
		aeronavesExistentes.setEditable(false);
		aeronavesExistentes.setFocusable(false);
		return aeronavesExistentes;
	}
	
	private String validaAssentos(int fileiras, int assentosPorFileira) {
		if(assentosPorFileira > 6) {
			return "Número maximo de 6 assentos por fileira";
		}if(fileiras > 30) {
			return "Número maximo de 30 fileiras";
		}
		else {
			return null;
		}
	}
}
