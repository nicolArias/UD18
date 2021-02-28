package controlador2;


import clase.ConexionBD;
import modelo2.CrudDepartamentos;
import modelo2.CrudEmpleados;
import vista2.OrganizacionView;

/*Autor: Nicol Dayana Arias Lebro
 *Fecha: 25/02/2021*/

public class ControllerOrg {
	private ConexionBD conexion;
	private CrudDepartamentos cdDepartamento;
	private CrudEmpleados cdEmpleado;
	private OrganizacionView oVista;

	private String nombreB;
	
	public ControllerOrg(CrudDepartamentos cdDepartamento,CrudEmpleados cdEmpleado,OrganizacionView oVista) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdDepartamento=cdDepartamento;
		this.cdEmpleado=cdEmpleado;
		this.oVista=oVista;
		
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=oVista.nombreBase();
		
		//Método de create
		CrearBD();
		tableDepartamentos();
		tableEmpleados();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		oVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tableDepartamentos() {
		String crearTablaD=cdDepartamento.createTable(nombreB);
		oVista.mostrarMensaje(crearTablaD);
	}
	
	public void tableEmpleados() {
		String crearTablaE=cdEmpleado.createTable(nombreB);
		oVista.mostrarMensaje(crearTablaE);
	}
	
	
	//Menú de opciones
	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=oVista.menu();
			if(respMenu.equalsIgnoreCase("Departamentos")) {
				respSMenu=oVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertDepartamento();
					break;
				case("2")://Consultar
					selectDepartamento();
					break;
				case("3")://Eliminar registros
					deleteDepartamento();
					break;
				case("4")://Eliminar tabla
					dlTablaDepartamento();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Empleados")) {
				respSMenu=oVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertEmpleado();
					break;
				case("2")://Consultar
					selectEmpleado();
					break;
				case("3")://Eliminar registros
					deleteEmpleado();
					break;
				case("4")://Eliminar tabla
					dlTablaEmpleado();
					break;
				}
			}else if(respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		}catch(NullPointerException e) {
			oVista.mostrarMensaje("Dato incorrecto");
		}
	}
	
	public void cerrarConexion() {
		String close=conexion.closeConnection();
		oVista.mostrarMensaje(close);

	}
	
	public void insertDepartamento() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadenaD=oVista.departamento().split("/");
			int codigo=Integer.parseInt(cadenaD[0]);
			String nombre=cadenaD[1];
			int presupuesto=Integer.parseInt(cadenaD[2]);
			String s_insertD=cdDepartamento.insertData(nombreB,codigo,nombre,presupuesto);
		}
		
		opcionMenu();
	}
	

	public void selectDepartamento() {
		
		String consulta=cdDepartamento.getValues(nombreB);
		oVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteDepartamento() {
		try {
			int codS=oVista.getCodigo();
			String deletD=cdDepartamento.deleteRecord(nombreB, codS);
			oVista.mostrarMensaje(deletD);
			
		}catch(NullPointerException | NumberFormatException e) {
			oVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaDepartamento() {
		String dlTabla=cdDepartamento.deleteTabla(nombreB);
		oVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertEmpleado() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadenaE=oVista.empleado().split("/");
			String DNI=cadenaE[0];
			String nombre=cadenaE[1];
			String apellidos=cadenaE[2];
			int departamento_fk=Integer.parseInt(cadenaE[3]);
			
			String s_insertE=cdEmpleado.insertData(nombreB,DNI,nombre,apellidos,departamento_fk);
			oVista.mostrarMensaje(s_insertE);
		}
		
		opcionMenu();
	}
	

	public void selectEmpleado() {
		
		String consulta=cdEmpleado.getValues(nombreB);
		oVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteEmpleado() {
		try {
			String codS=oVista.getDNI();
			String deletE=cdEmpleado.deleteRecord(nombreB, codS);
			oVista.mostrarMensaje(deletE);
			
		}catch(NullPointerException e) {
			oVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaEmpleado() {
		String dlTabla=cdEmpleado.deleteTabla(nombreB);
		oVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
}
