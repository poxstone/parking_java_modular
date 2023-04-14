package com.brisasparking.model;

import java.sql.Timestamp ;

import javax.persistence.*;

@Entity
@Table(name = "movimiento")
public class MovimientoModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer   id_movimiento;
	private Timestamp ingreso;
	private Timestamp salida;
	private String    placa;        // from vehiculo
	private Integer   id_operador;  // from operador
	

	public Integer getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(Integer id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public Timestamp  getIngreso() {
		return ingreso;
	}

	public void setIngreso(Timestamp  ingreso) {
		this.ingreso = ingreso;
	}

	public Timestamp  getSalida() {
		return salida;
	}

	public void setSalida(Timestamp  salida) {
		this.salida = salida;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getId_operador() {
		return id_operador;
	}

	public void setId_operador(Integer id_operador) {
		this.id_operador = id_operador;
	}

	protected MovimientoModel() {
    }
	
	public MovimientoModel(Timestamp  ingreso, String placa, Integer id_operador) {
        this.ingreso = ingreso;
        this.placa = placa;
        this.id_operador = id_operador;
    }
	
	public MovimientoModel(Timestamp  ingreso, Timestamp  salida, String placa, Integer id_operador) {
        this.ingreso = ingreso;
        this.salida = salida;
        this.placa = placa;
        this.id_operador = id_operador;
    }

}
