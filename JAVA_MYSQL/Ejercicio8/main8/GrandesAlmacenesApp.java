package main8;


import controlador8.ControllerGrandesAlmacenes;
import modelo8.CrudCajeros;
import modelo8.CrudMaquinas_Registradoras;
import modelo8.CrudProductos;
import modelo8.CrudVentas;
import vista8.VentasView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class GrandesAlmacenesApp {

	public static void main(String[] args) {
		
		CrudCajeros cdCajeros=new CrudCajeros();
		CrudMaquinas_Registradoras cdMaquinasR=new CrudMaquinas_Registradoras();
		CrudProductos cdProductos=new CrudProductos();
		CrudVentas cdVentas=new CrudVentas();
		VentasView vView=new VentasView();
		
		ControllerGrandesAlmacenes controller=new ControllerGrandesAlmacenes(cdCajeros,cdMaquinasR,cdProductos,vView);
	}

}
