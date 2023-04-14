package com.brisasparking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.brisasparking.model.AdminIngressModel;
import com.google.gson.Gson;



@RestController
@RequestMapping("/admin")
public class AdminController {
	private static final String MOVIMIENTOS_URL = "http://localhost:8080/movimientos";
	private static final String RECAUDOS_URL = "http://localhost:8080/recaudos";
	private static final Integer VALOR_MINUTO = 90;
	
	private ResponseEntity<String> putHttp(String url, JSONObject jsonObject, HttpMethod method) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
		ResponseEntity<String> response = restTemplate.exchange(url, method, request, String.class);
		return response;
	}
	
	private ResponseEntity<String> getHttp(String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		return response;
	}
	
	private HashMap<String, Object> StringToMap(String json) {
		Gson gson = new Gson();
        HashMap<String, Object> responseMap = gson.fromJson(json.replace("[", "").replace("]", ""), HashMap.class);
        return responseMap;
	}
	
	@PutMapping("/registrarEntrada")
	public ResponseEntity<String> NewIngres(@RequestBody AdminIngressModel ingress) {
		ResponseEntity<String> responseMovimiento;
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Long entradaMillis = calendar.getTimeInMillis();
		JSONObject jsonEntrada = new JSONObject();
		jsonEntrada.put("ingreso", entradaMillis );
		jsonEntrada.put("placa", ingress.getPlaca());
		jsonEntrada.put("id_operador", ingress.getId_operador());
		
		// Validate if movimiento open exists
		responseMovimiento = getHttp(MOVIMIENTOS_URL+"/getByPlacaNotSalida/"+ingress.getPlaca());
		if (!responseMovimiento.getBody().toString().equals("[]")) {
			return responseMovimiento;
		}

		// create movimiento
		putHttp(MOVIMIENTOS_URL+"/add/", jsonEntrada, HttpMethod.PUT);
		responseMovimiento = getHttp(MOVIMIENTOS_URL+"/getByPlacaNotSalida/"+ingress.getPlaca());
		
		return responseMovimiento;
	}
	
	@PostMapping("/registrarSalida")
	public ResponseEntity<String> NewEgress(@RequestBody AdminIngressModel ingress) throws ParseException {
		ResponseEntity<String> responseRecaudo;
		// Get movimiento data
		ResponseEntity<String> responseMovimiento = getHttp(MOVIMIENTOS_URL+"/getByIdMovimiento/"+ingress.getId_movimiento());
		
		// validate if exists
		HashMap<String, Object> movimientoMap = StringToMap(responseMovimiento.getBody().toString());
		if (movimientoMap.get("salida") != null) {
			if ( !movimientoMap.get("salida").toString().isEmpty()) {
				// get recaudo by id
				responseRecaudo = getHttp(RECAUDOS_URL+"/getByMovimientoId/"+ingress.getId_movimiento());
				
				if (!responseRecaudo.getBody().toString().equals("[]")) {
					return responseRecaudo;
				}
				
			}
		}

		// Update movimiento by id
		Calendar calendarEntrada = new GregorianCalendar();
		calendarEntrada.setTime(new Date());
		Long salidaMillis = calendarEntrada.getTimeInMillis();
		JSONObject jsonSalida = new JSONObject();
		jsonSalida.put("ingreso", movimientoMap.get("ingreso"));
		jsonSalida.put("salida", salidaMillis);
		jsonSalida.put("placa", movimientoMap.get("placa"));
		jsonSalida.put("id_operador", movimientoMap.get("id_operador"));
		putHttp(MOVIMIENTOS_URL+"/edit/"+ingress.getId_movimiento(), jsonSalida, HttpMethod.POST);
		
		// Create recaudo 
		Long entradaMillis = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH).parse((String) jsonSalida.get("ingreso")).getTime();
		Long totalMinutos = (salidaMillis - entradaMillis) / 1000 / 60;
		Long totalPagar = totalMinutos * VALOR_MINUTO;
		JSONObject jsonRecaudo = new JSONObject();
		jsonRecaudo.put("id_movimiento", ingress.getId_movimiento());
		jsonRecaudo.put("total_minutos", totalMinutos);
		jsonRecaudo.put("total_pagar", totalPagar);
		jsonRecaudo.put("valor_minuto", VALOR_MINUTO);
		responseRecaudo = putHttp(RECAUDOS_URL+"/add", jsonRecaudo, HttpMethod.PUT);

   		return responseRecaudo;
	}
}
