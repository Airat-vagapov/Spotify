package tech.itpark.jdbc.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark.jdbc.mapper.AuthorRowMapper;
import tech.itpark.jdbc.model.Album;
import tech.itpark.jdbc.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorManager {
    private final NamedParameterJdbcTemplate template;
    private final AuthorRowMapper rowMapper = new AuthorRowMapper();

    public List<Author> getAll() {
        return template.query(
                "SELECT id, name, picUrl FROM authors ORDER BY id LIMIT 50",
                rowMapper
        );
    }

    public Author getById(long id) {
        return template.queryForObject(
                "SELECT id, name, picUrl FROM authors WHERE id = :id",
                Map.of("id", id),
                rowMapper
        );
    }

    public Author removeById(long id) {
        final Author item = getById(id);

        template.update(
                "DELETE FROM authors WHERE id = :id",
                Map.of("id", item.getId())
        );
        return item;
    }

    public Author save(Author item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(
                    "INSERT INTO authors(name, picUrl) VALUES (:name, :picUrl)",
                    new MapSqlParameterSource(Map.of(
                            "name", item.getName(),
                            "picUrl", item.getPicUrl()
                    )),
                    keyHolder
            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }

        template.update(
                "UPDATE albums SET name = :name, picUrl = :picUrl WHERE id = :id",
                Map.of(
                        "id", item.getId(),
                        "name", item.getName(),
                        "picUrl", item.getPicUrl()
                )
        );
        return getById(item.getId());
    }

}
