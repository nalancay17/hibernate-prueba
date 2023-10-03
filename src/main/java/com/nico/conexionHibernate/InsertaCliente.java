package com.nico.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaCliente {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetalleCliente.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		Cliente c1 = new Cliente("Paco", "Gómez", "Princesa");
		DetalleCliente detalle1 = new DetalleCliente("www.pildorasinformaticas.es", "78754", "Segundo cliente");
		c1.setDetalleCliente(detalle1);

		try {
			session.beginTransaction();
			session.save(c1);
			session.getTransaction().commit();
			System.out.println("Cliente creado correctamente");

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Ocurrió un error. Se realiza un rollback");

		} finally {
			session.close();
			factory.close();
		}
	}

}
