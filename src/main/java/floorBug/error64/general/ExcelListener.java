package floorBug.error64.general;

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
            case "半地下":
            case "主楼半地下":
            case "半地下室":
            case "半地下 有窗":
                return "BM";
            case "一层":
            case "一楼":
                return "1";
            case "二层":
            case "二楼":
                return "2";
            case "负一":
            case "负一层":
            case "地下一":
            case "地下负一":
                return "B1";
            case "负二":
            case "负二层":
            case "地下二":
            case "地下负二":
                return "B2";

            default:
                return "";
        }
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