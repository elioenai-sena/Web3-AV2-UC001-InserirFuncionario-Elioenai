package br.ifpe.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.cadastro.Empresa;
import br.ifpe.web2.service.EmpresaService;
import br.ifpe.web2.service.FuncionarioService;
import br.ifpe.web2.util.ServiceException;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/empresas")
	public String exibirFormEmpresa(Empresa empresa, Model model) {
		model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		return "/empresa/empresa-form";
	}

	@PostMapping("/listarFuncByEmpresa")
	public String listaFuncByEmpresa(Empresa empresa, Model model, RedirectAttributes ra) {

		try {
			model.addAttribute("listaFuncionarios", funcionarioService.buscarPorEmpresa(empresa.getCodigo()));
			model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
			return "/empresa/empresa-form";

		} catch (ServiceException e) {
			e.printStackTrace();
			model.addAttribute("mensagem", e.getMessage());
			model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		}
		return "/empresa/empresa-form";
	}

}
