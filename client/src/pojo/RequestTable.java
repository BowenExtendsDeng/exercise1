package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author BowenDeng
 */
@Data
@Getter
public class RequestTable {
    private final String filePath;
    private final int mode;

    public RequestTable(String filePath, int mode){
        this.mode = mode;
        this.filePath = filePath;
    }

    public RequestTable(int mode){
        this.mode = mode;
        filePath = "";
    }
}
