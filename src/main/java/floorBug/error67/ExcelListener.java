package floorBug.error67;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<BugInfoReading> {

    //一行一行读取excel内容，把每行内容封装到User对象中
    //从excel第二行还是读取(第一行是表头)
    @Override
    public void invoke(BugInfoReading buginfo, AnalysisContext context) {
        //        用两组List分别存储id与异常值
        String buginfoString = buginfo.getBugStr();
        int splitIndex = buginfoString.indexOf("/");
        if (splitIndex != 1) {
            String pKey = buginfoString.substring(0, splitIndex).trim();
            String originalValue = buginfoString.substring(splitIndex + 1).trim();
            ReadAndWrite.primaryKeyList.add(pKey);
            ReadAndWrite.originalValueList.add(originalValue);
            String mdfValue = myRegex(originalValue);
            ReadAndWrite.modifiedValueList.add(mdfValue);
        }
    }

    //正则表达式处理异常
    public String myRegex(String originalValue) {
        if(originalValue.equals("半地下")){
            return "BM";
        }
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("");
        char[] chars = originalValue.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '到' || chars[i] == '至' || chars[i] == '一' || chars[i]=='-') {
                chars[i] = '-';
                stringList.set(0,"-");
            }
            if(chars[i] == '负'){
                chars[i] = 'B';
            }
            if(chars[i] == '和' || chars[i] == ',' || chars[i]=='、'){
                stringList.set(0, ",");
            }
        }
        String tempString1 = new String(chars);

        List<String> tempStrings2 = myGetNums(tempString1, stringList);
        if(tempStrings2.size() == 1){
            return "";
        }
        if(tempStrings2.size() == 2){
            return tempStrings2.get(1);
        }
        if(tempStrings2.size() == 3){
            String icon = tempStrings2.get(0);
            String s1 = tempStrings2.get(1);
            String s2 = tempStrings2.get(2);
            if(s1.compareTo(s2) == 0){
               return "";
            }else if(s1.length() > s2.length() ||
                    ((s1.length()==s2.length())&& s1.compareTo(s2)>0)){
                String sstemp = new String(s1);
                s1 = new String(s2);
                s2 =  sstemp;
            }
            return s1+icon+s2;
        }
        return "";
    }

    // 从字段中截出两组数字
    public List<String> myGetNums(String originalValue, List<String>stringList) {
        ArrayList<String> strings = new ArrayList<>(stringList);
        int index = 0;
        //第一个数字
        char[] chars = originalValue.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= '9' && chars[i] >= '0') {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(chars[i]);
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[j] <= '9' && chars[j] >= '0') {
                        sb1.append(chars[j]);
                    } else {
                        index = j;
                        break;
                    }
                    index = j;
                }
                index = Math.max(index, i+1);
                strings.add(sb1.toString());
                break;
            }
        }
        for(int i=index+1 ; i<chars.length ; i++) {
            if(chars[i]<='9'&&chars[i]>='0'){
                StringBuilder sb2 = new StringBuilder();
                sb2.append(chars[i]);
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[j] <= '9' && chars[j] >= '0') {
                        sb2.append(chars[j]);
                    }else{
                        index = j;
                        break;
                    }
                    index = j;
                }
                index = Math.max(index, i+1);
                strings.add(sb2.toString());
                break;
            }
        }
        for(int i = index+1 ; i<chars.length ; i++){
            if (strings.size()==3 && chars[i] <= '9' && chars[i] >= '0') {
                return new ArrayList<>();
            }
        }
        return strings;
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