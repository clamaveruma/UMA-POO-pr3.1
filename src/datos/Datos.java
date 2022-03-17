package datos;

import java.util.ArrayList;

public class Datos {

	private ArrayList<Double> datos = new ArrayList<>();
	private ArrayList<String> errores = new ArrayList<>();
	private double max;
	private double min;
	
	public Datos(String[] cadenas_datos, double min, double max) {
		// va pasando cada cadena de datos a la lista de numeros
		
		for(String cad: cadenas_datos) {
			try {
				datos.add(Double.parseDouble(cad));
			} catch (NumberFormatException e) {	
				// si hay error de conversión la pasa a la de errores
				errores.add(cad);
			}
		}
		//guarda max y min
		this.max = max;
		this.min = min;
	}
}
