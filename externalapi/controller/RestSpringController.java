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
		String uri="https://randomuser.me/api/?format=json&gender=".concat(user.getGender());
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri,String.class);
		
		JSONObject jsonObject= new JSONObject(result);
		JSONObject jsonObject1 = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("name");
		String firstname=jsonObject1.get("first").toString();
		String lastname=jsonObject1.get("last").toString();
		jsonObject1.put("first", "WeXL"+firstname);
		jsonObject1.put("last", "WeXL"+lastname);
		jsonObject.getJSONArray("results").getJSONObject(0).put("name", jsonObject1);
		return ResponseEntity.ok(jsonObject.toString());
	}
}
