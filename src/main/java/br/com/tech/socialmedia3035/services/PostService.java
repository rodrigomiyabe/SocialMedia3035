package br.com.tech.socialmedia3035.services;

import br.com.tech.socialmedia3035.dtos.PostDTO;
import br.com.tech.socialmedia3035.entities.Post;
import br.com.tech.socialmedia3035.repositories.PostRepository;
import br.com.tech.socialmedia3035.services.exceptions.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;

    }

    //Endpoint para criar uma postagem. O usuário poderá criar uma postagem
    //para o seu feed e para isso vai ser preciso verificar junto com a criação, o id
    //do usuário autenticado para o post estar vinculado a esse usuário.
    //Obs: Os campos title e private não deverão ser nulos.
    public PostDTO createPost(){
        return null;
    }

    public PostDTO updatePost(PostDTO postDTO, Long id){
        Post postUpdated = this.repository.findById(id).orElseThrow(() -> new EntityNotFound("Post not found!"));
        postUpdated.setUpdateAt(postDTO.getUpdateAt());
        postUpdated.setDescription(postDTO.getDescription());
        postUpdated.setPrivated(postDTO.getPrivated());
        postUpdated.setTitle(postDTO.getTitle());
        postUpdated.setPhotoLink(postDTO.getPhotoLink());
        postUpdated.setVideoLink(postDTO.getVideoLink());
        return new PostDTO(postUpdated);
    }

    public void deletePost(Long id){
        Post post = this.repository.findById(id).orElseThrow(() -> new EntityNotFound("Post not found!"));
        post.setDeleted(true);
    }

    public void alterPrivacy(Long id, PostDTO dto){
        Post post = this.repository.findById(id).orElseThrow(() -> new EntityNotFound("Post not found!"));
         post.setPrivated(dto.getPrivated());
         this.repository.save(post);
    }

    // TODO: 05/07/2023 REFAZER
    public List<PostDTO>listPostByUser(Long id){
        if(repository.findByDeletedIsFalseAndUserId(id).isEmpty()){
            throw new EntityNotFound("User does not have any post!");
        }
        else {
            return repository.findByDeletedIsFalseAndUserId(id).stream().map(PostDTO::new).collect(Collectors.toList());
        }
    }



}
