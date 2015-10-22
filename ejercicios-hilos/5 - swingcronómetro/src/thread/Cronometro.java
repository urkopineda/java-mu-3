package thread;

import javax.swing.SwingWorker;

import ui.PrincipalUI;

public class Cronometro extends SwingWorker<String,String> {
	
	int segundos;
	PrincipalUI ui;
	
	public Cronometro(PrincipalUI ui) {
		segundos = 0;
		this.ui = ui;
	}

	@Override
	protected String doInBackground() throws Exception {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			ui.setSeconds(++segundos);
		}
	}
	
}
