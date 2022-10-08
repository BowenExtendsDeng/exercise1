package pojo;

import lombok.Data;
import lombok.Getter;

/**
 * @author BowenDeng
 */
@Data
@Getter
public class Response {
    private int code;
    private int mode;
    private String msg;
    private String[] fileName;
    private String[] type;
}
