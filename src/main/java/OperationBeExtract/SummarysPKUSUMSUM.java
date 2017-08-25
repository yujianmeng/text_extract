package OperationBeExtract;

import PKUSUMSUM.Run;

import java.util.*;

/**
 * Created by yujian on 2017/8/2.
 */
public class SummarysPKUSUMSUM {

    private List<String> summaryFiles = new ArrayList<>();
    private List<String> summarys = new ArrayList<>();

    public List getSummarys(String filePath, String fileName){
        String summaryFilePath;
        String summaryFileName;
        Run run = new Run();

        String[] parameters = {
                "-T","1",
                "-input", filePath,
                "-output","",
                "-L","1",
                "-n","100",
                "-m","",
                "-stop","n"};

        for (int i=1; i<6;i++){
            String[] fileNameSplits = fileName.split("\\.");
            fileNameSplits[fileNameSplits.length-2] = fileNameSplits[fileNameSplits.length-2]+"_summary"+"_"+i;

            summaryFileName = "";
            for (String fileNameSplit : fileNameSplits)
                summaryFileName += fileNameSplit+".";
            summaryFileName = summaryFileName.substring(0,summaryFileName.length()-1);
            summaryFilePath = filePath.replace(fileName,summaryFileName);
            summaryFiles.add(summaryFilePath);

            parameters[5] = summaryFilePath;
            parameters[11] = i+"";

            try {
                run.getSummary(parameters);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        getSummaryFromTxt();
        return summarys;
    }

    private void getSummaryFromTxt(){
        ReadFile readFile;
        String[] splitContents;
        Map<String,String> contentMap = new HashMap<>();

        for (String filePath:summaryFiles){
            readFile = new ReadFile(filePath);
            splitContents = readFile.readFileContent().split("。");
            for (String splitContent:splitContents)
                contentMap.put(splitContent,"");
        }

        Iterator it = contentMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry) it.next();
            summarys.add(entry.getKey());
        }
    }
}
