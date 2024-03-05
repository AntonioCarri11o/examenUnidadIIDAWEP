package mx.edu.utez.server.DTO;

import mx.edu.utez.server.Model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long id;
    private String name;
    private String autor;
    LocalDate publishDate;
    private String imageURL;

    public Book getBook(){
        return new Book(
                getId(),
                getName(),
                getAutor(),
                getPublishDate(),
                getImageURL()
        );
    }
}
