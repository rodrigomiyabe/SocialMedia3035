package br.com.tech.socialmedia3035.controllers;

import br.com.tech.socialmedia3035.dtos.UserDTO;
import br.com.tech.socialmedia3035.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO>signUp(@RequestBody UserDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.createUser(dto));
    }

    //Endpoint para editar um usuário. O usuário vai poder editar seu perfil através
    //de um update.
    @PatchMapping("/update")
    public ResponseEntity<UserDTO>updateUser(@RequestBody UserDTO dto,@RequestParam Long id){
        return ResponseEntity.ok().body(service.updateUser(dto,id));
    }

    //● Endpoint para deletar um usuário. O usuário poderá deletar sua conta
    //através de um delete.
    @DeleteMapping("/delete")
    public ResponseEntity<Void>deleteUser(Long id){
        this.service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    //● Endpoint para listar todos os usuários não deletados. O usuário poderá ver
    //os usuários cadastrados para em uma próxima melhoria ele poder adicionar
    //como amigo em sua rede social.
    @GetMapping("list")
    public ResponseEntity<List<UserDTO>>userList(){
        return ResponseEntity.ok().body(this.service.listUserNotDeleted());
    }
    //● Criar um login com spring security usando o username e senha que deverá
    //ser criptografada quando salva no banco.



}
