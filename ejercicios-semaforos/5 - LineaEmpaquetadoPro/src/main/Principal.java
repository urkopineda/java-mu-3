package main;

import java.util.Scanner;

import buffer.Buffer;
import thread.RobotA;
import thread.RobotB;
import thread.RobotC;
import thread.RobotD;

public class Principal {
	
	Scanner teclado;
	
	Buffer bufferCilindros;
	Buffer bufferCubos;
	Buffer bufferRueda;
	
	RobotA a;
	RobotB b;
	RobotC c;
	RobotD d;
	
	static int MAX_CUBOS = 1;
	static int MAX_CILINDROS = 1;
	static int MAX_RUEDA = 1;
	
	public Principal() {
		bufferCilindros = new Buffer(MAX_CILINDROS);
		bufferCubos = new Buffer(MAX_CUBOS);
		bufferRueda = new Buffer(MAX_RUEDA);
		teclado = new Scanner(System.in);
	}
	
	private void crearRobots() {
		a = new RobotA(bufferCilindros);
		b = new RobotB(bufferCubos);
		c = new RobotC(bufferRueda);
		d = new RobotD(bufferCilindros, bufferCubos, bufferRueda);
	}
	
	private void iniciarRobots() {
		a.start();
		b.start();
		c.start();
		d.start();
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
		d.kill();
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
