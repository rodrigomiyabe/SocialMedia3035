package br.com.tech.socialmedia3035.services;

import br.com.tech.socialmedia3035.dtos.UserDTO;
import br.com.tech.socialmedia3035.entities.User;
import br.com.tech.socialmedia3035.repositories.UserRepository;
import br.com.tech.socialmedia3035.security.user.UserSecurity;
import br.com.tech.socialmedia3035.services.exceptions.EntityAlreadyExists;
import br.com.tech.socialmedia3035.services.exceptions.EntityNotFound;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private BCrypt bCrypt;
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

    public UserDTO updateUser(UserDTO dto, UserSecurity userSecurity){
        User userUpdated = this.repository.findByUsername(userSecurity.getUsername()).orElseThrow(() ->new EntityNotFound("User not found!"));
        userUpdated.setUpdateAt(LocalDate.now());
        if(dto.getName().isBlank()){
            userUpdated.setName(userUpdated.getName());
        }else {
            userUpdated.setName(dto.getName());
        }
        if(dto.getMail().isBlank()){
            userUpdated.setMail(userUpdated.getMail());
        }
        else {
            if(this.repository.existsByMail(dto.getMail())){
                throw new EntityAlreadyExists("Mail already exist!");
            }
            else {
                userUpdated.setMail(dto.getMail());
            }
        }
        if(dto.getPhone().isBlank()){
            userUpdated.setPhone(userUpdated.getPhone());
        }else {
            if(this.repository.existsByPhone(dto.getPhone())){
                throw new EntityAlreadyExists("Phone already exist!");
            }else {
                userUpdated.setPhone(dto.getPhone());
            }
        }
        //verificar, pois desse jeito retorna a senha do usuario
        if(dto.getPassword().isBlank()){
            userUpdated.setPassword(userUpdated.getPassword());
        }else {
            userUpdated.setPassword( bCryptPasswordEncoder.encode(dto.getPassword()));
        }
        userUpdated.setProfileLink(dto.getProfileLink());
        this.repository.save(userUpdated);
        return new UserDTO(userUpdated);
    }

    public void deleteUser(Long id){
        User user = this.repository.findById(id).orElseThrow(() ->new EntityNotFound("User not found!"));
        user.setDeleted(true);
        repository.save(user);
    }

    public List<UserDTO> listUserNotDeleted(){
        List<User> list = this.repository.findAllByDeletedFalse();
        return list.stream().map(x-> new UserDTO(x.getName(), x.getProfileLink())).collect(Collectors.toList());
    }

}
