
	package com.example.Namma_Ooru.controller;

	import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import com.example.Namma_Ooru.service.AuthService;
@CrossOrigin(origins="*")
	@RestController
	@RequestMapping("/auth")
	public class AuthController {

	    @Autowired
	    private AuthService authService;

	    @PostMapping("/tourist/login")
	    public ResponseEntity<String> touristLogin(@RequestBody Map<String, String> body){
	        return ResponseEntity.ok(
	            authService.touristLogin(body.get("phone"), body.get("password"))
	        );
	    }

	    @PostMapping("/guide/login")
	    public ResponseEntity<String> guideLogin(@RequestBody Map<String, String> body){
	        return ResponseEntity.ok(
	            authService.guideLogin(body.get("phone"), body.get("password"))
	        );
	    }

	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<String> handleError(RuntimeException ex){
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}

