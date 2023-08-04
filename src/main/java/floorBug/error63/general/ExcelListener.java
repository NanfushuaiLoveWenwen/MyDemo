package floorBug.error63.general;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<BugInfoReading> {

    //一行一行读取excel内容，把每行内容封装到User对象中
    //从excel第二行还是读取(第一行是表头)
    @Override
    public void invoke(BugInfoReading buginfo, AnalysisContext context) {
        //        用两组List分别存储id与异常值
    String buginfoString = buginfo.getBugStr();
    int splitIndex = buginfoString.indexOf("/");
        if(splitIndex!=1){
            String pKey = buginfoString.substring(0, splitIndex).trim();
            String originalValue = buginfoString.substring(splitIndex+1).trim();
            ReadAndWrite.primaryKeyList.add(pKey);
            ReadAndWrite.originalValueList.add(originalValue);
            String mdfValue = myRegex(originalValue);
            ReadAndWrite.modifiedValueList.add(mdfValue);
        }
    }

    //正则表达式处理异常
    public String myRegex(String originalValue){
        switch (originalValue){
            case "一":
                return "1";
            case "二":
                return "2";
            case "三":
                return "3";
            case "四":
                return "4";
            case "五":
                return "5";
            case "六":
                return "6";
            case "七":
                return "7";
            case "八":
                return "8";
            case "九":
                return "9";
            case "十":
                return "10";
        }
        return originalValue;
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }
    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完毕");
    }
}