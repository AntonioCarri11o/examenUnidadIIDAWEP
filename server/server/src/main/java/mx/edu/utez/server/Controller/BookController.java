package mx.edu.utez.server.Controller;

import mx.edu.utez.server.DTO.BookDTO;
import mx.edu.utez.server.Model.Book;
import mx.edu.utez.server.service.BookService;
import mx.edu.utez.server.service.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    BookService service;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/orderAutorByAsc/")
    public ResponseEntity<List<Book>> finbyAutorAsc() {
        return new ResponseEntity(
                this.service.withImageUrl(),
                HttpStatus.OK);
    }

    @GetMapping("/orderAutorByDesc/")
    public ResponseEntity<List<Book>> finbyAutorDesc() {
        return new ResponseEntity(
                this.service.OrderByAutorDesc(),
                HttpStatus.OK);
    }

    @GetMapping("/imageUrl/")
    public ResponseEntity<List<Book>> wtihPhotocover() {
        return new ResponseEntity(
                this.service.withImageUrl(),
                HttpStatus.OK);
    }

    @GetMapping("/orderDatesByDesc/")
    public ResponseEntity<List<Book>> finbyDateDesc() {
        return new ResponseEntity(this.service.OrderByPublishDateDesc(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Book>> insert(@RequestBody BookDTO dto) {
        return new ResponseEntity<>(
                this.service.insert(dto.getBook()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Book>> update(@RequestBody BookDTO dto) {
        return new ResponseEntity<>(
                this.service.update(dto.getBook()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<CustomResponse<Book>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(
                this.service.delete(id),
                HttpStatus.CREATED
        );
    }
}
