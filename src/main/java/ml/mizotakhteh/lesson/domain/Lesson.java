package ml.mizotakhteh.lesson.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public record Lesson(@Id Long id,
                     String title,
                     Long ownerId,
                     @OneToMany(fetch = FetchType.EAGER)
                     @JoinTable(name = "lessons_books")
                     Set<Book> books,
                     String version) {
}
