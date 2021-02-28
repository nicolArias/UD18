package main2;

import controlador2.ControllerOrg;
import modelo2.CrudDepartamentos;
import modelo2.CrudEmpleados;
import vista2.OrganizacionView;

public class OrganizacionApp {

	public static void main(String[] args) {
		
		CrudDepartamentos cdDepartamentos=new CrudDepartamentos();
		CrudEmpleados cdEmpleados=new CrudEmpleados();
		OrganizacionView organizacionV=new OrganizacionView();

		ControllerOrg controlador=new ControllerOrg(cdDepartamentos,cdEmpleados,organizacionV);
	}

}
