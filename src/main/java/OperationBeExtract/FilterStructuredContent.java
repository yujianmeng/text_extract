package OperationBeExtract;

import GetKeyMessage.GetKeyMessages;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujian on 2017/8/10.
 */
public class FilterStructuredContent {

    private String filePath;
    private String fileName;

    public FilterStructuredContent(String filePath){
        this.filePath = filePath;
        String[] filePathPar = filePath.split("\\\\");
        this.fileName = filePathPar[filePathPar.length-1];
        System.out.println(fileName);
    }

    public List filter(){
        List<List<String>> entrys = new ArrayList<>();
        List<String> structuredContents = new ReadStructuredFile().getStructuredContent(filePath);
        for (int i=4;i<structuredContents.size();i++){
            if (!StringUtils.isEmpty(structuredContents.get(i+1))&&!StringUtils.isEmpty(structuredContents.get(i+2))
                    &&!structuredContents.get(i+1).equals("问题现象")&&!structuredContents.get(i+1).equals("。")
                    &&!structuredContents.get(i+1).equals(".")
                    &&!structuredContents.get(i+1).equals("无"))
                entrys.add(getEntry(structuredContents,i));
            i=i+3;
        }
        return entrys;
    }

    private List getEntry(List<String> structuredContents,int i){
        GetKeyMessages getKeyMessages = new GetKeyMessages(fileName);
        List<String> locations = getKeyMessages.getKeyWord("location");
        String location = "";
        if (locations.size()==0)
            location = "none";
        else
            for (String l:locations)
                location += l;
        List<String> personNames = getKeyMessages.getKeyWord("personName");
        String personName = "";
        if (personNames.size()==0)
            personName = "none";
        else
            personName = personNames.get(0);

        List<String> entry = new ArrayList<>();
        entry.add(getDate());
        entry.add(location);
        entry.add(personName);
        entry.add(structuredContents.get(i+1));
        entry.add(structuredContents.get(i+2));
        entry.add(structuredContents.get(i+3));
        entry.add(fileName);
        return entry;
    }

    private String getDate(){

        String date = "";
        //get date from filename
        List<String> dates = new GetKeyMessages(filePath,fileName).getKeyWord("date");
        if (dates!=null)
            date = dates.get(0);
        else {
            //get date from file content
            dates = new GetKeyMessages(filePath, new ReadFile(filePath).readFileContent()).getKeyWord("date");
            if (dates!=null)
                date = dates.get(0);
            else date = "none";
        }
        return date;
    }

}
