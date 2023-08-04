package areaBug.errorRangeError.general;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: 创建excel对应的实体对象
 * @author Guoqianliang
 * @date 19:47 - 2021/4/14
 */
@Data
public class BugInfoWriting {
    @ExcelProperty(value = "basicRoomTypeId", index = 1)
    private String primaryKey;
    @ExcelProperty(value = "areaRange", index = 2)
    private String modifiedValue;
}


