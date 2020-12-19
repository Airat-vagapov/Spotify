package tech.itpark.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tech.itpark.jdbc.model.Album;
import tech.itpark.jdbc.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    public Author mapRow(ResultSet rs, int RowNum) throws SQLException {
        return new Author(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("picUrl")
        );
    }
}
