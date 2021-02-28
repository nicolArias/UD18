package vista1;



import javax.swing.JOptionPane;

public class TiendaView {

	//CONSTRUCTOR POR DEFECTO
	public TiendaView() {
		
	}
	
	
	//Método para solicitar el nombre de la base de datos
	public String nombreBase() {
		String baseDatos=JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");
		
		return baseDatos;
	}
	//Menú de opciones para escoger tabla
		public String menu() {
			String menu=JOptionPane.showInputDialog("MENÚ:"
													+"- Fabricantes"
													+"- Articulos"
													+"- Cerrar");
			return menu;
		}
		

	//Menú de opciones para realizar el crud
	public String subMenu() {
		String subMenu=JOptionPane.showInputDialog("MENÚ:"
												+"1. Insertar "
												+"2. Consultar"
												+"3. Eliminar Registros"
												+"4. Eliminar tabla");
		return subMenu;
	}
	
	//Datos de entrada para la tabla 'Fabricantes'
	public String fabricantes() {
		String nombreF=JOptionPane.showInputDialog("Registro de Fabricante\n Nombre de fabricante:");
		
		return nombreF;
	}
	
	//Donde se veran los registros de la tabla Fabricante
	/*public void listaDatosFabricantes(String consulta) {
		JOptionPane.showMessageDialog(null, consulta);
	}*/
	
	//En este metodo el usuario escribira el codigo del registro que desea eliminar
	public Integer getCodigo() {
		int codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Borrar registros: \n"+""
														+ "Ingrese el codigo del registro que desea eliminar:"));
		return codigo;
	}
	

	/*Datos de entrada para la tabla 'Articulos' */
	public String articulos() {
		String nombreA=JOptionPane.showInputDialog("Nombre articulo:");
		String precio=JOptionPane.showInputDialog("Precio:");
		
		String idFabricante=JOptionPane.showInputDialog("Ingrese el codigo del fabricante asociado:");
		
		return nombreA+"/"+precio+"/"+idFabricante;
	}
	
	
	
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
