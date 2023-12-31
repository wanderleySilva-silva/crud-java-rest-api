package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

@RestController
public class UsuarioController {
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping(value = "salvar")/* Mapeia a URL*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
	
    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "delete")/* Mapeia a URL*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete(@RequestParam Long iduser){/*Recebe os dados para salvar*/
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaruserid")/* Mapeia a URL*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid(@RequestParam (name = "iduser") Long iduser){/*Recebe os dados para salvar*/
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @PutMapping(value = "atualizar")/* Mapeia a URL*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarPorNome")/* Mapeia a URL*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome){/*Recebe os dados para salvar*/
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}

