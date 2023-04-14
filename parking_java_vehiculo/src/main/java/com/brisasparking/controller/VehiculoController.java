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

import com.brisasparking.model.VehiculoRepository;
import com.brisasparking.model.VehiculoModel;


@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    
    @Autowired
    private VehiculoRepository repository;
   
    @GetMapping("/list")
    public List<VehiculoModel> listClients() {
        List<VehiculoModel> listado = (List<VehiculoModel>) repository.findAll();
        return listado;
    }
    
    @GetMapping("/getByPlaca/{placa}")
    public List<VehiculoModel> getVehiculo(@PathVariable String placa) {
    	List<VehiculoModel> vehiculo = repository.findByPlaca(placa);
        return vehiculo;
    }
    
    @GetMapping("/getByClientId/{id_cliente}")
    public List<VehiculoModel> getByIdCliente(@PathVariable Integer id_cliente) {
    	List<VehiculoModel> vehiculo = repository.findByIdCliente(id_cliente);
        return vehiculo;
    }
    
    @PutMapping("/add")
    public VehiculoModel addVehiculo(@RequestBody VehiculoModel vehiculo) {
    	VehiculoModel nuevoVehiculo = repository.save(vehiculo);
        return nuevoVehiculo;
    }
    
    @DeleteMapping("/del/{placa}")
    public ResponseEntity<VehiculoModel> delVehiculo(@PathVariable String placa) {
    	repository.deleteByPlaca(placa);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/edit/{placa}")
    public List<VehiculoModel> editVehiculo(@PathVariable String placa, @RequestBody VehiculoModel vehiculo) {
    	repository.updateVehiculo(placa, vehiculo.getTipo_vehiculo(), vehiculo.getId_cliente());
    	List<VehiculoModel> vehiculoUpdate = repository.findByPlaca(placa);
        return vehiculoUpdate;
    }
}

