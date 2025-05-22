package bg.tuvarna.sit.kontrl1.service;

import bg.tuvarna.sit.kontrl1.model.dto.BookRequestDto;
import bg.tuvarna.sit.kontrl1.model.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto createBook(BookRequestDto dto);
    List<BookResponseDto> getBooksByGenre(String genreName);
}
