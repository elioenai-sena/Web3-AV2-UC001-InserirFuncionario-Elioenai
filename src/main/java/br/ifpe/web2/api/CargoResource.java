package br.ifpe.web2.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.model.cadastro.Cargo;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.util.ServiceException;

@RestController
@RequestMapping("/api/cargos")
public class CargoResource {

	@Autowired
	private CargoService service;

/*	
	@GetMapping("/cargos")
	public String exibirCargos(Cargo cargo, Model model) {
		model.addAttribute("listaCargos", this.service.listarTodos(true));
		return "/cargo/cargo-form";
	}

	@PostMapping("/salvarCargo")
	public String salvarCargo(@Valid Cargo cargo, BindingResult br, Model model, RedirectAttributes ra) {

		if (br.hasErrors()) {
			// System.out.println(br.hasErrors());
			return exibirCargos(cargo, model);
		}
		try {
			this.service.inserirCargo(cargo);
			ra.addFlashAttribute("mensagem", "Cargo Cadastrado com Sucesso");
			return "redirect:/cargos";

		} catch (ServiceException e) {
			br.addError(new ObjectError("global", e.getMessage()));
			return exibirCargos(cargo, model);
		}
	}

	@GetMapping("/deletar")
	public String deletar(Integer codigo, RedirectAttributes ra) {

		try {
			this.service.deletarPorCodigo(codigo);
			return "redirect:/cargos";
			
		} catch (ServiceException e) {
			e.printStackTrace();
			ra.addFlashAttribute("mensagemCargo", e.getMessage());
			
		}
		return "redirect:/cargos";
	}
*/
	
	//Api Rest
	@GetMapping("/listarCargos")
	public List<Cargo> listarCargo(){
		return this.service.listarTodos(true);
	}
}
