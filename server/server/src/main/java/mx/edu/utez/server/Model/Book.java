package mx.edu.utez.server.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String autor;
    @Column(columnDefinition = "DATE", nullable = false)
    LocalDate publishDate;
    @Column(columnDefinition = "LONGTEXT", name = "imageurl")
    String imageURL;

}
