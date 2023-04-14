package com.brisasparking.model;

import javax.persistence.*;

@Entity
@Table(name = "vehiculo")
public class VehiculoModel {
	@Id
	private String placa;
	private String tipo_vehiculo;
	private Integer id_cliente;  // from cliente
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	protected VehiculoModel() {
    }
	
	public VehiculoModel(String placa, String tipo_vehiculo, Integer id_cliente) {
        this.placa = placa;
        this.tipo_vehiculo = tipo_vehiculo;
        this.id_cliente = id_cliente;
    }

}
