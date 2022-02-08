package com.springbootapp.externalapi.controller;


import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springbootapp.externalapi.requestBody.User;

@RestController
public class RestSpringController {
	@GetMapping(value="/hello",
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity getClient(@RequestBody User user) throws JSONException {
		//construct URI
		String uri="https://randomuser.me/api/?format=json&gender=".concat(user.getGender());
		
		//Declaring RestTemplate
		RestTemplate rt = new RestTemplate();
		
		//fetch HTTP response and convert it into string
		String result = rt.getForObject(uri,String.class);
		
		// convert string data into JSON objects
		JSONObject jsonObject= new JSONObject(result);
		JSONObject jsonObject1 = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("name");
		
		//fetching value of first name
		String firstname=jsonObject1.get("first").toString();
		
		//fetching value of last name
		String lastname=jsonObject1.get("last").toString();
		
		//Modify value of first name by appending it to 'WeXL'
		jsonObject1.put("first", "WeXL"+firstname);
		
		//Modify value of last name by appending it to 'WeXL'
		jsonObject1.put("last", "WeXL"+lastname);
		
		//adding it to main JSON Object
		jsonObject.getJSONArray("results").getJSONObject(0).put("name", jsonObject1);
		
		//providing modified JSON object to client in form of Response Entity
		return ResponseEntity.ok(jsonObject.toString());
	}
}
