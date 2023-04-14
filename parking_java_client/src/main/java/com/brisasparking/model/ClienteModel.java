package com.brisasparking.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cliente;
	private String nombre;
	private String apellido;
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	protected ClienteModel() {
    }
	
	public ClienteModel(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
