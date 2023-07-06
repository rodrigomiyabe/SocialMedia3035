package br.com.tech.socialmedia3035.dtos;

import br.com.tech.socialmedia3035.entities.Post;
import br.com.tech.socialmedia3035.entities.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PostDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private LocalDate createdAt;
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDate updateAt;
    @NotBlank
    private String title;
    private String description;
    private String photoLink;
    private String videoLink;
    private Boolean privated;
    private Boolean deleted;
    private User user;


    public PostDTO(Post postUpdated) {
    }
}
