package floorBug.error70;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;


public class ReadAndWrite {
    static List<String> primaryKeyList = new ArrayList<>();
    static List<String> modifiedValueList = new ArrayList<>();
    static List<String> originalValueList = new ArrayList<>();
    public static void main(String[] args) {
        /**
         * 读操作
         */
        //设置文件名称和路径
        String ReadFileName=
                "D:\\Users\\zinanchen\\Desktop\\工作\\楼层异常数据\\70\\酒店异常数据明细-759916928.xlsx";
        //调用方法进行读操作
        EasyExcel.read(ReadFileName, BugInfoReading.class,new ExcelListener())
                    .sheet()
                    .doRead();


        /**
         * 写操作
         */
        //
        List<BugInfoWriting> list = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            BugInfoWriting data = new BugInfoWriting();
            data.setPrimaryKey(primaryKeyList.get(i));
            data.setModifiedValue(modifiedValueList.get(i));
            data.setOriginalValue(originalValueList.get(i));
            list.add(data);
        }
        System.out.println(primaryKeyList.size());
        System.out.println(modifiedValueList.size());
        System.out.println(originalValueList.size());
        // 设置excel文件路径和文件名称
        String WriteFileName = "D:\\Users\\zinanchen\\Desktop\\工作\\楼层异常数据\\70\\main.xlsx";

        // write方法实现写操作
        EasyExcel.write(WriteFileName, BugInfoWriting.class).sheet("用户信息")
                .doWrite(list);
    }
}

