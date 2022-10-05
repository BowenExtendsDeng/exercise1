package pojo;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

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
