package vista3;

import javax.swing.JOptionPane;

public class AlmacenesView {
	//CONSTRUCTOR POR DEFECTO
		public AlmacenesView() {
			
		}
		
		
		//Método para solicitar el nombre de la base de datos
		public String nombreBase() {
			String baseDatos=JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");
			
			return baseDatos;
		}
		//Menú de opciones para escoger tabla
			public String menu() {
				String menu=JOptionPane.showInputDialog("MENÚ:"
														+"\n- Almacenes"
														+"\n- Cajas"
														+"\n- Cerrar");
				return menu;
			}
			

		//Menú de opciones para realizar el crud
		public String subMenu() {
			String subMenu=JOptionPane.showInputDialog("MENÚ:"
													+"\n1. Insertar "
													+"\n2. Consultar"
													+"\n3. Eliminar Registros"
													+"\n4. Eliminar tabla");
			return subMenu;
		}
		
		//Datos de entrada para la tabla 'Fabricantes'
		public String almacenes() {
			String lugarA=JOptionPane.showInputDialog("Lugar almacen:");
			String capacidadA=JOptionPane.showInputDialog("Capacidad: ");
			
			return lugarA+"/"+capacidadA;
		}
		

		//En este metodo el usuario escribira el codigo del registro que desea eliminar
		public Integer getCodigo() {
			int codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
															+ "Ingrese el codigo del registro que desea eliminar:"));
			return codigo;
		}
		
		//En este metodo el usuario escribira el codigo del registro que desea eliminar
		public String getNumReferencia() {
			String codigo=JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
															+ "Ingrese el el nº de referencia del registro que desea eliminar:");
			return codigo;
		}
		

		/*Datos de entrada para la tabla 'Articulos'*/
		public String cajas() {
			String numReferencia=JOptionPane.showInputDialog("Nº referencia:");
			String contenido=JOptionPane.showInputDialog("Ingrese el contenido:");
			String valor=JOptionPane.showInputDialog("Ingrese el valor:");
			String almacenFK=JOptionPane.showInputDialog("Ingrese el código del almacen asociado:");
			
			return numReferencia+"/"+contenido+"/"+valor+"/"+almacenFK;
		}

		
		public void mostrarMensaje(String mensaje) {
			JOptionPane.showMessageDialog(null, mensaje);
		}
}
