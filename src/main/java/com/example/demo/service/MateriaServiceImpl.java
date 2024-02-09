package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IMateriaRepository;
import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;
@Service
public class MateriaServiceImpl implements IMateriaService {
	
	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public List<MateriaTO> buscarPorIdEstudiante(Integer id) {
		// TODO Auto-generated method stub
		List<Materia> ls = this.materiaRepository.seleccionarPorIdEstudiante(id);
		List<MateriaTO> lsFinal = new ArrayList<>();
		for(Materia mat : ls) {
			lsFinal.add(this.convertir(mat));
		}
		return lsFinal;
	}
	
	private MateriaTO convertir(Materia materia) {
		MateriaTO mat = new MateriaTO();
		mat.setCredito(materia.getCredito());
		mat.setId(materia.getId());
		mat.setNombre(materia.getNombre());
		return mat;
	}

}
