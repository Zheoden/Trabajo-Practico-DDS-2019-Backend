package setup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.dtos.Color;
import modelo.dtos.TipoPrenda;
import modelo.interfaces.Suscripcion;
import repository.UsuarioRepo;
import utils.Utils;

public class SetUp {
	
	public void init()
	{
		Prenda camisaRojo = new Prenda(TipoPrenda.CAMISA, Color.ROJO);
		Prenda bufandaAzul = new Prenda(TipoPrenda.BUFANDA, Color.AZUL);
		Prenda pantalonVerde = new Prenda(TipoPrenda.PANTALON, Color.VERDE);
		ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();
		Guardarropas guardaropa1 = new Guardarropas(prendas1);
		ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
		Suscripcion premium = new SuscripcionPremium();
		Calendar fecha1 = GregorianCalendar.getInstance();
		Calendar fecha2 = GregorianCalendar.getInstance();
		Usuario usuario1 = new Usuario(guardaRopas, premium,"test@test.com", "12341234", 0);

		prendas1.add(camisaRojo);
		prendas1.add(bufandaAzul);
		prendas1.add(pantalonVerde);
		guardaRopas.add(guardaropa1);
		fecha1.set(2019, 10, 12);
		fecha1.set(Calendar.HOUR_OF_DAY, 07);
		fecha1.set(Calendar.MINUTE, 30);
		fecha2.set(2019, 06, 29);
		fecha2.set(Calendar.HOUR_OF_DAY, 21);
		fecha2.set(Calendar.MINUTE, 30);
		Evento irAlAlamo = new Evento("AlamosNigth", "Adrogue", fecha2);
		Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
		usuario1.cargarEvento(irAlAlamo);
		usuario1.cargarEvento(developer);
		Suscripcion subs = new SuscripcionPremium();
		UsuarioRepo ur = new UsuarioRepo();
	
		usuario1.setNombreUsuario("pepeArgento");
		usuario1.setPasswordUsuario("hola123");
		ur.persist(usuario1);
		
		Usuario pepeJe = ur.getUser("pepeArgento","hola123");
		System.out.println(pepeJe.getNombreUsuario());
		System.out.println(pepeJe.getPasswordUsuario());
		
    	
	}

}
