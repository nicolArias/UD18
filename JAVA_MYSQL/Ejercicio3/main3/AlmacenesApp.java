package main3;

import controlador3.ControllerAlmacen;
import model3.CrudAlmacenes;
import model3.CrudCajas;
import vista3.AlmacenesView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class AlmacenesApp {

	public static void main(String[] args) {
		//Instanciamos las clases que usaremos en el controlador para enviarlas como parametro
		CrudAlmacenes cdAlmacen=new CrudAlmacenes();
		CrudCajas cdCaja=new CrudCajas();
		AlmacenesView aView=new AlmacenesView();
		
		
		ControllerAlmacen controller=new ControllerAlmacen(cdAlmacen,cdCaja,aView);

	}

}
