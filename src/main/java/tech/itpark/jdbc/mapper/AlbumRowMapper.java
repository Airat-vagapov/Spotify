package tech.itpark.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tech.itpark.jdbc.model.Album;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumRowMapper implements RowMapper<Album> {
    public Album mapRow(ResultSet rs, int RowNum) throws SQLException {
        return new Album(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("authors_id"),
                rs.getInt("year"),
                rs.getInt("count"),
                rs.getInt("length"),
                rs.getBoolean("liked"),
                rs.getString("picUrl")
        );
    }
}

