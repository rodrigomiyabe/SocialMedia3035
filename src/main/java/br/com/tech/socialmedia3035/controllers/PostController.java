package br.com.tech.socialmedia3035.controllers;

import br.com.tech.socialmedia3035.dtos.PostDTO;
import br.com.tech.socialmedia3035.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    //Endpoint para criar uma postagem. O usuário poderá criar uma postagem
    //para o seu feed e para isso vai ser preciso verificar junto com a criação, o id
    //do usuário autenticado para o post estar vinculado a esse usuário.
    //Obs: Os campos title e private não deverão ser nulos.
    @PostMapping("/create")
    public ResponseEntity<PostDTO>createPost(@RequestBody PostDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(this.service.createPost());
    }

    //● Endpoint para atualizar a postagem. O usuário poderá atualizar o post através
    //de um update.
    @PatchMapping("/update")
    public ResponseEntity<PostDTO>updatePost(@RequestBody PostDTO dto,Long id){
        return ResponseEntity.ok().body(this.service.updatePost(dto,id));
    }


    //● Endpoint para deletar uma postagem. O usuário poderá deletar um post
    //através do delete
    @DeleteMapping("/delete")
    public ResponseEntity<Void>delete(Long id){
        this.service.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    //● Endpoint de listagem de uma postagem pelo id do usuário. O usuário poderá
    //ver todas as suas postagens através de um get.
    // TODO: 05/07/2023 REFAZER 
    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>>listPosts(Long id){
      return ResponseEntity.ok().body(this.service.listPostByUser(id));
    }

    //● Endpoint para alterar a privacidade do post. O usuário poderá alterar o post
    //de privado para público setando como false ou true.
    @PatchMapping("/alterprivacy")
    public ResponseEntity<Void>alterPrivacy(@RequestParam Long id, @RequestBody PostDTO dto){
        this.service.alterPrivacy(id,dto);
        return ResponseEntity.ok().build();
    }
}
