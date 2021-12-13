package ml.mizotakhteh.lesson.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public record Book(@Id Long id,
                   String name,
                   @OneToMany(mappedBy = "books")
                   Set<Lesson> lessons,
                   String link) {
}
