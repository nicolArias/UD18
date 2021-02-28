package main4;

import controlador4.ControllerCine;
import modelo4.CrudPeliculas;
import modelo4.CrudSalas;
import vista4.CineView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class CineApp {

	public static void main(String[] args) {
		
		CrudPeliculas cdPelicula=new CrudPeliculas();
		CrudSalas cdSala=new CrudSalas();
		CineView cView= new CineView();
		
		ControllerCine controller=new ControllerCine(cdPelicula,cdSala,cView);
	}

}
