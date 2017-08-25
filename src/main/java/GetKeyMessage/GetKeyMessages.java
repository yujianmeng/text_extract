package GetKeyMessage;

import OperationBeExtract.SummarysPKUSUMSUM;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yujian on 2017/7/27.
 * 提取关键信息
 */
public class GetKeyMessages {

    Logger logger = LoggerFactory.getLogger(GetKeyMessages.class);
    private List keyWords;
    private String fileName;
    private String filePath;
    private String fileContent;

    public GetKeyMessages(String filePath, String fileContent){
        this.filePath = filePath;
        String[] filePathPar = filePath.split("\\\\");
        this.fileName = filePathPar[filePathPar.length-1];
        this.fileContent = fileContent;
    }

    public GetKeyMessages(String fileName){
        this.fileName = fileName;
    }
    //get the key words based on the type
    public List getKeyWord( String category){

        keyWords = new ArrayList<String>();
        switch (category){
            case "location":
                keyWords = getLocationOrPersonName("location");
                break;

            case "summary":
//                keyWords = getSummaryHanLP(fileContent);
                keyWords = getSummaryPKUSUMSUM();
                break;

            case "date":
                keyWords = matchDateString();
                break;
                
            case "personName":
                keyWords = getLocationOrPersonName("personName");
        }
        return keyWords;
    }

    //get all type key words
    public List getKeyWord(){

        keyWords = new ArrayList<List<String>>();
        List<String> locations = getLocationOrPersonName("location");
        keyWords.add(locations);
//        List<String> summarys = getSummaryHanLP();
        List<String> summarys = getSummaryPKUSUMSUM();
        keyWords.add(summarys);
        List<String> dates = matchDateString();
        keyWords.add(dates);
        return keyWords;
    }

    /**
     * (1)能匹配的年月日类型有：
     *    2014年4月19日
     *    2014年4月19号
     *    2014-4-19
     *    2014/4/19
     *    2014.4.19
     * (2)能匹配的时分秒类型有：
     *    15:28:21
     *    15:28
     *    5:28 pm
     *    15点28分21秒
     *    15点28分
     *    15点
     * (3)能匹配的年月日时分秒类型有：
     *    (1)和(2)的任意组合，二者中间可有任意多个空格
     * 如果dateStr中有多个时间串存在，只会匹配第一个串，其他的串忽略
     */
    private List<String> matchDateString() {
        try {
            List matches = new ArrayList();
            Pattern p = Pattern.compile
                    ("((\\d{4,4}[-|\\/|年|\\.])?\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)");
            Matcher matcher = p.matcher(fileContent);
            while (matcher.find()) {
                matches.add(matcher.group(0).trim());
            }
            if (matches.size() > 0)
                return matches;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //After filtering the fewer words of the paragraph, each paragraph is extracted based on the abstract
    private List<String> getSummaryHanLP() {
        List<String> summarys = new ArrayList<>();
        String[] contents = fileContent.split("\\\r|\\\n");
        List<String> usefulContents = new ArrayList<>();
        for (String content:contents){
            Pattern p = Pattern.compile("出差目的");
            Matcher matcher = p.matcher(content);
            while (matcher.find())
                summarys.add(content);
            if (content.length()>50) {
                usefulContents.add(content);
                summarys.add(HanLP.extractSummary(content,1).get(0));
            }
        }
        return summarys;
    }

    //Extract the abstract by the percentage of the entire article's paragraph, and each paragraph extracts a summary
    private List<String> getSummaryPerHanLP() {
        List<String> summarys = new ArrayList<>();
        String[] contents = fileContent.split("\\\r|\\\n");
        List<Integer> lengths = new ArrayList<>();
        for (String content:contents)
            lengths.add(content.length());
        Collections.sort(lengths);
        int minLength = lengths.get(lengths.size()-(int)(lengths.size()*0.05));
//        System.out.println(lengths);

        List<String> usefulContents = new ArrayList<>();
        for (String content:contents){
            Pattern p = Pattern.compile("出差目的");
            Matcher matcher = p.matcher(content);
            while (matcher.find())
                summarys.add(content);
            if (content.length()>minLength) {
                usefulContents.add(content);
                summarys.add(HanLP.extractSummary(content,1).get(0));
            }
        }
        return  summarys;
    }

    //PKUSUMSUM
    private List getSummaryPKUSUMSUM(){
        List<String> summarys = new ArrayList<>();

        summarys = new SummarysPKUSUMSUM().getSummarys(filePath, fileName);

        return summarys;
    }

    private List getLocationOrPersonName(String flag){
        List<String> results = new ArrayList<>();
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        List<Term> termList = segment.seg(fileName);

        for (Term term : termList){
            String nature = term.nature.toString();
            if (flag.equals("location"))
                if (nature.equals("ns")||nature.equals("nis"))//||nature.equals("nr")||nature.equals("nis"))
                    if (!results.contains(term.word))
                        results.add(term.word);
            if (flag.equals("personName"))
                if (nature.equals("nr"))
                    if (!results.contains(term.word))
                        results.add(term.word);
        }
        return results;
    }
}
