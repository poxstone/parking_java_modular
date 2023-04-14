package com.brisasparking.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/populate")
public class PoputaleController {
	private static final String HOST_URL = "http://localhost";
	
	private ResponseEntity<String> putHttp(String url, JSONObject jsonObject, HttpMethod method) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
		ResponseEntity<String> response = restTemplate.exchange(url, method, request, String.class);
		
		return response;
	}
	
	@GetMapping("/all")
	public String  populateAll() {
		// clientes add
		JSONObject jsonCliente1 = new JSONObject();
		jsonCliente1.put("nombre", "ana");
		jsonCliente1.put("apellido", "baez");
		putHttp(HOST_URL+":9091/clientes/add", jsonCliente1, HttpMethod.PUT);
		jsonCliente1.replace("nombre", "cesar");
		jsonCliente1.replace("apellido", "diaz");
		putHttp(HOST_URL+":9091/clientes/add", jsonCliente1, HttpMethod.PUT);
		jsonCliente1.replace("nombre", "eduardo");
		jsonCliente1.replace("apellido", "faez");
		putHttp(HOST_URL+":9091/clientes/add", jsonCliente1, HttpMethod.PUT);
		jsonCliente1.replace("nombre", "gustavo");
		jsonCliente1.replace("apellido", "herrera");
		putHttp(HOST_URL+":9091/clientes/add", jsonCliente1, HttpMethod.PUT);

		// movimiento
		JSONObject jsonMovimiento1= new JSONObject();
		jsonMovimiento1.put("ingreso", "2023-01-01T10:00:00.090+00:00");
		jsonMovimiento1.put("salida", "2023-01-01T11:00:00.090+00:00");
		jsonMovimiento1.put("placa", "abc-123");
		jsonMovimiento1.put("id_operador", 1);
		putHttp(HOST_URL+":9092/movimientos/add", jsonMovimiento1, HttpMethod.PUT);
		
		// operadores add
		JSONObject jsonOperador1= new JSONObject();
		jsonOperador1.put("nombre", "walter");
		jsonOperador1.put("apellido", "mojica");
		putHttp(HOST_URL+":9093/operadores/add", jsonOperador1, HttpMethod.PUT);
		jsonOperador1.replace("nombre", "edgar");
		jsonOperador1.replace("apellido", "hernandez");
		putHttp(HOST_URL+":9093/operadores/add", jsonOperador1, HttpMethod.PUT);
		jsonOperador1.replace("nombre", "maria");
		jsonOperador1.replace("apellido", "acosta");
		putHttp(HOST_URL+":9093/operadores/add", jsonOperador1, HttpMethod.PUT);

		// recaudos
		JSONObject jsonRecaudo1= new JSONObject();
		jsonRecaudo1.put("valor_minuto", 60);
		jsonRecaudo1.put("total_minutos", 90);
		jsonRecaudo1.put("total_pagar", 5400);
		jsonRecaudo1.put("id_movimiento", 1);
		putHttp(HOST_URL+":9094/recaudos/add", jsonRecaudo1, HttpMethod.PUT);
		
		// vehiculos add
		JSONObject jsonVehiculo1= new JSONObject();
		jsonVehiculo1.put("placa", "abc-123");
		jsonVehiculo1.put("tipo_vehiculo", "cedan");
		jsonVehiculo1.put("id_cliente", 1);
		putHttp(HOST_URL+":9095/vehiculos/add", jsonVehiculo1, HttpMethod.PUT);
		jsonVehiculo1.replace("placa", "xzy-987");
		jsonVehiculo1.replace("tipo_vehiculo", "furgon");
		jsonVehiculo1.replace("id_cliente", 2);
		putHttp(HOST_URL+":9095/vehiculos/add", jsonVehiculo1, HttpMethod.PUT);
		jsonVehiculo1.replace("placa", "van-000");
		jsonVehiculo1.replace("tipo_vehiculo", "van");
		jsonVehiculo1.replace("id_cliente", 2);
		putHttp(HOST_URL+":9095/vehiculos/add", jsonVehiculo1, HttpMethod.PUT);
		jsonVehiculo1.replace("placa", "bus-999");
		jsonVehiculo1.replace("tipo_vehiculo", "bus");
		jsonVehiculo1.replace("id_cliente", 3);
		putHttp(HOST_URL+":9095/vehiculos/add", jsonVehiculo1, HttpMethod.PUT);

		return "ok";
	}
}