package br.com.tech.socialmedia3035.dtos;

import br.com.tech.socialmedia3035.entities.Post;
import br.com.tech.socialmedia3035.entities.User;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
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
        this.createdAt = postUpdated.getCreatedAt();
        this.title = postUpdated.getTitle();
        this.description = postUpdated.getDescription();
        this.photoLink = postUpdated.getPhotoLink();
        this.videoLink = postUpdated.getVideoLink();
        this.privated = postUpdated.getPrivated();
        this.user = postUpdated.getUser();
    }
}
