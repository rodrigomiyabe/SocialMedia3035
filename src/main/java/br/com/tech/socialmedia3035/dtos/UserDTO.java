package br.com.tech.socialmedia3035.dtos;

import br.com.tech.socialmedia3035.entities.User;
import br.com.tech.socialmedia3035.security.auth.RegisterRequest;
import br.com.tech.socialmedia3035.security.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "This field can't be null!")
    private String username;

    @NotBlank(message = "This field can't be null!")
    private String name;

    @NotBlank(message = "This field can't be null!")
    private String password;

    @NotBlank(message = "This field can't be null!")
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-] +@ [a-zA-Z0-9.-]+$",message = "Invalid Mail")
    private String mail;

    @NotBlank(message = "This field can't be null!")
    @Size(min = 9, max = 13)
    private String phone;

    private String profileLink;

    @UpdateTimestamp(source = SourceType.DB)
    private LocalDate updateAt;

    private Boolean deleted;

    private Role role;

    public UserDTO(String userName, String name, String password, String mail, String phone) {
        this.username = userName;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.mail = user.getMail();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.profileLink = user.getProfileLink();
        this.role = user.getRole();
    }

    public UserDTO(String name, String profileLink) {
        this.name = name;
        this.profileLink = profileLink;
    }

    public UserDTO(RegisterRequest request) {
        this.name = request.getName();
        this.mail = request.getMail();
        this.phone = request.getPhone();
        this.password = request.getPassword();
        this.username = request.getUsername();
        this.role = Role.ADMIN;
    }

    public UserDTO(UserDetails userDetails){
        this.id = Long.valueOf(userDetails.getUsername());
        this.password = userDetails.getPassword();
    }

}
