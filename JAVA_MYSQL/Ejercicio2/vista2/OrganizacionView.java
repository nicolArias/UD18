package vista2;

import javax.swing.JOptionPane;

public class OrganizacionView {
	//CONSTRUCTOR POR DEFECTO
		public OrganizacionView() {
			
		}
		
		
		//Método para solicitar el nombre de la base de datos
		public String nombreBase() {
			String baseDatos=JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");
			
			return baseDatos;
		}
		//Menú de opciones para escoger tabla
			public String menu() {
				String menu=JOptionPane.showInputDialog("MENÚ:"
														+"- Departamentos"
														+"- Empleados"
														+"- Cerrar");
				return menu;
			}
			

		//Menú de opciones para realizar el crud
		public String subMenu() {
			String subMenu=JOptionPane.showInputDialog("MENÚ:"
													+"1. Insert ar "
													+"2. Consultar"
													+"3. Eliminar Registros"
													+"4. Eliminar tabla");
			return subMenu;
		}
		
		//Datos de entrada para la tabla 'Departamentos'
		public String departamento() {
			String codigoD=JOptionPane.showInputDialog("Ingrese el código :");
			String nombreD=JOptionPane.showInputDialog("Ingrese el nombre :");
			String presupuesto=JOptionPane.showInputDialog("Presupuedo :");
			
			return codigoD+"/"+nombreD+"/"+presupuesto;
		}
		
		
		//En este metodo el usuario escribira el codigo del registro que desea eliminar
		public Integer getCodigo() {
			int codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
															+ "Ingrese el codigo del registro que desea eliminar:"));
			return codigo;
		}
		
		public String getDNI() {
			String dni=JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
															+ "Ingrese el DNI del registro que desea eliminar:");
			return dni;
		}
		

		/*Datos de entrada para la tabla 'Articulos' va a recibir un arreglo de fabricantes para poder 
		seleccionar*/ 
		public String empleado() {
			String dniE=JOptionPane.showInputDialog("Ingrese el DNI :");
			String nombreE=JOptionPane.showInputDialog("Nombre :");
			String apellidoE=JOptionPane.showInputDialog("Apellidos :");
			
			String idDepartamento=JOptionPane.showInputDialog("Ingrese el codigo del departamento asociado:");
			
			return dniE+"/"+nombreE+"/"+apellidoE+"/"+idDepartamento;
		}
		
		
		
		
		public void mostrarMensaje(String mensaje) {
			JOptionPane.showMessageDialog(null, mensaje);
		}
}
