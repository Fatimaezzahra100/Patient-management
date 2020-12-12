package org.side.patients.web;

import java.util.Date;

import javax.validation.Valid;

import org.aspectj.apache.bcel.generic.RET;
import org.side.patients.dao.PatientRepository;
import org.side.patients.entites.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@GetMapping(path = "/patients")
	// ANOTATION @requestParam : on dit à spring mvc à dispatcher servlet va à la
	// requette http
	// et récupère le param getParam page et size
	public String patients(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyword", defaultValue = "") String mc) {
		// retourne une page et une page ce n'est pas une liste
		Page<Patient> listPatients = patientRepository.findByNameContains(mc, PageRequest.of(page, size));
		model.addAttribute("listPatients", listPatients.getContent());
		// pour que je puisse afficher les pages
		model.addAttribute("pages", new int[listPatients.getTotalPages()]);
		// JE VAIS STOCKER DANS LE MODEL LA PAGE COURANTE
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", mc);
		model.addAttribute("size", size);
		return "patient";
	}

	@GetMapping(path = "/deletePatient")
	public String deletePatient(Model model, Long id, String keyword, int page, int size) {
		patientRepository.deleteById(id);
		// la redirection avec le transfert de paramettre pour que lorsque je supprime
		// je reste sur la même page
		// Je reste toujourd sur le mêmes élements contextuels
		// géneralement avec la suppression c'est redirection
		return "redirect:/patients?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}

	@GetMapping(path = "/template1")
	public String template() {
		return "template1";
	}

	@GetMapping(path = "/formulairePatient")
	public String formulairePatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formulairePatient";
	}

	@GetMapping(path ="/editPatient")
	public String editPatient(Model model, Long id) {
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute("o", patient);
		return "formulairePatient";
	}

	@PostMapping(path = "/savePatient")
	public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "formulairePatient";
		patientRepository.save(patient);
		return "formulairePatient";
	}

}
