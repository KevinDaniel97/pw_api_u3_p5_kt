package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;
@Service
public class IEstudianteServiceImpl  implements IEstudianteService{
	
	@Autowired
	private IEstudianteRepository  estudianteRespository;
	
	@Override
	public void guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRespository.insertar(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRespository.actualizar(estudiante);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRespository.actualizarParcial(apellido, nombre, id);
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRespository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRespository.eliminar(id);
	}

	@Override
	public List<Estudiante> buscarTodos(String genero) {
		// TODO Auto-generated method stub
		return this.estudianteRespository.seleccionarTodos(genero);
	}

	@Override
	public List<EstudianteTO> buscarTodosTO() {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudianteRespository.seleccionarTodos("M");
		List<EstudianteTO> listaFinal = new ArrayList<>();
		for(Estudiante est:lista) {
			listaFinal.add(this.convertir(est));
		}
	
		return listaFinal;
	}
	
	private EstudianteTO convertir(Estudiante est) {
		
		EstudianteTO estuTO=new EstudianteTO();
		estuTO.setApellido(est.getApellido());
		estuTO.setFechaNacimiento(est.getFechaNacimiento());
		estuTO.setGenero(est.getGenero());
		estuTO.setId(est.getId());
		estuTO.setNombre(est.getNombre());
		return estuTO;

	}

}
