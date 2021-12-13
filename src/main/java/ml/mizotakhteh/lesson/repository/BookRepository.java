package ml.mizotakhteh.lesson.repository;

import ml.mizotakhteh.lesson.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
