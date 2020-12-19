package tech.itpark.jdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.jdbc.manager.AuthorManager;
import tech.itpark.jdbc.model.Album;
import tech.itpark.jdbc.model.Author;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorManager authorManager;

    @GetMapping
    public List<Author> authorsGetAll() {
        return authorManager.getAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable long id) {
        return authorManager.getById(id);
    }

    @PostMapping
    public Author save(@RequestBody Author item) {
        return authorManager.save(item);
    }

    @DeleteMapping("/{id}")
    public Author removeById(@PathVariable long id) {
        return authorManager.removeById(id);
    }

}
