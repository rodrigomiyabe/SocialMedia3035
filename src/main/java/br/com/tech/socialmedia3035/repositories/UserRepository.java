package br.com.tech.socialmedia3035.repositories;

import br.com.tech.socialmedia3035.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User>findByUsername(String userName);
    Optional<User>findById(Long id);

    Boolean existsByMail(String mail);
    Boolean existsByPhone(String phone);

    List<User>findAllByDeletedFalse();



}
