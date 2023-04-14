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

import com.brisasparking.model.ClienteRepository;
import com.brisasparking.model.ClienteModel;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteRepository repository;
   
    @GetMapping("/list")
    public List<ClienteModel> listClients() {
        List<ClienteModel> listado = (List<ClienteModel>) repository.findAll();
        listado.forEach(item -> System.out.println(item));
        return listado;
    }
    
    @GetMapping("/getByNameLastName/{nombre}/{apellido}")
    public List<ClienteModel> getClient(@PathVariable String nombre, @PathVariable String apellido) {
    	List<ClienteModel> cliente = repository.findByNombreApellido(nombre, apellido);
    	cliente.forEach(item -> System.out.println(item));
        return cliente;
    }
    
    @GetMapping("/getById/{id_cliente}")
    public Optional<ClienteModel> getClient(@PathVariable Integer id_cliente) {
    	Optional<ClienteModel> cliente = repository.findById(id_cliente);
        return cliente;
    }
    
    @PutMapping("/add")
    public ClienteModel addClient(@RequestBody ClienteModel cliente) {
    	// validate if exists
    	List<ClienteModel> existCliente = repository.findByNombreApellido(cliente.getNombre().toString(), cliente.getApellido().toString());
    	if (!existCliente.isEmpty()) {
    		return existCliente.get(0);
    	}
    	//Cliente nuevoCliente = repository.save(new Cliente("juanito", "alimania"));
    	ClienteModel nuevoCliente = repository.save(cliente);
        return nuevoCliente;
    }
    
    private void findByNombreApellido(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	@DeleteMapping("/del/{id_cliente}")
    public ResponseEntity<ClienteModel> delClient(@PathVariable Integer id_cliente) {
    	repository.deleteById(id_cliente);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/edit/{id_cliente}")
    public Optional<ClienteModel> editClient(@PathVariable Integer id_cliente, @RequestBody ClienteModel cliente) {
    	repository.updateClient(id_cliente, cliente.getApellido(), cliente.getNombre());
    	Optional<ClienteModel> clienteUpdate = repository.findById(id_cliente);
        return clienteUpdate;
    }
}

