package ml.mizotakhteh.lesson.resource;

import ml.mizotakhteh.lesson.domain.Lesson;
import ml.mizotakhteh.lesson.repository.LessonRepository;
import ml.mizotakhteh.lesson.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lessons")
public class LessonResource {

    private final Logger log = LoggerFactory.getLogger(LessonResource.class);

    private final LessonService lessonService;

    LessonResource(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllInvoices(Pageable pageable) {
        log.debug("REST request to get a page of Lessons");
        Page<Lesson> page = lessonService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Long id) {
        return new ResponseEntity<Lesson>(new Lesson(null,null,null,null,null), HttpStatus.OK);
    }
}
