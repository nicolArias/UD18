package main7;

import controlador6.ControllerPiezasPro;
import controlador7.ControllerCientificos;
import modelo7.CrudAsignado_A;
import modelo7.CrudCientificos;
import modelo7.CrudProyectos;
import vista7.CientificosView;


/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class CientificosApp {

	public static void main(String[] args) {
		
		CrudCientificos cdCientificos=new CrudCientificos();
		CrudProyectos cdProyectos=new CrudProyectos();
		CrudAsignado_A cdAsignado_A=new CrudAsignado_A();
		CientificosView cView=new CientificosView();
		
		ControllerCientificos controller=new ControllerCientificos(cdCientificos,cdProyectos,cdAsignado_A,cView);
	}

}
