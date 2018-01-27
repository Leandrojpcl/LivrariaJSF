package br.com.itego.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.itego.util.JPAUtil;

public class DAO<T> {
	
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void gravar(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		// abre a transação
		em.getTransaction().begin();
		
		// persiste o objeto
		em.persist(t);
		
		// commita a transação
		em.getTransaction().commit();
		
		// fecha a entity manager
		em.close();
	}

	public void remover(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		// abre a transação
		em.getTransaction().begin();
		// remove o objeto da base de dados,
		// mas o mesmo continua em memória até que o em seja fechado
		em.remove(t);
		// commita a transação
		em.getTransaction().commit();
		// fecha a entity manager
		em.close();
	}

	public void atualizar(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		// abre a transação
		em.getTransaction().begin();
		// recupera o estado Managed do objeto JPA.
		em.merge(t);
		// commita a transação
		em.getTransaction().commit();
		// fecha a entity manager
		em.close();
	}

	public T buscarPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		// abre a transação
		em.getTransaction().begin();
		// Instancia um objeto genérico que recebe a entidade do banco. 
		T instancia = em.find(classe, id);
		// commita a transação
		em.getTransaction().commit();
		// fecha a entity manager
		em.close();
		
		return instancia;
	}
	
	public List<T> listar(){
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

}

