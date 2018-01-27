package br.com.itego.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf;

	public EntityManager getEntityManager() {
		emf = Persistence.createEntityManagerFactory("livrariajsf");
		return emf.createEntityManager();
	}

	public void close(EntityManager em) {
		em.close();
	}
}
