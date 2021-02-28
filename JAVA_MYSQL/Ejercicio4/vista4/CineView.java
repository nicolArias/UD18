package vista4;

import javax.swing.JOptionPane;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha:25/02/2021*/
public class CineView {
	//CONSTRUCTOR POR DEFECTO
			public CineView() {
				
			}
			
			
			//M�todo para solicitar el nombre de la base de datos
			public String nombreBase() {
				String baseDatos=JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");
				
				return baseDatos;
			}
			//Men� de opciones para escoger tabla
				public String menu() {
					String menu=JOptionPane.showInputDialog("MEN�:"
															+"\n- Peliculas"
															+"\n- Salas"
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
			
			//Datos de entrada para la tabla 'Peliculas'
			public String peliculas() {
				String nombre=JOptionPane.showInputDialog("Nombre pel�cula:");
				String calificacionE=JOptionPane.showInputDialog("Calificaicon edad: ");
				
				return nombre+"/"+calificacionE;
			}
			

			//En este metodo el usuario escribira el codigo del registro que desea eliminar
			public Integer getCodigo() {
				int codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
																+ "Ingrese el codigo del registro que desea eliminar:"));
				return codigo;
			}
			

			/*Datos de entrada para la tabla 'Salas'*/
			public String salas() {
				String nombre=JOptionPane.showInputDialog("Nombre sala:");
				String codPelicula=JOptionPane.showInputDialog("C�digo pel�cula:");
				
				
				return nombre+"/"+codPelicula;
			}

			
			public void mostrarMensaje(String mensaje) {
				JOptionPane.showMessageDialog(null, mensaje);
			}
}
