package buffer;

import thread.Coche;

public class GarageITV {
	
	aBuzon<Coche> cocheras;
	
	int capacidad;
	int revisores;
	
	Coche [] parking;
	
	public GarageITV(int capacidad, int revisores) {
		cocheras = new aBuzon<>(revisores);
		this.capacidad = capacidad;
		this.revisores = revisores;
		parking = new Coche[capacidad];
	}

	public void entrarAlParking() {
		
	}
	
	public void entrarCochera() {
		
	}
	
	public void salirCochera() {
		
	}
	
}
