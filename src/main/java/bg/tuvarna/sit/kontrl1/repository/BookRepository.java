package bg.tuvarna.sit.kontrl1.repository;

import bg.tuvarna.sit.kontrl1.model.entity.Author;
import bg.tuvarna.sit.kontrl1.model.entity.Book;
import bg.tuvarna.sit.kontrl1.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByGenreName(String genreName);
}
