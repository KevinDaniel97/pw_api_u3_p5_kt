package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Profesor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class ProfesorRespositoryImpl implements IProfesorRepository{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void insertar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.persist(profesor);
	}

	@Override
	public void actualizar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.merge(profesor);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		//SQL: UPDATE profesor  p set p.prof_nombre=:valor, p.prof_apellido=:valor2....
		Query query=this.entityManager
				.createQuery("UPDATE Profesor p SET p.nombre = :valor1, p.apellido =:valor2 WHERE p.id =:valor3");
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", apellido);
		query.setParameter("valor3", id);
		query.executeUpdate();
		
	}

	@Override
	public Profesor seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Profesor.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionar(id));
	}

	@Override
	public List<Profesor> seleccionarTodos(String genero) {
		TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p WHERE p.genero = :variable", Profesor.class);
		myQuery.setParameter("variable", genero);
        return myQuery.getResultList();
	}

}
