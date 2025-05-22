package bg.tuvarna.sit.kontrl1.repository;

import bg.tuvarna.sit.kontrl1.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);
}
