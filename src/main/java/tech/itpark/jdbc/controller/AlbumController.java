package tech.itpark.jdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.jdbc.manager.AlbumManager;
import tech.itpark.jdbc.model.Album;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumManager albumManager;

    @GetMapping
    public List<Album> albumsGetAll() {
        return albumManager.getAll();
    }

    @GetMapping("/{id}")
    public Album getById(@PathVariable long id) {
        return albumManager.getById(id);
    }

    @GetMapping("/authors/{authorId}")
    public List<Album> getByAuthorId(@PathVariable long authorId) {
        return albumManager.getByAuthorId(authorId);
    }

    @PostMapping
    public Album save(@RequestBody Album item) {
        return albumManager.save(item);
    }

    @DeleteMapping("/{id}")
    public Album removeById(@PathVariable long id) {
        return albumManager.removeById(id);
    }

    @GetMapping("/search/")
    public List<Album> search(@RequestParam String name) {
        return albumManager.search(name);
    }

}

