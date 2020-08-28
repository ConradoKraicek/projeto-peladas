package br.com.conrado.peladas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.conrado.peladas.dao.DAO;
import br.com.conrado.peladas.modelo.Pelada;
import br.com.conrado.peladas.modelo.Usuario;

@ManagedBean
@ViewScoped
public class PeladaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pelada pelada = new Pelada();
	
	private Pelada peladaSelecionada;
	
	private List<Pelada> peladas = new ArrayList<>();
	
	private List<Usuario> usuarios = new ArrayList<>();

	private Integer usuarioId;
	
	@PostConstruct
    public void init() {
		peladas =  new DAO<Pelada>(Pelada.class).listaTodos();
		usuarios = getUsuariosTodos(); 

    }
	
	public PeladaBean() {
		peladas = getPeladas();
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public Pelada getPelada() {
		return pelada;
	}
	
	public void setPelada(Pelada pelada) {
		this.pelada = pelada;
	}

	public List<Pelada> getPeladas() {
		return this.peladas;

	}

	public List<Usuario> getUsuariosTodos() {
		return new DAO<Usuario>(Usuario.class).listaTodos();
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;

	}
	
	public Pelada getPeladaSelecionada() {
		return peladaSelecionada;
	}

	public void setPeladaSelecionada(Pelada peladaSelecionada) {
		this.peladaSelecionada = peladaSelecionada;
	}

	public void carregarPeladaPelaId() {
		this.pelada = new DAO<Pelada>(Pelada.class).buscaPorId(this.pelada.getId()); 
	}
	
	public void gravarUsuario() {
		Usuario usuario = new DAO<Usuario>(Usuario.class).buscaPorId(this.usuarioId);
		this.pelada.adicionaUsuario(usuario);
	}

	public void gravar() {
        
		Usuario usuario = new DAO<Usuario>(Usuario.class).buscaPorId(this.usuarioId);
		this.pelada.adicionaUsuario(usuario);
		
		if(this.pelada.getId() == null) {
			new DAO<Pelada>(Pelada.class).adiciona(this.pelada);
		} else {
			new DAO<Pelada>(Pelada.class).atualiza(this.pelada);
		}

		peladas =  new DAO<Pelada>(Pelada.class).listaTodos();
		this.pelada = new Pelada();


	}

	public void remover() {
		new DAO<Pelada>(Pelada.class).remove(peladaSelecionada);
		peladaSelecionada = null;
		peladas =  new DAO<Pelada>(Pelada.class).listaTodos();
	}
	
	public void removerUsuario(Usuario usuario) {
		this.pelada.removeUsuario(usuario);
	}
	
	public void carregar(Pelada pelada) {
		this.pelada = pelada;
	}
	
	public String formUsuario() {
		return "usuario?faces-redirect=true";
	}

	
}
