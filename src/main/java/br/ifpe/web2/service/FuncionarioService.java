package br.ifpe.web2.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.util.ServiceException;
import br.ifpe.web2.util.Util;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	

	public void inserirFuncionario(Funcionario funcionario) throws ServiceException {

		Funcionario funcExistente = this.funcionarioDAO.findByCpf(funcionario.getCpf());

		if (funcExistente != null) {
			throw new ServiceException("Já existe um funcionário cadastrado com este CPF");
		}
		if (Util.calcularIdade(funcionario.getDataNascimento()) < 18) {
			throw new ServiceException("Funcionário deve ter 18 anos ou mais");
		}

		funcionario.setDataCriacao(new Date());
		funcionario.setDataUltimaAtualizacao(new Date());
		this.funcionarioDAO.save(funcionario);
	}

	public List<Funcionario> buscarTodos() {
		return this.funcionarioDAO.findAll();
	}

	public List<Funcionario> buscarPorEmpresa(Integer empresaCodigo) throws ServiceException {
		
		List<Funcionario> listaFunc = this.funcionarioDAO.findByEmpresaCodigo(empresaCodigo);
		if (listaFunc.size() > 0) {
			return listaFunc;
		} else {
			throw new ServiceException("Não temos Funcionario Cadastrado para esse Empresa");
		}

	}
	
	public void deletarFuncPorCodigo(Integer id) {
		this.funcionarioDAO.deleteById(id);
	}

	public Optional<Funcionario> buscarFuncPorId(Integer id) {
		return funcionarioDAO.findById(id);
	}
	
	

}
