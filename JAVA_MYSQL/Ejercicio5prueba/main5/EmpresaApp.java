package main5;



import controlador5.ControllerEmpresa;
import modelo5.CrudDespachos;
import modelo5.CrudDirectores;
import vista5.EmpresaView;


/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class EmpresaApp {

	public static void main(String[] args) {
		
		CrudDespachos cdDespachos=new CrudDespachos();
		CrudDirectores cdDirectores=new CrudDirectores();
		EmpresaView eView= new EmpresaView();
		
		ControllerEmpresa controller=new ControllerEmpresa(cdDespachos,cdDirectores,eView);
	}

}
