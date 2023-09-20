package com.nico.operacionesPrueba;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nico.conexionHibernate.Cliente;

public class GuardaClientePrueba {

	public static void main(String[] args) {
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class)
				.buildSessionFactory();

		Session miSession = miFactory.openSession();

		try {
			// Registro nuevo
			Cliente cliente1 = new Cliente("Iván", "Fernández", "Malvinas");
			miSession.beginTransaction();
			miSession.save(cliente1);
			miSession.getTransaction().commit();
			System.out.println("Registro insertado correctamente");

			// Lectura registro nuevo
			miSession.beginTransaction();
			Cliente guardado = miSession.get(Cliente.class, cliente1.getId());
			miSession.getTransaction().commit();
			System.out.println("Lectura de cliente: " + guardado);
			
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
