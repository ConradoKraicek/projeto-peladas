package br.com.conrado.peladas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.conrado.peladas.dao.DAO;
import br.com.conrado.peladas.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private Integer usuarioId;
	
	
	@PostConstruct
	public void init() {
		
		usuarios = new DAO<Usuario>(Usuario.class).listaTodos();
		
	}

	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public void carregarUsuarioPorId() {
		this.usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuarioId);
	}

	public String gravar() {

		if(this.usuario.getId() == null) {
			new DAO<Usuario>(Usuario.class).adiciona(this.usuario);
		} else {
			new DAO<Usuario>(Usuario.class).atualiza(this.usuario);
		}

		this.usuario = new Usuario();

		return "pelada?faces-redirect=true";
	}
	
	public void remover(Usuario usuario) {
		new DAO<Usuario>(Usuario.class).remove(usuario);
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
