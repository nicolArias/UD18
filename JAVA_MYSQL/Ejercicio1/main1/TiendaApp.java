package main1;

import controlador1.Controller;
import modelo1.CrudArticulos;
import modelo1.CrudFabricantes;
import vista1.TiendaView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class TiendaApp {

	public static void main(String[] args) {
	
		CrudFabricantes cdFabricante=new CrudFabricantes();
		CrudArticulos cdArticulos=new CrudArticulos();
		TiendaView tiendaV=new TiendaView();

		Controller controlador=new Controller(cdFabricante,cdArticulos,tiendaV);
		
		
	}
}