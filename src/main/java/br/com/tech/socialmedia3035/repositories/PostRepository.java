package br.com.tech.socialmedia3035.repositories;

import br.com.tech.socialmedia3035.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post>findById(Long id);

    List<Post>findByDeletedIsFalseAndUserId(Long id);






}
