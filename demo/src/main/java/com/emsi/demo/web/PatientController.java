package com.emsi.demo.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emsi.demo.entities.Patient;
import com.emsi.demo.repositories.PatientRepository;
import com.fasterxml.jackson.core.sym.Name;
import com.sun.xml.bind.v2.util.EditDistance;

import javassist.compiler.ast.Keyword;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	@GetMapping(path = "/index")
	public String index(){
		return "index";
	}
	
	@GetMapping(path = "/patients")
	public String list(Model Model, 
			@RequestParam(name="page",defaultValue ="0") int page,
			@RequestParam(name="size",defaultValue="10") int size,
			@RequestParam(name="keyword",defaultValue ="") String mc){
		Page<Patient> pagePatients = patientRepository.findByNomContains(mc,PageRequest.of(page, size));
		Model.addAttribute("patients",pagePatients.getContent());
		Model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
		Model.addAttribute("currentPage",page);
		Model.addAttribute("size",size);
		Model.addAttribute("keyword",mc);
		return "patients";
	}
	@GetMapping(path = "/deletePatient")
	public String delete(Long id,String keyword,int page,int size){
		patientRepository.deleteById(id);
		return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
	}
	
	@GetMapping(path ="/formPatient")
	public String formPatient(Model model) {	
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode","new");
		return"formPatient";
	}
	
	@GetMapping(path ="/editPatient")
	public String editPatient(Model model,Long id) {
		Patient patient=patientRepository.findById(id).get();
		model.addAttribute("patient", patient);
		model.addAttribute("mode","update");
		return"formPatient";
		
	}
	@PostMapping("/savePatient")
	public String savePatient(Model model,@Valid Patient patient, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return "formPatient";
		patientRepository.save(patient);
		model.addAttribute("patient",patient);
		return "confirmation";
	}
	
}
