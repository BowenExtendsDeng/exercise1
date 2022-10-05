package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author BowenDeng
 */
@Data
@Getter
public class DirInfo {
    private final String path;
    private final String[] files;

    public DirInfo(String path, String[] files){
        this.path = path;
        this.files = files;
    }
}
