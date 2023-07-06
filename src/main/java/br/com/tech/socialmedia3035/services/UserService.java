package br.com.tech.socialmedia3035.services;

import br.com.tech.socialmedia3035.dtos.UserDTO;
import br.com.tech.socialmedia3035.entities.User;
import br.com.tech.socialmedia3035.repositories.UserRepository;
import br.com.tech.socialmedia3035.services.exceptions.EntityAlreadyExists;
import br.com.tech.socialmedia3035.services.exceptions.EntityNotFound;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO createUser(UserDTO dto){
        User newUser = new User();
        if(!this.repository.findByUsername(dto.getUsername()).isEmpty()){
            throw new EntityAlreadyExists("Username already exist!");
        } else if (this.repository.existsByMail(dto.getMail())){
            throw new EntityAlreadyExists("Mail already exist!");
        } else if (this.repository.existsByPhone(dto.getPhone())){
            throw new EntityAlreadyExists("Phone already exist!");
        }
        else {
            newUser.setUsername(dto.getUsername());
            newUser.setName(dto.getName());
            newUser.setPhone(dto.getPhone());
            newUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            newUser.setMail(dto.getMail());
            newUser.setProfileLink(dto.getProfileLink());
            newUser.setDeleted(false);
            newUser.setRole(dto.getRole());
            this.repository.save(newUser);
            return new UserDTO(newUser);
        }
    }

    public UserDTO updateUser(UserDTO dto, Long id){
        User userUpdated = this.repository.findById(id).orElseThrow(() ->new EntityNotFound("User not found!"));
        userUpdated.setUpdateAt(dto.getUpdateAt());
        userUpdated.setName(dto.getName());
        if(this.repository.existsByMail(dto.getMail())){
            throw new EntityAlreadyExists("Mail already exist!");
        }else {
            userUpdated.setMail(dto.getMail());
        }
        if(this.repository.existsByPhone(dto.getPhone())){
            throw new EntityAlreadyExists("Phone already exist!");
        }else {
            userUpdated.setPhone(dto.getPhone());
        }
        userUpdated.setPassword( dto.getPassword());
        userUpdated.setProfileLink(dto.getProfileLink());
        this.repository.save(userUpdated);
        return new UserDTO(userUpdated);
    }

    public void deleteUser(Long id){
        User user = this.repository.findById(id).orElseThrow(() ->new EntityNotFound("User not found!"));
        user.setDeleted(true);
    }

    public List<UserDTO> listUserNotDeleted(){
        List<User> list = this.repository.findAllByDeletedFalse();
        return list.stream().map(x-> new UserDTO(x.getName(), x.getProfileLink())).collect(Collectors.toList());
    }

    //Criar um login com spring security usando o username e senha que deverá ser criptografada quando salva no banco.


}
