package vista5;

import javax.swing.JOptionPane;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha:25/02/2021*/
public class EmpresaView {
	//CONSTRUCTOR POR DEFECTO
			public EmpresaView() {
				
			}
			
			
			//M�todo para solicitar el nombre de la base de datos
			public String nombreBase() {
				String baseDatos=JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");
				
				return baseDatos;
			}
			//Men� de opciones para escoger tabla
				public String menu() {
					String menu=JOptionPane.showInputDialog("MEN�:"
															+"\n- Despachos"
															+"\n- Directores"
															+"\n- Cerrar");
					return menu;
				}
				

			//Men� de opciones para realizar el crud
			public String subMenu() {
				String subMenu=JOptionPane.showInputDialog("MEN�:"
														+"\n1. Insertar "
														+"\n2. Consultar"
														+"\n3. Eliminar Registros"
														+"\n4. Eliminar tabla");
				return subMenu;
			}
			
			//Datos de entrada para la tabla 'Despachos'
			public String despacho() {
				String numero=JOptionPane.showInputDialog("N�mero despacho:");
				String capacidad=JOptionPane.showInputDialog("Capacidad: ");
				
				return numero+"/"+capacidad;
			}
			

			//En este metodo el usuario escribira el codigo del registro que desea eliminar
			public Integer getCodigo() {
				int numero=Integer.parseInt(JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
																+ "Ingrese el codigo del registro que desea eliminar:"));
				return numero;
			}
			
			public String getDNI() {
				String DNI=JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
																+ "Ingrese el DNI del director que desea eliminar:");
				return DNI;
			}
			

			/*Datos de entrada para la tabla 'Salas'*/
			public String directores() {
				String DNI=JOptionPane.showInputDialog("DNI:");
				String nomApels=JOptionPane.showInputDialog("Nombre y Apellidos:");
				String DNIJefe=JOptionPane.showInputDialog("Ingrese el DNI del Jefe:");
				String despacho_fk=JOptionPane.showInputDialog("Ingrese el c�digo del despacho asociado:");

				return DNI+"/"+nomApels+"/"+DNIJefe+"/"+despacho_fk;
			}

			
			public void mostrarMensaje(String mensaje) {
				JOptionPane.showMessageDialog(null, mensaje);
			}
}
