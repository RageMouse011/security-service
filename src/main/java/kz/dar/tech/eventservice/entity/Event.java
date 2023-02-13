package kz.dar.tech.eventservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "section", nullable = false)
    private String section;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @Column(name = "comment")
    private List<Comment> comments;

}
