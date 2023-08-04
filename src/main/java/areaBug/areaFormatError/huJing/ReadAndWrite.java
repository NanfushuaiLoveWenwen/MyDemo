package areaBug.areaFormatError.huJing;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;


public class ReadAndWrite {
    public static List<String> primaryKeyList = new ArrayList<>();
    public static List<String> modifiedValueList = new ArrayList<>();
    public static void main(String[] args) {
        /**
         * 读操作
         */
        //设置文件名称和路径
        String ReadFileName="D:\\Users\\zinanchen\\Desktop\\工作\\面积异常数据\\面积非结构化异常\\酒店异常数据明细-1872315292.xlsx";
        //调用方法进行读操作
        EasyExcel.read(ReadFileName, BugInfoReading.class,new ExcelListener())
                    .sheet()
                    .doRead();


        /**
         * 写操作
         */
        // 向list集合添加100条数据
        List<BugInfoWriting> list = new ArrayList<>();
        for (int i = 0; i < 454; i++) {
            BugInfoWriting data = new BugInfoWriting();
            data.setPrimaryKey(primaryKeyList.get(i));
            data.setModifiedValue(modifiedValueList.get(i));
            list.add(data);
        }
        System.out.println(primaryKeyList.size());
        System.out.println(modifiedValueList.size());
        // 设置excel文件路径和文件名称
        String WriteFileName = "D:\\Users\\zinanchen\\Desktop\\工作\\面积异常数据\\面积非结构化异常\\写入测试.xlsx";

        // write方法实现写操作
        EasyExcel.write(WriteFileName, BugInfoWriting.class).sheet("用户信息")
                .doWrite(list);
    }
}

