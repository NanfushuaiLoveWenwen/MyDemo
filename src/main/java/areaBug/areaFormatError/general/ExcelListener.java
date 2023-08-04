package areaBug.areaFormatError.general;

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

    //正则表达式处理异常
    public String myRegex(String mdfValue){
        if(mdfValue.endsWith("未知")){
            return "0";
        }
        /**
         * '㎡' 13217
         * '未' 26410
         */
        char[] chars = mdfValue.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='.'
                    ||chars[i]=='+'
                    ||chars.length==1
                    || chars[i] == 'N'
                    || chars[i] == 26410
                    || chars[i] == '&'
                    || chars[i] == '、'
                    || chars[i] == '*'){
                return "0";
            }

           //'㎡' 13217
            if(chars[chars.length-1] == 13217){
                return mdfValue.substring(0, chars.length-1);
            }
            // M2
            if (chars[chars.length - 2] == 'M' && chars[chars.length - 1] == '2') {
                return mdfValue.substring(0, chars.length-2);
            }
        }
        //'0' 48
        //'9' 57
        // 将面积区间替换成 -
        int state = 0;
        StringBuilder resString = new StringBuilder();
        for (int i1 = 0; i1 < chars.length; i1++) {
            if(chars[i1] == 32422){
                continue;
            }
            if(state == 0){
                if(chars[i1]>47 && chars[i1]<58){
                    resString.append(chars[i1]);
                }
                if(chars[i1]<=47 || chars[i1] >=58){
                    state++;
                    resString.append('-');
                }
            }else{
                if(chars[i1]<=47 || chars[i1] >=58){
                    continue;
                }else {
                    resString.append(chars[i1]);
                }
            }
        }

        // 针对上一组的问题 开头或者结尾有-时
        if(resString.charAt(0) == '-'){
            return resString.toString().substring(1);
        }else if(resString.charAt(resString.length()-1) == '-'){
            return resString.toString().substring(0,resString.length()-1);
        }
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