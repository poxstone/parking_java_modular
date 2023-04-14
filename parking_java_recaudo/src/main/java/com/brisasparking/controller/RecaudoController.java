package com.brisasparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brisasparking.model.RecaudoRepository;
import com.brisasparking.model.RecaudoModel;


@RestController
@RequestMapping("/recaudos")
public class RecaudoController {
    
    @Autowired
    private RecaudoRepository repository;
   
    @GetMapping("/list")
    public List<RecaudoModel> listRecaudo() {
        List<RecaudoModel> listado = (List<RecaudoModel>) repository.findAll();
        return listado;
    }
    
    @GetMapping("/getByRecaudoId/{id_recaudo}")
    public List<RecaudoModel> getRecaudo(@PathVariable Integer id_recaudo) {
    	List<RecaudoModel> recaudo = repository.findByIdRecaudo(id_recaudo);
        return recaudo;
    }
    @GetMapping("/getByMovimientoId/{id_movimiento}")
    public List<RecaudoModel> findByIdMovimiento(@PathVariable Integer id_movimiento) {
    	List<RecaudoModel> recaudo = repository.findByIdMovimiento(id_movimiento);
        return recaudo;
    }
    
    @PutMapping("/add")
    public RecaudoModel addRecaudo(@RequestBody RecaudoModel recaudo) {
    	
    	// validate if exists
    	List<RecaudoModel> existRecaudo = repository.findByIdMovimiento(Integer.parseInt(recaudo.getId_movimiento().toString()));
    	if (!existRecaudo.isEmpty()) {
    		return existRecaudo.get(0);
    	}
    	// insert new
    	RecaudoModel nuevoRecaudo = repository.save(recaudo);
        return nuevoRecaudo;
    }
    
    @DeleteMapping("/del/{id_recaudo}")
    public ResponseEntity<RecaudoModel> delRecaudo(@PathVariable Integer id_recaudo) {
    	repository.deleteById(id_recaudo);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/edit/{id_recaudo}")
    public List<RecaudoModel> editRecaudo(@PathVariable Integer id_recaudo, @RequestBody RecaudoModel recaudo) {
    	repository.updateRecaudo(id_recaudo, recaudo.getValor_minuto(), recaudo.getTotal_minutos(), recaudo.getTotal_pagar(), recaudo.getId_movimiento());
    	List<RecaudoModel> recaudoUpdate = repository.findByIdMovimiento(id_recaudo);
        return recaudoUpdate;
    }
}

