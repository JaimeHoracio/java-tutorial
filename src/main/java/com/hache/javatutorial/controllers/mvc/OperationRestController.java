package com.hache.javatutorial.controllers.mvc;

import com.hache.javatutorial.dto.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 curl http://localhost:8080/api/operation/path/2
 curl http://localhost:8080/api/operation/param
 curl http://localhost:8080/api/operation/param-required //fail
 curl http://localhost:8080/api/operation/param-required?id=3
 curl http://localhost:8080/api/operation/path-required //fail
 curl http://localhost:8080/api/operation/path-required/4
 curl -X POST http://localhost:8080/api/operation/post -H "Content-type: application/json" -d '{ "id": "4", "name": "horacio", "age": 41 }'

**/

@RestController
@RequestMapping("/api/operation")
public class OperationRestController {

    @GetMapping("/hello")
    public String getTest() {
        return "Hola mundo";
    }

    @GetMapping("/path/{id}")
    public String pathVariableTest(@PathVariable(required = false) String id) {
        return "Identificador pathVariable: " + id;
    }

    @GetMapping("/path-required/{id}")
    public String pathVariableRequiredTest(@PathVariable String id) {
        return "Identificador pathVariable: " + id;
    }

    @GetMapping("/param")
    public String requestParamTest(@RequestParam(required = false) String id) {
        return "Identificador requestParam: " + id;
    }

    @GetMapping("/param-required")
    public String requestParamRequiredTest(@RequestParam String id) {
        return "Identificador requestParam: " + id;
    }

    @PostMapping("post")
    public ResponseEntity<Persona> postTest(@RequestBody Persona persona) {
        return ResponseEntity.ok().body(persona);
    }


}
