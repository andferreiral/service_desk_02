package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Andrey
 *
 */
@Repository
public class ChamadoDAO {
	@PersistenceContext
	EntityManager manager;
	/*
	@Autowired
	public ChamadoDAO(EntityManager manager){
		this.manager = manager;
	}
	*/
	
	public int criarChamado(Chamado chamado) {
		manager.persist(chamado);
		return chamado.getNumero();
	}

	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(Fila fila) {
		fila = manager.find(Fila.class, fila.getId());
		
		String jpsql = "select c from Chamado c where c.fila= :fila and c.status = :status";
		
		Query query = manager.createQuery(jpsql);
		query.setParameter("fila", fila);
		query.setParameter("status", Chamado.ABERTO);
		
		List<Chamado> result = query.getResultList();
		
		return result;
	}

	public List<Chamado> listarChamados() throws IOException{
		return manager.createQuery("select c from Chamado c").getResultList();
	}

}
