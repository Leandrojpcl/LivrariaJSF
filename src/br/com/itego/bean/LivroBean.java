package br.com.itego.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.itego.dao.DAO;
import br.com.itego.modelo.Autor;
import br.com.itego.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	
	private Livro livro = new Livro();
	private Integer autorId;

	public void gravar() {
		if(livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("E necessario ao menos um autor para cadastro de Livro"));
			return;
		}
		new DAO<Livro>(Livro.class).gravar(this.getLivro());
		this.livro = new Livro();//serve para limpar o preenchimeno no xhtml
	}
	
	public List<Autor> autoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public Livro getLivro() {
        return livro;
    }
	
	public void addAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscarPorId(this.autorId);
		this.livro.adicionarAutor(autor);
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listar();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void formataISBN(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN invalido! Deve-se começar com 1"));
		}
	}
}

