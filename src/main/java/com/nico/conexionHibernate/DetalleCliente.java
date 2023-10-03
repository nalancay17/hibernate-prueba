package com.nico.conexionHibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_cliente")
public class DetalleCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "web")
	private String web;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "comentario")
	private String comentario;

	@OneToOne(mappedBy = "detalleCliente", cascade = CascadeType.ALL)
	private Cliente cliente;

	public DetalleCliente() {

	}

	public DetalleCliente(String web, String telefono, String comentario) {
		this.web = web;
		this.telefono = telefono;
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "DetalleCliente [id=" + id + ", web=" + web + ", telefono=" + telefono + ", comentario=" + comentario
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
