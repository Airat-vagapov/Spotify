package tech.itpark.jdbc.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark.jdbc.mapper.AlbumRowMapper;
import tech.itpark.jdbc.model.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AlbumManager {
    private final NamedParameterJdbcTemplate template;
    private final AlbumRowMapper rowMapper = new AlbumRowMapper();

    public List<Album> getAll() {
        return template.query(
                "SELECT id, name, authors_id, year, count, length, liked, picUrl FROM albums ORDER BY id LIMIT 50",
                rowMapper
        );
    }

    public Album getById(long id) {
        return template.queryForObject(
                "SELECT id, name, authors_id, year, count, length, liked, picUrl FROM albums WHERE id = :id",
                Map.of("id", id),
                rowMapper
        );
    }

    public List<Album> getByAuthorId(long authorId) {
        return template.query(
                "SELECT id, name, authors_id, year, count, length, liked, picUrl FROM albums WHERE authors_id = :authors_id",
                Map.of("authors_id", authorId),
                rowMapper
        );
    }

    public Album removeById(long id) {
        final Album item = getById(id);

        template.update(
                "DELETE FROM albums WHERE id = :id",
                Map.of("id", item.getId())
        );
        return item;
    }


    public Album save(Album item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(
                    "INSERT INTO albums(name, authors_id, year, count, length, liked, picUrl) VALUES (:name, :authors_id, :year, :count, :length, :liked, :picUrl)",
                    new MapSqlParameterSource(Map.of(
                            "name", item.getName(),
                            "authors_id", item.getAuthorId(),
                            "year", item.getYear(),
                            "count", item.getCount(),
                            "length", item.getLength(),
                            "liked", item.isLiked(),
                            "picUrl", item.getPicUrl()
                    )),
                    keyHolder
            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }

        template.update(
                "UPDATE albums SET name = :name, authors_id = :authors_id, year = :year, count = :count, length = :length, liked = :liked, picUrl = :picUrl WHERE id = :id",
                Map.of(
                        "id", item.getId(),
                        "name", item.getName(),
                        "authors_id", item.getAuthorId(),
                        "year", item.getYear(),
                        "count", item.getCount(),
                        "length", item.getLength(),
                        "liked", item.isLiked(),
                        "picUrl", item.getPicUrl()
                )
        );

        return getById(item.getId());
    }


    public List<Album> search(String name) {
        return template.query(
                "SELECT id, name, authors_id, year, count, length, liked, picUrl FROM albums WHERE name = :name",
                Map.of("name", name),
                rowMapper
        );
    }

}
