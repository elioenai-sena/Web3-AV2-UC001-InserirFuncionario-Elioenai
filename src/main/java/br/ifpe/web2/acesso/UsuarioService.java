package br.ifpe.web2.acesso;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.util.ServiceException;
import br.ifpe.web2.util.Util;

@Service
public class UsuarioService {

	private static final String DEFAULT_PASSWORD = "user123";

	@Autowired
	private UsuarioDAO usuarioDAO;

	public void criarUsuarioAdmin() {
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("admin");
		usuario.setSenha("adm123");
		usuario.setPerfil(Perfil.ADMINISTRADOR);
		usuario.setNome("Administrador");
		usuario.setSituacaoUsuario(SituacaoUsuario.ATIVO);

		try {
			inserirUsuario(usuario);
		} catch (ServiceException e) {
			System.out.println("Usuário admin já existe");
		}
	}

	public void criarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("consulta");
		usuario.setSenha("c123");
		usuario.setPerfil(Perfil.APENAS_CONSULTA);
		usuario.setNome("Apenas_Consulta");
		usuario.setSituacaoUsuario(SituacaoUsuario.ATIVO);

		try {
			inserirUsuario(usuario);
		} catch (ServiceException e) {
			System.out.println("Usuário " + usuario.getLogin() + " já existe");
		}
	}

	public void resetSenha(String login) throws ServiceException {

		Usuario usuario = this.usuarioDAO.findByLogin(login);
		if (usuario != null) {
			usuario.setSenha(Util.md5(DEFAULT_PASSWORD));
			this.usuarioDAO.save(usuario);
		} else {
			throw new ServiceException("Login Não existe!!!");
		}

	}

	public void inserirUsuario(Usuario usuario) throws ServiceException {
		usuario.setSenha(Util.md5(usuario.getSenha()));
		usuario.setDataCriacao(new Date());
		if (this.usuarioDAO.existsByLogin(usuario.getLogin()) == false) {
			this.usuarioDAO.save(usuario);
		} else {
			throw new ServiceException("Já existe usuário com este login");
		}
	}

	public long obterQuantidade() {
		return this.usuarioDAO.count();
	}
}
