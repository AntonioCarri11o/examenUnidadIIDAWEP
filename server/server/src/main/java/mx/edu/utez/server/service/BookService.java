package mx.edu.utez.server.service;

import mx.edu.utez.server.Model.Book;
import mx.edu.utez.server.Model.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<Book>> getById(Long id) {
        return new CustomResponse<Optional<Book>>(this.repository.findById(id), false,200, "OK");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Book> insert(Book book){
        return new CustomResponse<>(
                this.repository.saveAndFlush(book),
                false,
                200,
                "Libro Registrado!"
        );
    }

    //Validar que existe el id
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Book> update(Book book){
        if(!this.repository.existsById(book.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El libro no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(book),
                false,
                200,
                "El Libro ha sido actualizado!"
        );
    }

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    @Transactional
    public CustomResponse<Book> delete(Long id) {
        try {
            Optional<Book> peliculaOptional = repository.findById(id);

            if (!peliculaOptional.isPresent()) {
                return new CustomResponse<>(
                        null,
                        true,
                        400,
                        "El libro no existe"
                );
            }

            repository.deleteById(id);
            return new CustomResponse<>(
                    null,
                    false,
                    200,
                    "Libro eliminado!"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomResponse<>(
                    null,
                    true,
                    500,
                    "Error interno al intentar eliminar la Libro"
            );
        }
    }


    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> OrderByAutorAsc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByAutorAsc();
        return new CustomResponse<>(
                libro,
                false,
                200,
                "Orden Autores Ascedente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> OrderByAutorDesc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByAutorDesc();
        return new CustomResponse<>(
                libro,
                false,
                200,
                "Orden Autores Descedente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> withImageUrl() {
        List<Book> libro;
        libro = this.repository.findAllByImageURLIsNotNull();
        return new CustomResponse<>(
                libro,
                false,
                200,
                "Orden fechas Ascedente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> OrderByPublishDateDesc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByPublishDateDesc();
        return new CustomResponse<>(
                libro,
                false,
                200,
                "Orden fechas Descedente"
        );
    }


}
