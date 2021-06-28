package br.com.springboot.crud_springboot.controllers;

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

import br.com.springboot.crud_springboot.model.Usuario;
import br.com.springboot.crud_springboot.repository.UsuarioRepository;


@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	    
    @GetMapping(value = "listatodos")
    @ResponseBody   
    public ResponseEntity<List<Usuario>> listaUsuarios(){
		
    	List<Usuario> users = usuarioRepository.findAll();
    	 
    	return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
    }
    
    
    @PostMapping(value= "salvar")			
    @ResponseBody  							
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    
    @PutMapping(value= "atualizar")			
    @ResponseBody  							
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não encontrado", HttpStatus.CREATED);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    
    
    @DeleteMapping(value= "delete")			
    @ResponseBody  							
    public ResponseEntity<String> delete(@RequestParam Long iduser){
		
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso!", HttpStatus.OK);
    }
    

    @GetMapping(value= "buscaruserid")			
    @ResponseBody  								
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){
		
    	Usuario user = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    
    @GetMapping(value= "buscarnome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarnome(@RequestParam(name = "nome") String name){
    	
    	List<Usuario> user = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }
}


