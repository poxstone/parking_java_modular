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

import com.brisasparking.model.MovimientoRepository;
import com.brisasparking.model.MovimientoModel;


@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    
    @Autowired
    private MovimientoRepository repository;
   
    @GetMapping("/list")
    public List<MovimientoModel> listClients() {
        List<MovimientoModel> listado = (List<MovimientoModel>) repository.findAll();
        return listado;
    }
    
    @GetMapping("/getByIdMovimiento/{id_movimiento}")
    public MovimientoModel getMovimiento(@PathVariable Integer id_movimiento) {
    	List<MovimientoModel> movimiento = repository.findByIdMovimiento(id_movimiento);
        return movimiento.get(0);
    }
    
    @GetMapping("/getByIdOperador/{id_operador}")
    public List<MovimientoModel> findByIdOperador(@PathVariable Integer id_operador) {
    	List<MovimientoModel> movimiento = repository.findByIdOperador(id_operador);
        return movimiento;
    }
    
    @GetMapping("/getByPlacaNotSalida/{placa}")
    public List<MovimientoModel> findByPlacaSalida(@PathVariable String placa) {
    	List<MovimientoModel> movimiento = repository.findByPlacaSalida(placa);
        return movimiento;
    }
    
    @PutMapping("/add")
    public MovimientoModel addMovimiento(@RequestBody MovimientoModel movimiento) {
    	// validate movimiento
    	List<MovimientoModel> existMovimiento = repository.findByPlacaEntrada(movimiento.getPlaca(), movimiento.getIngreso());
    	if (!existMovimiento.isEmpty()) {
    		return existMovimiento.get(0);
    	}
    	// create movimiento
    	MovimientoModel nuevoMovimiento = repository.save(movimiento);
        return nuevoMovimiento;
    }
    
    @DeleteMapping("/del/{id_movimiento}")
    public ResponseEntity<MovimientoModel> delMovimiento(@PathVariable Integer id_movimiento) {
    	repository.deleteById(id_movimiento);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/edit/{id_movimiento}")
    public List<MovimientoModel> editMovimiento(@PathVariable Integer id_movimiento, @RequestBody MovimientoModel movimiento) {
    	repository.updateMovimiento(id_movimiento, movimiento.getIngreso(), movimiento.getSalida(), movimiento.getPlaca(), movimiento.getId_operador());
    	List<MovimientoModel> movimientoUpdate = repository.findByIdMovimiento(id_movimiento);
        return movimientoUpdate;
    }
}

