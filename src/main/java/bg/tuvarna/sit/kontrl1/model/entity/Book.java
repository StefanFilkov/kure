package bg.tuvarna.sit.kontrl1.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int year;

    @ManyToOne()
    private Genre genre;

    @ManyToMany()
    private Set<Author> authors = new HashSet<>();
}
