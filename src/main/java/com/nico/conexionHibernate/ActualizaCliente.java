package com.nico.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ActualizaCliente {

	public static void main(String[] args) {
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class)
				.buildSessionFactory();

		Session miSession = miFactory.openSession();

		try {
			actualizarRegistro(miSession);
			eliminarRegistro(miSession);
		} catch (Exception e) {
			miSession.getTransaction().rollback();
			System.out.println("Fallo en la transacción. Se realiza rollback");
			e.printStackTrace();
		}finally {
			miSession.close();
			miFactory.close();
		}
	}

	private static void actualizarRegistro(Session s) throws Exception {
		// Actualización cliente 1era forma
		int id = 2;
		s.beginTransaction();
		Cliente cliente = s.get(Cliente.class, id);
		cliente.setNombre("Esteban");

		// actualización 2da forma
		s.createQuery("UPDATE Cliente SET apellido = 'Alancay' WHERE id = '2'").executeUpdate();
		s.getTransaction().commit();
		System.out.println("Actualización exitosa");
	}

	private static void eliminarRegistro(Session s) throws Exception {
		// Eliminar cliente 1era forma
		int id = 3;
		s.beginTransaction();
		Cliente cliente = s.get(Cliente.class, id);
		s.delete(cliente);

		// Eliminar 2da forma
		s.createQuery("DELETE Cliente WHERE direccion = 'Goya'").executeUpdate();
		s.getTransaction().commit();
		System.out.println("Borrado exitoso");
	}

}
