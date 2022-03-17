package datos;

import java.util.ArrayList;



public class Datos {

	private ArrayList<Double> datos = new ArrayList<>();
	private ArrayList<String> errores = new ArrayList<>();
	private double max;
	private double min;
	
	/**
	 * Construye un objeto Datos
	 * @param cadenas_datos, array de cadenas representando números
	 * @param min
	 * @param max
	 */
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
	
	public double calcMedia() {
		double sum = 0;
		int count = 0;
		for (double item: datos) {
			if (min <= item && item <= max) {
				sum = sum + item;
				++count;
			}	
		}
		if (count == 0) {
				throw new DatosException("No hay datos en el rango especificado");
		}
		return sum/count;
	}
	public double calcDesvTipica() {
		double mean = calcMedia();  // lanza excepción si no hay datos en el rango
		double sumDifSq = 0; // suma de (desviaciones al cuadrado)
		int count = 0;
		for (double item: datos) {
			if (min <= item && item <= max) {
				sumDifSq = sumDifSq + Math.pow(item - mean, 2);
				++count;
			}	
		}
		// ya no creo error por count ==0 porque calcMedia lo creó
		return Math.sqrt(sumDifSq/count);
	}
	
	public void setRango(String cadenaRango) {
		try {
			String[] parts = cadenaRango.split(";");
			//intento cada parte o sólo los pongo si no hay errores??
			min = Double.parseDouble(parts[0]);
			max = Double.parseDouble(parts[1]);
		} catch (RuntimeException e) {
			throw new DatosException("Error en los datos al establecer el rango");	
		}
	}
	
	public ArrayList<Double> getDatos(){
		return datos;
	}
	public ArrayList<String> getErrores(){
		return errores;
	}	
	
	@Override
	public String toString() {
		String result = String.format("Min: %f, Max: %f, %s, %s, ",
				min, max, datos, errores);
		try {
			result = result + String.format("Media: %f, DesvTipica: %f",
					calcMedia(), calcDesvTipica());
		} catch (DatosException e) {
			result = result + "Media: ERROR, DesvTipica: ERROR";
		}
	}
	
}
