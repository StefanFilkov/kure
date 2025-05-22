package bg.tuvarna.sit.kontrl1.service;

import bg.tuvarna.sit.kontrl1.model.dto.BookRequestDto;
import bg.tuvarna.sit.kontrl1.model.dto.BookResponseDto;
import bg.tuvarna.sit.kontrl1.model.entity.Author;
import bg.tuvarna.sit.kontrl1.model.entity.Book;
import bg.tuvarna.sit.kontrl1.model.entity.Genre;
import bg.tuvarna.sit.kontrl1.repository.AuthorRepository;
import bg.tuvarna.sit.kontrl1.repository.BookRepository;
import bg.tuvarna.sit.kontrl1.repository.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public BookResponseDto createBook(BookRequestDto dto) {
        Genre genre = genreRepository.findByName(dto.getGenre())
                .orElseGet(() -> {
                    Genre g = new Genre();
                    g.setName(dto.getGenre());
                    return genreRepository.save(g);
                });

        Set<Author> authors = dto.getAuthors().stream()
                .map(name -> authorRepository.findByName(name)
                        .orElseGet(() -> {
                            Author a = new Author();
                            a.setName(name);
                            return authorRepository.save(a);
                        }))
                .collect(Collectors.toSet());

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());
        book.setGenre(genre);
        book.setAuthors(authors);

        Book saved = bookRepository.save(book);

        return new BookResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getYear(),
                saved.getGenre().getName(),
                saved.getAuthors().stream().map(Author::getName).toList()
        );
    }

    @Override
    public List<BookResponseDto> getBooksByGenre(String genreName) {
        List<Book> books = bookRepository.findAllByGenreName(genreName);

        if (books.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Каталога не съдържа книги от жанр %s", genreName)
            );
        }

        return books.stream()
                .map(book -> new BookResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getYear(),
                        book.getGenre().getName(),
                        book.getAuthors().stream()
                                .map(Author::getName)
                                .collect(Collectors.toList())
                ))
                .toList();
    }
}
