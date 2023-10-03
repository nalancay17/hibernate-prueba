package com.nico.conexionHibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaPedido {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetalleCliente.class)
				.addAnnotatedClass(Pedido.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		int idCliente = 5;
		Date fecha = new Date(System.currentTimeMillis());
		Pedido p1 = new Pedido(fecha, "Efectivo");
		Pedido p2 = new Pedido(fecha, "Crédito");
		Pedido p3 = new Pedido(fecha, "Débito");

		try {
			session.beginTransaction();
			Cliente c = session.get(Cliente.class, idCliente);

			p1.setCliente(c);
			p2.setCliente(c);
			p3.setCliente(c);

			session.save(p1);
			session.save(p2);
			session.save(p3);

			session.getTransaction().commit();
			System.out.println("Pedidos insertados correctamente");

			System.out.println("Lectura de pedidos");
			c.getPedidos().forEach(p -> System.out.println(p));			

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Ocurrió un error. Se realiza un rollback");

		} finally {
			session.close();
			factory.close();
		}
	}

}
