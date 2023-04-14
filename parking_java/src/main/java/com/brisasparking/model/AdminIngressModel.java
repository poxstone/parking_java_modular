package com.brisasparking.model;

public class AdminIngressModel {
	private Integer id_movimiento;
	private Integer id_operador;
	private Integer id_cliente;
	private String  placa;
	
	public Integer getId_movimiento() {
		return id_movimiento;
	}
	
	public void setId_movimiento(Integer id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	
	public Integer getId_operador() {
		return id_operador;
	}

	public void setId_operador(Integer id_operador) {
		this.id_operador = id_operador;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	
	protected AdminIngressModel() {
    }
	
	public AdminIngressModel(Integer id_operador, Integer id_cliente, String placa) {
		this.id_operador = id_operador;
		this.id_cliente = id_cliente;
		this.placa = placa;
    }
}
