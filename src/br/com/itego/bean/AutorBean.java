package br.com.itego.bean;

import javax.faces.bean.ManagedBean;

import br.com.itego.dao.DAO;
import br.com.itego.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		new DAO<Autor>(Autor.class).gravar(this.getAutor());
		this.autor = new Autor(); // serve para limpar preenchimento do input do formulario
	}
}
