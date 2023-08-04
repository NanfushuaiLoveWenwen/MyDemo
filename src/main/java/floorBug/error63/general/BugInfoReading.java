package floorBug.error63.general;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class BugInfoReading {
    //设置表头名称
    //设置列对应的属性
    @ExcelProperty(value = "异常字段值")
    private String bugStr;
}