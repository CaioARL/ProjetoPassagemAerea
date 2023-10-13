package br.edu.ifsp.projeto_passagem_aerea.application.model;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {

    public void mainMenu() {
        JFrame jframe = getFrame("Menu Principal");

        // Parâmetros
        JButton jButtonParametros = getButton("Parâmetros do sistema", 100);

        // Reserva
        JButton jButtonReserva = getButton("Reserva de Passagens", 200);
        
        //Reserva
        JButton jButtonSair = getButton("Sair", 300);

        jframe.setLayout(null);
        jframe.add(jButtonParametros);
        jframe.add(jButtonReserva);
        jframe.add(jButtonSair);
    }
    
    private void parametroMenu() {
    	JFrame jframe = getFrame("Parâmetros do Sistema");

        // Aeronave
        JButton jButtonAerovane = getButton("Cadastrar Aeronave", 100);

        // Voo
        JButton jButtonVoo = getButton("Cadastrar Voo", 200);
        
        //Voltar
        JButton jButtonVoltar = getButton("Voltar", 300);

        jframe.setLayout(null);
        jframe.add(jButtonAerovane);
        jframe.add(jButtonVoo);
        jframe.add(jButtonVoltar);
    }
    
    private void reservaMenu() {
    	JFrame jframe = getFrame("Reserva de Passagens");

        //Reserva
    	JButton jButtonReserva = getButton("Fazer Reserva", 50);

        //Lugares vazios
        JButton jButtonVazio = getButton("Consultar Lugares Vazios", 150);
        
        //Reservas realizadas
        JButton jButtonRealizadas = getButton("Consultar Reservas Realizadas", 250);

        //Voltar
        JButton jButtonVoltar = getButton("Voltar", 350);
        
        jframe.setLayout(null);
        jframe.add(jButtonReserva);
        jframe.add(jButtonVazio);
        jframe.add(jButtonRealizadas);
        jframe.add(jButtonVoltar);
    }

    private JButton getButton(String title, int posicao) {
    	JButton button = new JButton(title);
    	button.setBounds(250, posicao, 300, 70);
    	return button;
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
}
