package com.brisasparking.model;

import javax.persistence.*;



@Entity
@Table(name = "recaudo")
public class RecaudoModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_recaudo;
	private Integer valor_minuto;
	private Integer total_minutos;
	private Integer total_pagar;
	private Integer id_movimiento;
	
	
	public Integer getId_recaudo() {
		return id_recaudo;
	}

	public void setId_recaudo(Integer id_recaudo) {
		this.id_recaudo = id_recaudo;
	}

	public Integer getValor_minuto() {
		return valor_minuto;
	}

	public void setValor_minuto(Integer valor_minuto) {
		this.valor_minuto = valor_minuto;
	}

	public Integer getTotal_minutos() {
		return total_minutos;
	}

	public void setTotal_minutos(Integer total_minutos) {
		this.total_minutos = total_minutos;
	}

	public Integer getTotal_pagar() {
		return total_pagar;
	}

	public void setTotal_pagar(Integer total_pagar) {
		this.total_pagar = total_pagar;
	}

	public Integer getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(Integer id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	protected RecaudoModel() {
    }
	
	public RecaudoModel(Integer valor_minuto, Integer total_minutos, Integer total_pagar, Integer id_movimiento) {
        this.valor_minuto = valor_minuto;
        this.total_minutos = total_minutos;
        this.total_pagar = total_pagar;
        this.id_movimiento = id_movimiento;
    }


}
