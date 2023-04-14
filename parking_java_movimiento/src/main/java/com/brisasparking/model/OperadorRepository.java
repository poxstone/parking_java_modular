package com.brisasparking.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface OperadorRepository extends CrudRepository<OperadorModel, Integer> {
    
	public List<OperadorModel> findByApellido(String apellido);
	
	public List<OperadorModel> findByNombre(String nombre);
	
    @Query(value="SELECT * FROM operador WHERE nombre=:nombre AND apellido=:apellido", nativeQuery = true)
    public List<OperadorModel> findByNombreApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Modifying
    @Transactional
    @Query(value="UPDATE operador SET nombre=:nombre, apellido=:apellido WHERE id_operador=:id_operador", nativeQuery = true)
    public void updateOperador(@Param("id_operador") Integer id_operador, @Param("nombre") String nombre, @Param("apellido") String apellido);
  
}
