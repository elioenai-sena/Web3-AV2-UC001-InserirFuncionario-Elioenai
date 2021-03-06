package br.ifpe.web2.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.acesso.Usuario;
import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.service.EmpresaService;
import br.ifpe.web2.service.FuncionarioService;
import br.ifpe.web2.util.ServiceException;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	public FuncionarioDAO funcDao;
	
	@Autowired
	private CargoService cargoService;

	@Autowired
	private EmpresaService empresaService;

	@GetMapping("/formFunc")
	public String exibirFormFunc(Funcionario funcionario, Model model) {
		model.addAttribute("listaCargos", this.cargoService.listarTodos(true));
		model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		model.addAttribute("listaFuncionarios", this.funcService.buscarTodos());
		
		return "/funcionario/funcionario-form";
	}

	@PostMapping("/inserirFuncionario")
	public String inserirFuncionario(@Valid Funcionario funcionario, BindingResult br,Integer codigo, Model model,
			RedirectAttributes ra, HttpSession session) {
		if (br.hasErrors()) {
			return exibirFormFunc(funcionario, model);
		}
		try {
			Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
			funcionario.setCriadoPor(usuarioLogado);
			funcionario.setAlteradorPor(usuarioLogado);

			this.funcService.inserirFuncionario(funcionario, codigo);
			ra.addFlashAttribute("mensagem", "Funcionário Cadastrado com Sucesso");
			return "redirect:/formFunc";
		} catch (ServiceException e) {
			br.addError(new ObjectError("global", e.getMessage()));
			return exibirFormFunc(funcionario, model);
		}
	}

	@GetMapping("/deletarFuncPorCodigo")
	public String deletarFuncPorCodigo(Integer codigo) {

		this.funcService.deletarFuncPorCodigo(codigo);
		return "redirect:/formFunc";
	}

	@GetMapping("/editarFuncionario")
	public String editarFuncionario(String cpf, Integer codigo, Model model, RedirectAttributes ra) {

//		ra.addAttribute("funcionario", this.funcService.buscaPorCodigoAndCpf(codigo, cpf));
//		ra.addAttribute("funcionario", this.funcService.buscaPorCodigo(codigo));
//		return "redirect:/formFunc";
		
//		model.addAttribute("funcionario", this.funcService.buscaPorCodigoAndCpf(codigo, cpf)) ;
		model.addAttribute("funcionario", this.funcService.buscaPorCodigo(codigo)) ;
		
		model.addAttribute("listaCargos", this.cargoService.listarTodos(true));
		model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		model.addAttribute("listaFuncionarios", this.funcService.buscarTodos());
		return "/funcionario/funcionario-form";
	
	}

}
