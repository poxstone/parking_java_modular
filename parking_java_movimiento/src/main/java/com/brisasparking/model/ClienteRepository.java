package com.brisasparking.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ClienteRepository extends CrudRepository<ClienteModel, Integer> {
    
	public List<ClienteModel> findByApellido(String apellido);
	
	public List<ClienteModel> findByNombre(String nombre);
	
    @Query(value="SELECT * FROM cliente WHERE nombre=:nombre AND apellido=:apellido", nativeQuery = true)
    public List<ClienteModel> findByNombreApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Modifying
    @Transactional
    @Query(value="UPDATE cliente SET nombre=:nombre, apellido=:apellido WHERE id_cliente=:id_cliente", nativeQuery = true)
    public void updateClient(@Param("id_cliente") Integer id_cliente, @Param("nombre") String nombre, @Param("apellido") String apellido);
  
}
