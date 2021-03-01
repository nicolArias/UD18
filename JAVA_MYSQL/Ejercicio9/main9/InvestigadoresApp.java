package main9;

import controlador9.ControllerInvestigadores;
import modelo9.CrudEquipos;
import modelo9.CrudFacultades;
import modelo9.CrudInvestigadores;
import modelo9.CrudReservas;
import vista9.InvestigadoresView;

public class InvestigadoresApp {

	public static void main(String[] args) {
		
		CrudFacultades cdFacultades=new CrudFacultades();
		CrudEquipos cdEquipos=new CrudEquipos();
		CrudInvestigadores cdInvestigadores=new CrudInvestigadores();
		CrudReservas cdReservas=new CrudReservas();
		InvestigadoresView iView=new InvestigadoresView();
		
		ControllerInvestigadores controller=new ControllerInvestigadores(cdFacultades,cdEquipos,cdInvestigadores,cdReservas,iView);

	}

}
