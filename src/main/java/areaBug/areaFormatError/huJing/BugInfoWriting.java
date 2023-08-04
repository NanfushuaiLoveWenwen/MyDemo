package areaBug.areaFormatError.huJing;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: 创建excel对应的实体对象
 * @author Guoqianliang
 * @date 19:47 - 2021/4/14
 */
@Data
public class BugInfoWriting {
    @ExcelProperty(value = "主键ID", index = 3)
    private String primaryKey;
    @ExcelProperty(value = "修改主题值", index = 6)
    private String modifiedValue;
}


