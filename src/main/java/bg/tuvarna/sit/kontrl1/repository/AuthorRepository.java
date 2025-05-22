package bg.tuvarna.sit.kontrl1.repository;

import bg.tuvarna.sit.kontrl1.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
}
