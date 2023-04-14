package com.brisasparking.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface VehiculoRepository extends CrudRepository<VehiculoModel, Integer> {
    
	public List<VehiculoModel> findByPlaca(String placa);
	
	//public List<VehiculoModel> findById_cliente(Integer id_cliente);
	@Query(value="SELECT * FROM vehiculo WHERE id_cliente=:id_cliente", nativeQuery = true)
    public List<VehiculoModel> findByIdCliente(@Param("id_cliente") Integer id_cliente);

    @Modifying
    @Transactional
    @Query(value="UPDATE vehiculo SET tipo_vehiculo=:tipo_vehiculo, id_cliente=:id_cliente WHERE placa=:placa", nativeQuery = true)
    public void updateVehiculo(@Param("placa") String placa, @Param("tipo_vehiculo") String tipo_vehiculo, @Param("id_cliente") Integer id_cliente);
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM vehiculo WHERE placa=:placa", nativeQuery = true)
    public void deleteByPlaca(@Param("placa") String placa);
  
}
