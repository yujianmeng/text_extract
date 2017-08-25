package OperationBeExtract;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by yujian on 2017/7/26.
 */
public class ReadFile {

    private static Logger logger = LoggerFactory.getLogger(ReadFile.class);
    private String filePath;
    private String suffixName;      //文件后缀名

    public ReadFile(String filePath) {
        this.filePath = filePath;
        String[] filePathPar = filePath.split("\\.");
        this.suffixName = filePathPar[filePathPar.length-1];
    }

    public String readFileContent(){
        String fileContent="";
        switch (suffixName){
            case "docx":
                fileContent = readDocxFile();
                break;
            case "txt":
                fileContent = readTxtFile();
                break;
            case "doc":
                fileContent = readDocFile();
                break;
        }

        return fileContent;
    }

    private String readTxtFile() {
        String fileContent = "";
        try {
            File file = new File(filePath);
            boolean isError = verifyFile(file);
            if (isError) {
                logger.warn(filePath + " happened an error!");
                fileContent = "Error!";
                return fileContent;
            }

            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent += line;
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;

    }

    //not Structured
    private String readDocxFile() {
        String fileContent = "";
        try {
            File file = new File(filePath);
            boolean isError = verifyFile(file);
            if (isError) {
                logger.warn(filePath + " happened an error!");
                fileContent = "Error!";
                return fileContent;
            }
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            fileContent = extractor.getText();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    //not Structured
    private String readDocFile() {
        String fileContent = "";
        try {
            File file = new File(filePath);
            boolean isError = verifyFile(file);
            if (isError) {
                logger.warn(filePath + " happened an error!");
                fileContent = "Error!";
                return fileContent;
            }

            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            Range rang = doc.getRange();
            fileContent = rang.text();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private boolean verifyFile(File file){

        boolean isError = false;
        if(!file.exists()) {
            logger.warn(file.getName() + " is not exit");
            isError = true;
        }

        if (!file.isFile()) {
            logger.warn(file.getName() + " is not a file");
            isError = true;
        }
        return isError;
    }
}
