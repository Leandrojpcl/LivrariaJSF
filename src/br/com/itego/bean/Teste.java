package br.com.itego.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.itego.modelo.Autor;

public class Teste {

	public static void main(String[] args) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("livrariajsf");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();//abertura do banco
		
		Autor autor = new Autor();
		autor.setNome("Leandro");
		em.persist(autor);
		
		em.getTransaction().commit();//fechamento do banco
		
		em.close();
		emf.close();
	}

}
