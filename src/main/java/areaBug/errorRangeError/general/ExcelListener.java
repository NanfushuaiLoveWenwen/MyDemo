package areaBug.errorRangeError.general;

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
            String mdfValue = buginfoString.substring(splitIndex+1).trim();
            ReadAndWrite.primaryKeyList.add(pKey);
            mdfValue = myRegex(mdfValue);
            ReadAndWrite.modifiedValueList.add(mdfValue);
        }

    }

    public String myRegex(String mdfValue){
        int splitIndex = mdfValue.indexOf("-");
        String higherArea = mdfValue.substring(0,splitIndex);
        String lowerArea = mdfValue.substring(splitIndex+1);
        StringBuilder resString = new StringBuilder();
        resString.append(lowerArea).append("-").append(higherArea);
        return resString.toString();
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