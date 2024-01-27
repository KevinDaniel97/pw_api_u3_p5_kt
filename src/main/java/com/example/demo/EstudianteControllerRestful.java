package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="estudiantes")
public class EstudianteControllerRestful {

	@Autowired
	private IEstudianteService estudianteService;
	//GET
	@GetMapping(path ="/buscar")
	public Estudiante buscar() {
		return this.estudianteService.buscar(1);
	}
	//http://localhost:8080/API/v1.0Matricula/estudiantes/buscar
	
	@PostMapping(path="/guardar")
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	//http://localhost:8080/API/v1.0Matricula/estudiantes/guardar

}
