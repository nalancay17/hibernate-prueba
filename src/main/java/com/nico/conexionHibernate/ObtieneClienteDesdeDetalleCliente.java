package com.nico.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtieneClienteDesdeDetalleCliente {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetalleCliente.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		int idDetalle = 1;
		try {
			session.beginTransaction();
			DetalleCliente detalle = session.get(DetalleCliente.class, idDetalle);

			if (detalle != null) {
				System.out.println(detalle);
				System.out.println(detalle.getCliente());
			} else {
				System.out.println("El detalle no existe");
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Ocurrió un error. Se realiza un rollback");

		} finally {
			session.close();
			factory.close();
		}
	}

}
