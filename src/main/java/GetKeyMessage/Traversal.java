package GetKeyMessage;

import DBConnection.KeyMessageToDB;
import OperationBeExtract.FilterStructuredContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujian on 2017/8/11.
 * Description:Traversal all files and return all names of the accidents
 */
public class Traversal {

    static Logger logger = LoggerFactory.getLogger(Traversal.class);

    public List obtain(String path){
        File file = new File(path);
        List<List<String>> entrys =  traversalFiles(file);
//        writeProblemDesToTxt(entrys,"D:\\华自\\test\\SegmentTest");
        boolean result = new KeyMessageToDB().toDB(entrys);
        if (result)
            logger.info("save messages to DB success!");
        else logger.warn("save messages to DB failed!");
        return entrys;
        }


    private List traversalFiles(File file){
        List<List<String>> entrys = new ArrayList<>();
        File[] files = file.listFiles();
        for (File f : files){
            if (f.isDirectory())
                traversalFiles(f);
            if (f.isFile()) {
                entrys.addAll(new FilterStructuredContent(f.getAbsolutePath()).filter());
//                System.out.println(entrys.toString());
            }
        }
        return entrys;
    }

    private void writeProblemDesToTxt(List<List<String>> entrys,String writePath){
        File dirs = new File(writePath);
        if (dirs.exists()) {
            logger.info("the file is already exit");
            dirs.delete();
        }
        try {
            dirs.mkdirs();
//            file.createNewFile();
//            FileWriter writer = new FileWriter(filePath,true);
            String problemDec = "";
            String fileName = "";
            FileOutputStream fileOutputStream = null;
            for (int i=1;i<=entrys.size();i++){
                problemDec = entrys.get(i-1).get(3);
                fileName = i+".txt";
                fileOutputStream = new FileOutputStream(writePath+File.separator+fileName);
                fileOutputStream.write(problemDec.getBytes("utf-8"));
                fileOutputStream.flush();
            }
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}