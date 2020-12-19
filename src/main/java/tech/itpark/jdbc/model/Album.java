package tech.itpark.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {
    private long id;
    private String name;
    private long authorId;
    private int year;
    private int count;
    private int length;
    private boolean liked;
    private String picUrl;
}

