package com.sigecap.sigecapexamenbackend.util;

import java.io.InputStream;
import java.net.URL;

public class CargarRecurso {

	public static InputStream cargarReporte (String archivo) {
		return cargarStream ("static/reports/" + archivo);
	}

	public static InputStream cargarStream (String path) {
		ClassLoader cl = CargarRecurso.class.getClassLoader();
		return cl.getResourceAsStream(path);
	}

	public static URL cargarImagen (String archivo) {
		return cargar ("static/image/" + archivo);
	}

	public static URL cargarCarpetaSubReporte () {
		return cargar ("static/reports/");
	}
	
	public static URL cargarSubReporte (String archivo) {
		return cargar ("static/reports/" + archivo);
	}
	public static URL cargar (String path) {
		return CargarRecurso.class.getClassLoader().getResource(path);
	}
	
}
