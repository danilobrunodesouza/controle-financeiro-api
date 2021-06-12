package br.com.danilo.controlefinanceiro.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.danilo.controlefinanceiro.controllers.form.EntryForm;
import br.com.danilo.controlefinanceiro.dto.EntryDTO;
import br.com.danilo.controlefinanceiro.models.Entry;
import br.com.danilo.controlefinanceiro.repository.EntryRepository;

@RestController
@RequestMapping("/entry")
public class EntryController {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@GetMapping
	public List<EntryDTO> getAllEntry() {
		List<Entry> entries = entryRepository.findAll();
		
		return EntryDTO.convert(entries);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EntryDTO> getEntryById(@PathVariable Long id) {
		Optional<Entry> optional = entryRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new EntryDTO(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EntryDTO> insertEntry(@RequestBody @Valid EntryForm entryForm, UriComponentsBuilder uriBuilder) {
		Entry entry = entryForm.convert();
		entryRepository.save(entry);
		
		URI uri = uriBuilder.path("/entry/{id}").buildAndExpand(entry.getId()).toUri();
		return ResponseEntity.created(uri).body(new EntryDTO(entry));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<EntryDTO> updateEntry(@PathVariable Long id, @RequestBody @Valid EntryForm entryForm, UriComponentsBuilder uriBuilder) {
		Optional<Entry> optional = entryRepository.findById(id);
		
		if(optional.isPresent()) {
			Entry entry = optional.get();
			entry.getValuesFrom(entryForm);
			return ResponseEntity.ok(new EntryDTO(entry));
		} else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeEntry(@PathVariable Long id){
		entryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
