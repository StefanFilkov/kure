package bg.tuvarna.sit.kontrl1.controller;

import bg.tuvarna.sit.kontrl1.model.dto.BookRequestDto;
import bg.tuvarna.sit.kontrl1.model.dto.BookResponseDto;
import bg.tuvarna.sit.kontrl1.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto dto) {
        BookResponseDto response = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> getBooksByGenre(@PathVariable String genre) {
        List<BookResponseDto> books = bookService.getBooksByGenre(genre);
        if (books.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Каталога не съдържа книги от жанр " + genre);
        }
        return ResponseEntity.ok(books);
    }
}
