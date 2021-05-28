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
import org.springframework.web.bind.annotation.RestController;

import com.emsi.demo.entities.Patient;
import com.emsi.demo.repositories.PatientRepository;
import com.fasterxml.jackson.core.sym.Name;
import com.sun.xml.bind.v2.util.EditDistance;

import javassist.compiler.ast.Keyword;

@RestController
public class RestPatientController {
	@Autowired
	private PatientRepository patientRepository;

	@GetMapping("/listPatients")
	
	public List<Patient> list(){
		return patientRepository.findAll();
		
	}
	
	@GetMapping("/patients/{id}")

	public Patient getOne(@PathVariable Long id) {
		return patientRepository.findById(id).get();
	}
}
