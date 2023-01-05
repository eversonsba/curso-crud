package br.com.springboot.curso_crud.controllers;

import java.util.List;

import org.hibernate.annotations.Parameter;
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

import br.com.springboot.curso_crud.model.Usuario;
import br.com.springboot.curso_crud.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
    
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	
    	
    	usuarioRepository.save(usuario);
    	
    	return "Hello " + name + "!";
    }
    @GetMapping(value="listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }
    
    @PostMapping(value="salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario usuarioSalvo = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value="delete")
    @ResponseBody
    public ResponseEntity <String> delete(@RequestParam Long idUser){
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("Usuario Deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value="buscaruserid")
    @ResponseBody
    public ResponseEntity <Usuario> buscarId(@RequestParam(name="idUser") Long idUser){
    	Usuario usuarioBuscado = usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(usuarioBuscado, HttpStatus.OK);
    }
    
    @PutMapping(value="atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
        	return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
    	}
    	
    	Usuario usuarioSalvo = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

}
