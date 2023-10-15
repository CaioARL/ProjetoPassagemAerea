package br.edu.ifsp.projeto_passagem_aerea.application.model;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.util.ObjectUtils;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	Aeronave aeronave =  new Aeronave(null);
	Aviao aviao = new Aviao(null, 0, 0);
	Voo voo =  new Voo(aviao, 0, null, null);
	Passageiro passageiro = new Passageiro(null, null);

	public void mainMenu() throws Exception{
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

	private void parametroMenu() throws Exception{
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

	private void reservaMenu()throws Exception {
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

	    List<String> titles = new ArrayList<String>();
		titles.add("Modelo aeronave: ");
		titles.add("Numero de fileiras");
		titles.add("Numero total de assentos");
	    JPanel modeloPanel = getText(titles, 100, this.aeronave, this.aviao);
	    List<JTextField> modelosText = new ArrayList<JTextField>();
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

	    
	    try {
	    	jButtonSalvar.addActionListener(action -> {
	    		String modelo = modelosText.get(0).getText();
	    		int nroFileira = Integer.parseInt(modelosText.get(1).getText());
	    		int totalAssentos = Integer.parseInt(modelosText.get(2).getText());
	    		
		        try {
					this.saveAviao(jframe, new Aviao(modelo, nroFileira, totalAssentos));
				} catch (Exception e) {
					e.printStackTrace();
				}
		    });
	    }catch (Exception e) {
	    	throw new Error("Erro ao salvar aeronave:", e);
		}
	    
	}


	private void vooMenu() throws Exception{
		JFrame jframe = getFrame("Cadastro voo");
		
		List<String> titles = new ArrayList<String>();
		titles.add("Insira aqui o numero do voo: ");
	    titles.add("Insira aqui a data do voo: ");
	    titles.add("Insira aqui a hora do voo: ");
	    JPanel modeloPanel = getText(titles, 50, this.voo);
	    List<JTextField> modelosText = new ArrayList<JTextField>();
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
				this.redirect(action, jframe, "voltar", "parametros");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	    try {
	    	jButtonSalvar.addActionListener(action -> {
		        try {
		    		int numero = Integer.parseInt(modelosText.get(0).getText());
		    		String data = modelosText.get(1).getText();
		    		String hora = modelosText.get(2).getText();
					this.saveVoo(jframe, new Voo(new Aviao(hora, numero, numero), numero, data, hora));
				} catch (NumberFormatException e) {
					throw new Error("Erro ao inserir valores, por favor verifique os tipos: ", e);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
		    });
	    }catch (NumberFormatException e) {
	    	throw new Error("Formato de dados inserido incorretamente:", e);
		}
	}

	private void doReservaMenu() throws Exception{
		JFrame jframe = getFrame("Fazer Reserva");

		// Reserva

		// Lugares vazios

		// Reservas realizadas

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

	private void vazio() throws Exception{
		JFrame jframe = getFrame("Consultar lugares vazios");

		// Reserva

		// Lugares vazios

		// Reservas realizadas

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

	private void realizadas() throws Exception{
		JFrame jframe = getFrame("Consultar reservas realizadas");

		// Reserva

		// Lugares vazios

		// Reservas realizadas

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
	
	private JPanel getText(List<String> titles, int posicao, Aeronave aeronave, Aviao aviao) {
		return this.getText(titles, posicao, aeronave, null, aviao);
	}
	
	private JPanel getText(List<String> titles, int posicao, Voo voo) {
		return this.getText(titles, posicao, null, voo, null);
	}

	private JPanel getText(List<String> titles, int posicao, Aeronave aeronave, Voo voo, Aviao aviao) {
		JPanel panel = new JPanel();
		
		List<JTextField> textFields = new ArrayList<JTextField>();
		
		if(!ObjectUtils.isEmpty(aeronave)) {
			textFields.add(new JTextField(aeronave.getModelo()));
			textFields.add(new JTextField(String.valueOf(aviao.lugares.length)));
			int columns = 0;
	        if (aviao.lugares.length > 0 && aviao.lugares[0] != null) {
	            columns = aviao.lugares[0].length;
	        }
			textFields.add(new JTextField(String.valueOf(columns)));
		}
		if(!ObjectUtils.isEmpty(voo)) {
			textFields.add(new JTextField(String.valueOf(voo.getNro())));
			textFields.add(new JTextField(voo.getData()));
			textFields.add(new JTextField(voo.getHora()));
		}

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for (int i=0; i< textFields.size(); i++) {
			panel.add(new Label(titles.get(i)));
			panel.add(textFields.get(i));
			panel.setBounds(250, posicao, 300, 150);
			posicao += 10;
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
			vazio();
			break;
		}
		case "realizadas": {
			realizadas();
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
			this.aviao = new Aviao(newAviao.getModelo(), newAviao.lugares.length, newAviao.lugares[0].length);
			this.aeronave = new Aeronave(newAviao.getModelo());
			success(jFrame, "Aviao salva com sucesso");
		}catch (Exception e) {
			throw new Exception("Erro no método saveAviao: ", e);
		}
		
	}
	
	private void saveVoo(JFrame jFrame, Voo newVoo) throws Exception {
		try {
			this.voo = newVoo;
			success(jFrame, "Voo salvo com sucesso");
		}catch (Exception e) {
			throw new Exception("Erro no método saveVoo: ", e);
		}
		
	}
	
	private void success(JFrame jFrame, String mensagem) {
	    JOptionPane.showMessageDialog(jFrame, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}
}
