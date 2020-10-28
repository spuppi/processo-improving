package com.spuppi.apirestdemo.resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spuppi.apirestdemo.model.Event;
import com.spuppi.apirestdemo.repository.EventObjectRepository;
import com.spuppi.apirestdemo.service.EventService;
import com.spuppi.exception.RequestNotAuthorizedException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/issues")
public class EventResource {

	private static final Logger log = LogManager.getLogger(EventResource.class);

	//ADICIONAR LEVEL 3 HATEOAS

	//ENDPOINTS COM CACHES PARA OBJETOS DOS EVENTOS

	//ADICIONAR TIMEOUTS

	//ADICIONAR METODO DE MERGE DE BRANCHS - POST

	//ADICIONAR SSL

	//ENCAPSULAR EM DOCKER E SUBIR NA AWS

	//ADICIONAR GITIGNORE


	@Value("${git.secret}")
	private String gitSecret;

	@Autowired
	EventService eventService;

	@Autowired
	EventObjectRepository eventObjectRepository;

	@ApiOperation(value = "Cadastrar evento")
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestHeader("X-Hub-Signature-256") String signature, @RequestBody byte[] postGit) {

		log.info("addEvent: " + postGit);

		if(!eventService.isValidPayload(signature, postGit, signature)) {
			log.error("Hash error");
			throw new RequestNotAuthorizedException("HASH error");
		}

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(eventService.saveEvent(postGit));
	}

	@ApiOperation(value = "Retorna os eventos associados a issue informada")
	@GetMapping(value="/{issue}/events", produces = "application/json")
	public ResponseEntity<?> listEvents(@PathVariable(value="issue") long issue, 
			@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(value = "size", required = false,defaultValue = "5") int size){
				
		Page<Event> events = eventService.findEventsByIssue(issue, page, size);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(events);
	}	

	@ApiOperation(value = "Retorna os documentos dos eventos associados ao usuário informada")
	@GetMapping(value="/events/documents/login/{login}", produces = "application/json")
	public ResponseEntity<?> listEventsDocsByLogin(@PathVariable(value="login") String login, 
			@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(value = "size", required = false,defaultValue = "5") int size){
		
		Page<JSONObject> events = eventService.findByEventsObjectsByLogin(login, page, size);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(events);
	}

	@ApiOperation(value = "Retorna os documentos dos eventos associados a issue informada")
	@GetMapping(value="/events/documents/issue/{issue}", produces = "application/json")
	public ResponseEntity<?> findEventsDocsByIssue(@PathVariable(value="issue") int issue, 
			@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(value = "size", required = false,defaultValue = "5") int size){
		
		Page<JSONObject> events = eventService.findByEventsObjectsByIssue(issue, page, size);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(events);
	}
}
