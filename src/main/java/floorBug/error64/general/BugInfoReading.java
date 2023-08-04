package floorBug.error64.general;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class BugInfoReading {
    //设置表头名称
    //设置列对应的属性
    @ExcelProperty(value = "abnormal_value")
    private String bugStr;
}