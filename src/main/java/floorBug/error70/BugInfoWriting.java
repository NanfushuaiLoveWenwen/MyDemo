package floorBug.error70;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: 创建excel对应的实体对象
 * @author Guoqianliang
 * @date 19:47 - 2021/4/14
 */
@Data
public class BugInfoWriting {
    @ExcelProperty(value = "basicRoomTypeId", index = 0)
    private String primaryKey;
    @ExcelProperty(value = "floorRange", index = 1)
    private String modifiedValue;
    @ExcelProperty(value = "originalfloorRange", index = 2)
    private String originalValue;
}


