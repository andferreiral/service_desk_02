package br.usjt.arqsw.dao;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Andrey
 *
 */
@Repository
public class FilaDAO {
	@PersistenceContext
	EntityManager manager;
	/*
	@Autowired
	public FilaDAO(EntityManager manager){
		this.manager = manager;	
	}
	*/
	
	@SuppressWarnings("unchecked")
	public List<Fila> listarFilas() throws IOException {
		return manager.createQuery("select f from Fila f").getResultList();
	}

	public Fila carregar(int id) throws IOException{
		return manager.find(Fila.class, id);
	}
	
	public void alterar(Fila fila) throws IOException {
		manager.merge(fila);
	}
	
	public void excluir(Fila fila) throws IOException {
		manager.remove(fila);
	}
	
}
