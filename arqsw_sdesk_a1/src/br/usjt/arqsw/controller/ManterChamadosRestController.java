package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

@RestController
public class ManterChamadosRestController {
	private ChamadoService cService;
	private FilaService fService;

	@Autowired
	public ManterChamadosRestController(ChamadoService cs, FilaService fs) {

		this.cService = cs;
		this.fService = fs;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados")
	public @ResponseBody List<Chamado> listarChamados() {
		List<Chamado> chamados = null;
		try {
			chamados =  cService.listarChamados();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return chamados;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody List<Chamado> listarChamados(@PathVariable("id") Long id) {
		List<Chamado> chamados = null;
		try {
			Fila fila = fService.carregar(id.intValue());
			chamados =  cService.listarChamados(fila);
		} catch (IOException e) {
			e.printStackTrace();

		}
		return chamados;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="rest/chamado")
	public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado){
		try {
			int id = cService.criarChamado(chamado);
			chamado.setNumero(id);
			return new ResponseEntity(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
