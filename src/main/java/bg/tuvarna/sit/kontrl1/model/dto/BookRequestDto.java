package bg.tuvarna.sit.kontrl1.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookRequestDto {

    @NotBlank(message = "Заглавието не може да бъде празно")
    private String title;

    @Min(value = 1, message = "Годината трябва да бъде положително число")
    private int year;

    @NotBlank(message = "Жанрът не може да бъде празен")
    private String genre;

    @NotEmpty(message = "Списъкът с автори не може да бъде празен")
    private List<@NotBlank(message = "Името на автора не може да е празно") String> authors;
}
