package ar.com.jmonteagudo.timesheets.util;

public class Format {
	
	public static String NumeroAString(Integer numero, Integer cantidadDigitos) {
		String numeroFormateado;
		numeroFormateado = (new String(new char[cantidadDigitos]).replace("\0", "0")) + numero.toString();
		numeroFormateado = numeroFormateado.substring(numeroFormateado.length()-cantidadDigitos, numeroFormateado.length());
		
		return numeroFormateado;
	}

}
