package br.com.tech.socialmedia3035.entities;

import br.com.tech.socialmedia3035.security.user.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false,updatable = false, unique = true)
    private String username;
    @Column(name = "u_name", nullable = false)
    private String name;
    @Column(name = "u_password", nullable = false)
    private String password;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "profilelink")
    private String profileLink;
    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp(source = SourceType.DB)
    private LocalDate createdAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;
    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;
    @Enumerated(EnumType.STRING)
    private Role role;

}
