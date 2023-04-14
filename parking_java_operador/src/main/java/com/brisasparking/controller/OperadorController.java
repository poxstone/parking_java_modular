package com.brisasparking.controller;


import java.util.List;
import java.util.Optional;

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

import com.brisasparking.model.OperadorRepository;
import com.brisasparking.model.OperadorModel;

@RestController
@RequestMapping("/operadores")
public class OperadorController {
    
    @Autowired
    private OperadorRepository repository;
   
    @GetMapping("/list")
    public List<OperadorModel> listOperadors() {
        List<OperadorModel> listado = (List<OperadorModel>) repository.findAll();
        return listado;
    }
    
    @GetMapping("/getByNameLastName/{nombre}/{apellido}")
    public List<OperadorModel> getOperador(@PathVariable String nombre, @PathVariable String apellido) {
    	List<OperadorModel> Operador = repository.findByNombreApellido(nombre, apellido);
    	Operador.forEach(item -> System.out.println(item));
        return Operador;
    }
    
    @GetMapping("/getById/{id_Operador}")
    public Optional<OperadorModel> getOperador(@PathVariable Integer id_Operador) {
    	Optional<OperadorModel> Operador = repository.findById(id_Operador);
        return Operador;
    }
    
    @PutMapping("/add")
    public OperadorModel addOperador(@RequestBody OperadorModel operador) {
    	// validate if exists
    	List<OperadorModel> existOperador = repository.findByNombreApellido(operador.getNombre().toString(), operador.getApellido().toString());
    	if (!existOperador.isEmpty()) {
    		return existOperador.get(0);
    	}
    	// create operador
    	OperadorModel nuevoOperador = repository.save(operador);
        return nuevoOperador;
    }
    
    @DeleteMapping("/del/{id_Operador}")
    public ResponseEntity<OperadorModel> delOperador(@PathVariable Integer id_Operador) {
    	repository.deleteById(id_Operador);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/edit/{id_Operador}")
    public Optional<OperadorModel> editOperador(@PathVariable Integer id_Operador, @RequestBody OperadorModel Operador) {
    	repository.updateOperador(id_Operador, Operador.getApellido(), Operador.getNombre());
    	Optional<OperadorModel> OperadorUpdate = repository.findById(id_Operador);
        return OperadorUpdate;
    }
}

