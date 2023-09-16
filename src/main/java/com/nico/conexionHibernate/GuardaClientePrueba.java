package com.nico.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GuardaClientePrueba {

	public static void main(String[] args) {
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class)
				.buildSessionFactory();

		Session miSession = miFactory.openSession();

		try {
			Cliente cliente1 = new Cliente("Pablo", "Sabatino", "Washington");
			miSession.beginTransaction();
			miSession.save(cliente1);
			miSession.getTransaction().commit();
			System.out.println("Registro insertado correctamente");
		} catch (Exception e) {
			miSession.getTransaction().rollback();
			System.out.println("Fallo en la transacción. Se realiza rollback");
			e.printStackTrace();
		}finally {
			miSession.close();
			miFactory.close();
		}
	}
}
