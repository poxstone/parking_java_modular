package com.brisasparking.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RecaudoRepository extends CrudRepository<RecaudoModel, Integer> {
	
	@Query(value="SELECT * FROM recaudo WHERE id_recaudo=:id_recaudo", nativeQuery = true)
    public List<RecaudoModel> findByIdRecaudo(@Param("id_recaudo") Integer id_recaudo);
	
	@Query(value="SELECT * FROM recaudo WHERE id_movimiento=:id_movimiento", nativeQuery = true)
    public List<RecaudoModel> findByIdMovimiento(@Param("id_movimiento") Integer id_movimiento);

    @Modifying
    @Transactional
    @Query(value="UPDATE recaudo SET valor_minuto=:valor_minuto, total_minutos=:total_minutos, total_pagar=:total_pagar, id_movimiento=:id_movimiento WHERE id_recaudo=:id_recaudo", nativeQuery = true)
    public void updateRecaudo(@Param("id_recaudo") Integer id_recaudo, @Param("valor_minuto") Integer valor_minuto, @Param("total_minutos") Integer total_minutos, @Param("total_pagar") Integer total_pagar, @Param("id_movimiento") Integer id_movimiento);
  
}
