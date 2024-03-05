package mx.edu.utez.server.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByOrderByAutorAsc();
    List<Book> findAllByOrderByAutorDesc();
    List<Book> findAllByOrderByPublishDateAsc();
    List<Book> findAllByOrderByPublishDateDesc();
    List<Book> findAllByImageURLIsNotNull();

}
