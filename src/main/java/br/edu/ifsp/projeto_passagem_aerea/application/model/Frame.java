package br.edu.ifsp.projeto_passagem_aerea.application.model;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Frame extends JFrame {

    public void mainMenu() {
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
        	this.redirect(action, jframe, "parametro");
        });
        
        jButtonReserva.addActionListener(action -> {
        	this.redirect(action, jframe, "reserva");
        });
        
        jButtonSair.addActionListener(action -> {
        	this.redirect(action, jframe, "sair");
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
        	this.redirect(action, jframe, "aeronave");
        });
        
        jButtonVoo.addActionListener(action -> {
        	this.redirect(action, jframe, "voo");
        });
        
        jButtonVoltar.addActionListener(action -> {
        	this.redirect(action, jframe, "voltar", "main");
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
        	this.redirect(action, jframe, "doReserva");
        });
        
        jButtonVazio.addActionListener(action -> {
        	this.redirect(action, jframe, "vazio");
        });
        
        jButtonRealizadas.addActionListener(action -> {
        	this.redirect(action, jframe, "realizadas");
        });
        
        jButtonVoltar.addActionListener(action -> {
        	this.redirect(action, jframe, "voltar", "main");
        });
        
    }
    
    private void aeronaveMenu() {
    	JFrame jframe = getFrame("Cadastro aeronave");
    	
        // Reserva
    	JPanel modeloText = getText("Insira aqui o modelo: ", 100);
        
        // Voltar
        JButton jButtonVoltar = getButton("Voltar", 350);
        
         // Salvar
        JButton jButtonSalvar =  getButton("Salvar", 250);
        
        // add buttons
        jframe.setLayout(null);
        jframe.add(modeloText);
        jframe.add(jButtonVoltar);
        jframe.add(jButtonSalvar);
        
        // add functions
        jButtonVoltar.addActionListener(action -> {
        	this.redirect(action, jframe, "voltar", "parametros");
        });
    }
    
    private void vooMenu() {
    	JFrame jframe = getFrame("Cadastro voo");

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
        	this.redirect(action, jframe, "voltar", "parametros");
        });
    }
    
    private void doReservaMenu() {
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
        	this.redirect(action, jframe, "voltar", "reserva");
        });
    }
    
    private void vazio() {
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
        	this.redirect(action, jframe, "voltar", "reserva");
        });
    }
    
    private void realizadas() {
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
        	this.redirect(action, jframe, "voltar", "reserva");
        });
    }

    private JButton getButton(String title, int posicao) {
    	JButton button = new JButton(title);
    	button.setBounds(250, posicao, 300, 70);
    	return button;
    }
    
    private JPanel getText(String title, int posicao) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(title);
        JTextField textField = new JTextField();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(textField);

        panel.setBounds(250, posicao, 300, 100);

        return panel;
    }

    
    private JFrame getFrame(String title) {
        JFrame jframe = new JFrame();
        jframe.setTitle(title);
        jframe.setVisible(true);
        jframe.setSize(800, 500);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        return jframe;
    }
    
    private void redirect(ActionEvent e, JFrame jframe, String to) {
    	this.redirect(e, jframe, to, null);
    }
    
    private void redirect(ActionEvent e, JFrame jframe, String to, String backTo) {
    	jframe.setVisible(false);
    	switch (to) {
		case "reserva": {
			reservaMenu();
			break;
		}
		case "parametro":{
			parametroMenu();
			break;
		}
		case "aeronave":{
			aeronaveMenu();
			break;
		}
		case "voo":{
			vooMenu();
			break;
		}
		case "doReserva":{
			doReservaMenu();
			break;
		}
		case "vazio":{
			vazio();
			break;
		}
		case "realizadas":{
			realizadas();
			break;
		}
		case "sair":{
			jframe.dispose();
			break;
		}
		case "voltar":{
			backTo(backTo);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + to);
		}
    }
    
    private void backTo(String backTo) {
    	
    	switch (backTo) {
		case "main" : {
			mainMenu();
			break;
		}
		case "parametros":{
			parametroMenu();
			break;
		}
		case "reserva":{
			reservaMenu();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + backTo);
		}
    	
    }
}
