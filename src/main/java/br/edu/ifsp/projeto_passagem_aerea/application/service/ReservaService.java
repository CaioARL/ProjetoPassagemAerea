package br.edu.ifsp.projeto_passagem_aerea.application.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.springframework.util.ObjectUtils;

import br.edu.ifsp.projeto_passagem_aerea.application.model.Aeronave;
import br.edu.ifsp.projeto_passagem_aerea.application.model.AssentoDTO;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Aviao;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Passageiro;
import br.edu.ifsp.projeto_passagem_aerea.application.model.Voo;

public class ReservaService {
	private Aeronave[] aeronaves = new Aeronave[10];
	private Aviao[] avioes = new Aviao[10];
	private Voo[] voos = new Voo[10];
	private Passageiro passageiro = new Passageiro(null, null);
	private int contadorVoos = 0;
	private int contadorAeronave = 0;

	private void inicializaArrays() {
		this.aeronaves[0] = new Aeronave(null);
		this.avioes[0] = new Aviao(null, 0, 0);
		this.voos[0] = new Voo(new Aviao(null, 0, 0), null, null, null);
	}

	public void mainMenu() {
		if (contadorAeronave == 0 && contadorVoos == 0) {
			inicializaArrays();
		}

		JFrame jframe = getFrame("Menu Principal");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

	private void parametroMenu() {
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

	private void aeronaveMenu() throws Exception {

		JFrame jframe = getFrame("Cadastro Aeronave");

		List<String> titles = new ArrayList<>();
		titles.add("Aeronaves cadastradas");
		titles.add("Modelo aeronave:");
		titles.add("Numero de fileiras:");
		titles.add("Numero total de assentos por fileira:");

		int cont = contadorAeronave;
		if (contadorAeronave < 1) {
			cont++;
		}

		JPanel modeloPanel = getText(titles, 100, this.aeronaves[cont - 1], this.avioes[cont - 1]);
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
			try {
				String modelo = modelosText.get(1).getText();
				int fileiras = Integer.parseInt(modelosText.get(2).getText());
				int assentosPorFileira = Integer.parseInt(modelosText.get(3).getText());
				String validaAssentos = validaAssentos(fileiras, assentosPorFileira);
				if (validaAssentos == null) {
					this.saveAviao(jframe, new Aviao(modelo, fileiras, assentosPorFileira));
				} else {
					error(validaAssentos);
				}
			} catch (NumberFormatException e) {
				try {
					error("Erro ao inserir valores, por favor verifique os tipos");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	private void vooMenu() throws Exception {
		JPanel modeloPanel = new JPanel();
		List<JTextField> modelosText = new ArrayList<>();

		int cont = contadorVoos;
		JFrame jframe = getFrame("Cadastro voo");

		List<String> titles = new ArrayList<>();
		titles.add("Voos cadastrados: ");
		titles.add("Aeronaves cadastradas: ");
		titles.add("Modelo do avião: ");
		titles.add("Insira aqui o número do voo: ");
		titles.add("Insira aqui a data do voo: ");
		titles.add("Insira aqui a hora do voo: ");

		if (contadorVoos < 1) {
			cont++;
		}

		try {
			modeloPanel = getText(titles, 50, this.voos[cont - 1]);
			for (int i = 0; i < titles.size(); i++) {
				modelosText.add((JTextField) modeloPanel.getComponent(i * 2 + 1));
			}
		} catch (Exception e) {
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

				Boolean isValidDate = validarDataHora(data, hora);

				if (!ObjectUtils.isEmpty(aviao) && Boolean.TRUE.equals(isValidDate)) {
					this.saveVoo(jframe, new Voo(aviao, numero, data, hora));
				} else if (Boolean.FALSE.equals(isValidDate)) {
					error("Data e/ou hora inválida");
				} else {
					error("Aeronave não encontrada");
				}
			} catch (NumberFormatException e) {
				throw new Error("Erro ao inserir valores, por favor verifique os tipos: ", e);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	private void doReservaMenu(AssentoDTO assento) throws Exception {
		JFrame jframe = getFrame("Fazer Reserva");

		// Reserva
		List<String> titles = new ArrayList<>();
		titles.add("Voos cadastrados: ");
		titles.add("Nome: ");
		titles.add("CPF: ");
		titles.add("Código voo: ");
		titles.add("número fileira: ");
		titles.add("número assento: ");
		JPanel modeloPanel = getText(titles, 50, this.passageiro, assento);
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

				boolean isCPFValid = validarCPF(cpf);

				int nroVoo = Integer.parseInt(modelosText.get(3).getText());
				int nroFileira = Integer.parseInt(modelosText.get(4).getText());
				int nroAssento = Integer.parseInt(modelosText.get(5).getText());

				this.passageiro = new Passageiro(nome, cpf);
				int posicaoVoo = getPosicaoVoo(nroVoo);
				Voo voo = loadVoo(nroVoo);

				if (ObjectUtils.isEmpty(voo.getAeronave().getPassageiro(nroFileira, nroAssento)) && isCPFValid) {
					savePassageiro(jframe, passageiro, posicaoVoo, nroFileira, nroAssento);
				} else if (Boolean.FALSE.equals(isCPFValid)) {
					error("CPF inválido, não inclua pontos e traços");
				}
				else {
					error("Lugar ja se encontra ocupado");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void lugaresMenu() throws Exception {
		JFrame jframe = getFrame("Consultar lugares", 800, 800);

		// Painel entrada
		JPanel modeloPanel = new JPanel();
		modeloPanel.add(new Label("Numero voo"));
		modeloPanel.add(new JTextField());

		modeloPanel.setLayout(new BoxLayout(modeloPanel, BoxLayout.Y_AXIS));
		modeloPanel.setBounds(250, 50, 300, 50);

		// Painel lugares
		JPanel matrizPanel = new JPanel();
		Label titulo = new Label("Preencha o numero do voo e selecione pesquisar para visualizar os lugares");
		titulo.setForeground(new Color(255, 0, 0));
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
				matrizPanel.setBounds(50, 150, 700, 400);
				matrizPanel.revalidate();

				JTextField nroVoo = (JTextField) modeloPanel.getComponent(1);
				Voo voo = loadVoo(Integer.parseInt(nroVoo.getText()));
				Passageiro[][] lugares = voo.getAeronave().getLugares();

				for (int i = 0; i < lugares.length; i++) {
					for (int j = 0; j < lugares[i].length; j++) {
						int assento = j + 1;
						int fileira = i + 1;
						Passageiro lugar = lugares[i][j];
						JButton button = new JButton();
						if (lugar != null && lugar.getCpf() != null) {
							button.setBackground(new Color(0, 255, 0));
							button.addActionListener(action2 -> {
								try {
									message("Lugar Reservado para o CPF: " + lugar.getCpf());
								} catch (Exception e) {
									e.printStackTrace();
								}
							});
						} else {
							button.setBackground(new Color(255, 0, 0));
							button.addActionListener(action2 -> {
								try {
									message("Assento " + assento + " na fileira " + fileira + " encontra-se vazio",
											jframe, new AssentoDTO(voo.getNro(), fileira, assento));
								} catch (Exception e) {
									e.printStackTrace();
								}
							});
						}
						button.setPreferredSize(new Dimension(30, 30));
						matrizPanel.add(button);
					}
				}
				matrizPanel.revalidate();

			} catch (NumberFormatException e) {
				errorOnTry("Valor não inserido");

			} catch (Exception e) {
				throw new Error("Erro no action Pesquisar: ", e);
			}
		});
	}

	private JButton getButton(String title, int posicao) {
		return this.getButton(title, new Rectangle(250, posicao, 300, 50));
	}

	private JButton getButton(String title, Rectangle rectangle) {
		JButton button = new JButton(title);
		if (rectangle != null) {
			button.setBounds(rectangle);
		}

		return button;
	}

	private JPanel getText(List<String> titles, int posicao, Aeronave aeronave, Aviao aviao) throws Exception {
		return this.getText(titles, posicao, aeronave, null, aviao, null, null);
	}

	private JPanel getText(List<String> titles, int posicao, Voo voo) throws Exception {
		return this.getText(titles, posicao, null, voo, null, null, null);
	}

	private JPanel getText(List<String> titles, int posicao, Passageiro passageiro, AssentoDTO assento)
			throws Exception {
		return this.getText(titles, posicao, null, null, null, passageiro, assento);
	}

	private JPanel getText(List<String> titles, int posicao, Aeronave aeronave,
			Voo voo, Aviao aviao, Passageiro passageiro, AssentoDTO assento) throws Exception {
		JPanel panel = new JPanel();

		List<JTextField> textFields = new ArrayList<>();
		if (!ObjectUtils.isEmpty(aviao)) {
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
		if (!ObjectUtils.isEmpty(voo)) {
			JTextField voosExistentes = listVooCadastradas();
			JTextField aeronavesExistentes = listAeronavesCadastradas();
			textFields.add(voosExistentes);
			textFields.add(aeronavesExistentes);
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			textFields.add(new JTextField());
		}
		if (!ObjectUtils.isEmpty(passageiro)) {
			JTextField voosExistentes = listVooCadastradas();
			textFields.add(voosExistentes);
			textFields.add(new JTextField());
			textFields.add(new JTextField());
			if (assento != null) {
				textFields.add(new JTextField(String.valueOf(assento.getVoo())));
				textFields.add(new JTextField(String.valueOf(assento.getFileira())));
				textFields.add(new JTextField(String.valueOf(assento.getPoltrona())));
			} else {
				textFields.add(new JTextField());
				textFields.add(new JTextField());
				textFields.add(new JTextField());
			}

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
		return this.getFrame(title, 600, 800, true);
	}

	private JFrame getFrame(String title, Integer altura, Integer largura) {
		return this.getFrame(title, altura, largura, true);
	}

	private JFrame getFrame(String title, Integer altura, Integer largura, Boolean exit) {
		JFrame jframe = new JFrame();
		jframe.setTitle(title);
		jframe.setVisible(true);
		jframe.setSize(largura, altura);
		if (Boolean.TRUE.equals(exit)) {
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		return jframe;
	}

	private void redirect(ActionEvent e, JFrame jframe, String to) throws Exception {
		jframe.dispose();
		this.redirect(e, jframe, to, null, null);
	}

	private void redirect(ActionEvent e, JFrame jframe, String to, AssentoDTO assento) throws Exception {
		jframe.dispose();
		this.redirect(e, jframe, to, null, assento);
	}

	private void redirect(ActionEvent e, JFrame jframe, String to, String backTo) throws Exception {
		jframe.dispose();
		this.redirect(e, jframe, to, backTo, null);
	}

	private void redirect(ActionEvent e, JFrame jframe, String to, String backTo, AssentoDTO assento) throws Exception {
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
				doReservaMenu(assento);
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
			if (Boolean.TRUE.equals(validadeBeforeSave(newAviao))) {
				this.avioes[contadorAeronave] = newAviao;
				this.aeronaves[contadorAeronave] = new Aeronave(newAviao.getModelo());
				this.contadorAeronave++;
				if (jFrame != null) {
					success(jFrame, "Aviao salvo com sucesso", "aeronave");
				}

			} else {
				error("Aviao ja cadastrado");
			}

		} catch (Exception e) {
			throw new Exception("Erro no método saveAviao: ", e);
		}

	}

	private void saveVoo(JFrame jFrame, Voo newVoo) throws Exception {
		try {
			if (Boolean.TRUE.equals(validadeBeforeSave(newVoo))) {
				this.voos[contadorVoos] = newVoo;
				this.contadorVoos++;
				success(jFrame, "Voo salvo com sucesso", "voo");
			} else {
				error("Voo ja existente");
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			error("Limite de voos cadastrados atingido");
		} catch (Exception e) {
			throw new Exception("Erro no método saveVoo: ", e);
		}

	}

	private void savePassageiro(JFrame jFrame, Passageiro newPassageiro, Integer posicaoVoo, int fileira, int assento)
			throws Exception {
		try {
			if (Boolean.TRUE.equals(validadeBeforeSave(this.avioes[posicaoVoo], newPassageiro))) {
				Voo voo = this.voos[posicaoVoo];
				Aviao aeronave = voo.getAeronave();
				aeronave.setPassageiro(fileira, assento, newPassageiro);
				this.voos[posicaoVoo].setAeronave(aeronave);
				success(jFrame, "Passageiro registrado com sucesso", "doReserva");
			} else {
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
		if (Boolean.TRUE.equals(redirect)) {
			redirect(null, jFrame, "voltar", to);
		}
		if (Boolean.TRUE.equals(reload)) {
			redirect(null, jFrame, to);
			jFrame.dispose();
		}
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public void error(String mensagem) throws Exception {
		this.error(mensagem, false, null);
	}

	public void errorOnTry(String mensagem) {
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void error(String mensagem, Boolean redirect, String to) throws Exception {
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
		if (Boolean.TRUE.equals(redirect)) {
			redirect(null, jFrame, "voltar", to);
		}
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void message(String msg) {
		this.message(msg, null, null, null);
	}

	private void message(String msg, JFrame jframeAntigo, AssentoDTO assento) {
		this.message(msg, jframeAntigo, "cadastrar", assento);
	}

	private void message(String msg, JFrame jframeAntigo, String buttonText, AssentoDTO assento) {
		JFrame jFrame = new JFrame();
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(msg);
		panel.add(label, BorderLayout.CENTER);

		if (assento != null) {
			JButton button = new JButton(buttonText);
			button.addActionListener((ActionEvent e) -> {
				try {
					redirect(e, jFrame, "doReserva", assento);
					jframeAntigo.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});

			panel.add(button, BorderLayout.SOUTH);
		}

		JOptionPane.showMessageDialog(jFrame, panel, "Informação", JOptionPane.INFORMATION_MESSAGE);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private Aviao getByAeronave(Aeronave aeronave) {
		for (Aviao aviao : this.avioes) {
			if (!ObjectUtils.isEmpty(aviao) && Objects.equals(aviao.getModelo(), aeronave.getModelo())) {
				return new Aviao(aviao.getModelo(), aviao.getFileiras(), aviao.getAssentos());
			}
		}
		return null;
	}

	private Integer getPosicaoVoo(int nroVoo) throws Exception {
		int cont = 0;

		Voo voo = loadVoo(nroVoo);

		if (voo == null) {
			return null;
		}

		for (Voo vooSalvo : this.voos) {
			if (Objects.equals(voo.getNro(), vooSalvo.getNro())) {
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

	private boolean validarCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
			return false;
		}

		int[] numeros = new int[11];
		for (int i = 0; i < 11; i++) {
			numeros[i] = Integer.parseInt(cpf.charAt(i) + "");
		}

		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += numeros[i] * (10 - i);
		}

		int resto = soma % 11;
		if (resto == 1 || resto == 0) {
			if (numeros[9] != 0) {
				return false;
			}
		} else if (numeros[9] != 11 - resto) {
			return false;
		}

		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += numeros[i] * (11 - i);
		}

		resto = soma % 11;
		if (resto == 1 || resto == 0) {
			if (numeros[10] != 0) {
				return false;
			}
		} else if (numeros[10] != 11 - resto) {
			return false;
		}

		return true;
	}

	private Boolean validarDataHora(String data, String hora) {
		String regexData = "^\\d{2}/\\d{2}/\\d{4}$";
		String regexHora = "^\\d{2}:\\d{2}$";
		return !(!data.matches(regexData) || !hora.matches(regexHora));
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

		if (!ObjectUtils.isEmpty(passageiro) && !ObjectUtils.isEmpty(aviao)) {
			return aviao.verificaLugarPorCpf(passageiro.getCpf());
		}

		return true;
	}

	private JTextField listAeronavesCadastradas() {
		StringBuilder aeronavesCadastradas = new StringBuilder();

		for (Aeronave aeronaveSaved : this.aeronaves) {
			if (!ObjectUtils.isEmpty(aeronaveSaved) && aeronaveSaved.getModelo() != null) {
				aeronavesCadastradas.append(aeronaveSaved.getModelo()).append("; ");
			}
		}
		if (aeronavesCadastradas.length() == 0) {
			aeronavesCadastradas.append("Não há aeronaves cadastradas");
		}
		JTextField aeronavesExistentes = new JTextField(aeronavesCadastradas.toString());
		aeronavesExistentes.setEditable(false);
		aeronavesExistentes.setFocusable(false);
		return aeronavesExistentes;
	}

	private JTextField listVooCadastradas() {
		StringBuilder voosCadastrados = new StringBuilder();

		for (Voo vooSaved : this.voos) {
			if (!ObjectUtils.isEmpty(vooSaved) && vooSaved.getNro() != null) {
				voosCadastrados.append(vooSaved.getNro()).append("; ");
			}
		}
		if (voosCadastrados.length() == 0) {
			voosCadastrados.append("Não há voos cadastrados");
		}
		JTextField voosExistentes = new JTextField(voosCadastrados.toString());
		voosExistentes.setEditable(false);
		voosExistentes.setFocusable(false);
		return voosExistentes;
	}

	private String validaAssentos(int fileiras, int assentosPorFileira) {
		if (assentosPorFileira > 6) {
			return "Número maximo de 6 assentos por fileira";
		}
		if (fileiras > 30) {
			return "Número maximo de 30 fileiras";
		} else {
			return null;
		}
	}
}
