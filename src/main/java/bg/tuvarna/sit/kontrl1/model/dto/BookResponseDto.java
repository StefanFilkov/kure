package bg.tuvarna.sit.kontrl1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private int year;
    private String genre;
    private List<String> authors;
}

