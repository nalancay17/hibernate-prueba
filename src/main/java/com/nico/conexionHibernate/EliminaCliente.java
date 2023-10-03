package com.nico.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaCliente {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetalleCliente.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		int idCliente = 2; 
		try {
			session.beginTransaction();
			Cliente c = session.get(Cliente.class, idCliente);
			if (c != null)
				session.delete(c);
			session.getTransaction().commit();

			if (c != null)
				System.out.println("Cliente eliminado de la base de datos");
			else
				System.out.println("El cliente no existe");

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Ocurrió un error. Se realiza un rollback");

		} finally {
			session.close();
			factory.close();
		}
	}

}
