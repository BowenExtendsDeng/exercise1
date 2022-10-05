package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author BowenDeng
 */
@Data
@AllArgsConstructor
@Getter
public class DirInfo {
    private final String path;
    private final String[] files;
}
