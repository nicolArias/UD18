package main6;

import controlador6.ControllerPiezasPro;
import modelo6.CrudPiezas;
import modelo6.CrudPiezas_Proveedores;
import modelo6.CrudProveedores;
import vista6.PiezasProView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class PiezasProApp {

	public static void main(String[] args) {
		
		CrudPiezas cdPiezas=new CrudPiezas();
		CrudProveedores cdProveedores=new CrudProveedores();
		CrudPiezas_Proveedores cdPiezas_Pro=new CrudPiezas_Proveedores();
		PiezasProView pView= new PiezasProView();
		
		ControllerPiezasPro controller=new ControllerPiezasPro(cdPiezas,cdProveedores,cdPiezas_Pro,pView);
	}

}
