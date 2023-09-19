package com.nico.conexionHibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConsultaCliente {

	public static void main(String[] args) {
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class)
				.buildSessionFactory();

		Session miSession = miFactory.openSession();
		String consulta1 = "From Cliente";
		String consulta2 = "FROM Cliente c WHERE c.apellido='Gómez'";
		String consulta3 = "FROM Cliente c WHERE c.apellido = 'Delgado' OR c.direccion = 'Gran Vía'";

		try {
			ejecutarQuery(miSession, consulta1);  // obtenemos todos los clientes
			ejecutarQuery(miSession, consulta2); // obtenemos todos los clientes con apellido 'Gómez'
			ejecutarQuery(miSession, consulta3);  // obtener los que viven en Gran Vía o se apellidan Delgado
		} catch (Exception e) {
			miSession.getTransaction().rollback();
			System.out.println("Ocurrió un error. Se realiza rollback");
			e.printStackTrace();
		} finally {
			miSession.close();
			miFactory.close();
		}
	}

	private static void ejecutarQuery(Session s, String query) throws Exception {
		List<Cliente> clientes;
		try {
			s.beginTransaction();
			clientes = s.createQuery(query).getResultList();
			s.getTransaction().commit();
			clientes.forEach(c -> System.out.println(c));
		} catch (Exception e) {
			throw e;
		}
	}

}
