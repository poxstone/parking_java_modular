package com.brisasparking;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



@RestController
public class MainController {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	@GetMapping(value="saludar", produces=MediaType.TEXT_PLAIN_VALUE)
	public String Saludar() {
		String sql = "INSERT INTO Cliente (nombre, apellido) VALUES (?, ?)";
        
        int result = jdbcTemplate.update(sql, "Ravi", "Ravi");
         
        if (result > 0) {
            System.out.println("A new row has been inserted.");
        }
        
		return "Hello";
	}
	
	@GetMapping(value="sumar/{num1}/{num2}")
	public double simar(@PathVariable("num1") double n1, @PathVariable("num2") double n2) {
		return n1+n2;
	}
	
	

}
