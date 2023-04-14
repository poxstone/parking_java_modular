package com.brisasparking.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface MovimientoRepository extends CrudRepository<MovimientoModel, Integer> {
	
	@Query(value="SELECT * FROM movimiento WHERE id_movimiento=:id_movimiento", nativeQuery = true)
    public List<MovimientoModel> findByIdMovimiento(@Param("id_movimiento") Integer id_movimiento);
	
	@Query(value="SELECT * FROM movimiento WHERE id_operador=:id_operador", nativeQuery = true)
    public List<MovimientoModel> findByIdOperador(@Param("id_operador") Integer id_operador);
	
	@Query(value="SELECT * FROM movimiento WHERE placa=:placa AND ingreso=:ingreso", nativeQuery = true)
    public List<MovimientoModel> findByPlacaEntrada(@Param("placa") String placa, @Param("ingreso") Timestamp ingreso);
	
	@Query(value="SELECT * FROM movimiento WHERE placa=:placa AND salida is NULL", nativeQuery = true)
    public List<MovimientoModel> findByPlacaSalida(@Param("placa") String placa);

    @Modifying
    @Transactional
    @Query(value="UPDATE movimiento SET ingreso=:ingreso, salida=:salida, placa=:placa, id_operador=:id_operador WHERE id_movimiento=:id_movimiento", nativeQuery = true)
    public void updateMovimiento(@Param("id_movimiento") Integer id_movimiento, @Param("ingreso") Timestamp ingreso, @Param("salida") Timestamp salida, @Param("placa") String placa, @Param("id_operador") Integer id_operador);
  
}
