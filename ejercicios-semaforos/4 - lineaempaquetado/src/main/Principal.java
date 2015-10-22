package main;

import java.util.Scanner;

import buffer.Buffer;
import thread.RobotA;
import thread.RobotB;
import thread.RobotC;

public class Principal {
	
	Scanner teclado;
	
	Buffer bufferCilindros;
	Buffer bufferCubos;
	
	RobotA a;
	RobotB b;
	RobotC c;
	
	static int MAX_CUBOS = 2;
	static int MAX_CILINDROS = 3;
	
	public Principal() {
		bufferCilindros = new Buffer(MAX_CILINDROS);
		bufferCubos = new Buffer(MAX_CUBOS);
		teclado = new Scanner(System.in);
	}
	
	private void crearRobots() {
		a = new RobotA(bufferCilindros);
		b = new RobotB(bufferCubos);
		c = new RobotC(bufferCilindros, bufferCubos);
	}
	
	private void iniciarRobots() {
		a.start();
		b.start();
		c.start();
	}
	
	private void start() {
		crearRobots();
		iniciarRobots();
		teclado.nextLine();
	}
	
	private void matarRobots() {
		a.kill();
		b.kill();
		c.kill();
	}
	
	private void stop() {
		matarRobots();
	}
	
	public static void main(String [] args) {
		Principal mainProgram = new Principal();
		mainProgram.start();
		mainProgram.stop();
	}

}
