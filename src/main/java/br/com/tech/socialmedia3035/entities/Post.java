package br.com.tech.socialmedia3035.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;
    @Column(name = "title", length = 50, nullable = false)
    private String title;
    @Column(name = "description", length = 200)
    private String description;
    @Column(name = "photolink")
    private String photoLink;
    @Column(name = "videolink")
    private String videoLink;
    @Column(name = "privated", nullable = false, columnDefinition = "boolean default false")
    private Boolean privated;
    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;
    @ManyToOne
    private User user;

}
