package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import thread.Cronometro;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PrincipalUI implements ActionListener {

	Cronometro crono;
	Scanner teclado;
	
	JFrame ventana;
	JPanel dfPanel;
	private JLabel numero;
	JButton boton;
		
	public PrincipalUI() {
		ventana = new JFrame("Cronómetro");
		ventana.setSize(500, 500);
		ventana.setLocation(50, 50);
		ventana.setContentPane(createDefaultPanel());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private Container createDefaultPanel() {
		dfPanel = new JPanel(new BorderLayout());
		dfPanel.add(numero = new JLabel("0"), BorderLayout.CENTER);
		numero.setFont(new Font("Dialog", Font.BOLD, 70));
		numero.setHorizontalAlignment(SwingConstants.CENTER);
		boton = new JButton("Iniciar");
		boton.setActionCommand("iniciar");
		boton.addActionListener(this);
		dfPanel.add(boton, BorderLayout.SOUTH);
		return dfPanel;
	}

	private void start() {
		crono = new Cronometro(this);
		crono.execute();
	}

	public static void main(String [] args) {
		@SuppressWarnings("unused")
		PrincipalUI ui = new PrincipalUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("iniciar")) start();	
	}
	
	public void setSeconds(int seconds) {
		numero.setText(String.valueOf(seconds));
	}
}
