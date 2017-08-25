package OperationBeExtract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by yujian on 2017/8/2.
 */
public class WriteFileToTxt {

    private static Logger logger = LoggerFactory.getLogger(WriteFileToTxt.class);
    private ReadFile readFile;
    private String filePath;
    private String[] txtFilePathAndContent;
    private String txtFilePath;
    private String suffixName;
    private String fileContent;

    public WriteFileToTxt(String filePath){
        this.filePath = filePath;
        txtFilePathAndContent = new String[2];
    }

    public String[] toTxt(){
        String[] filePathPar = filePath.split("\\.");
        this.suffixName = filePathPar[filePathPar.length-1];

        switch (suffixName){
            case "docx":
                txtFilePathAndContent = writeContentToTxt();
                break;
            case "txt":
                logger.info("the file is txt format already.");
                fileContent = new ReadFile(filePath).readFileContent();
                txtFilePathAndContent[0] = filePath;
                txtFilePathAndContent[1] = fileContent;
                break;
            case "doc":
                txtFilePathAndContent = writeContentToTxt();
                break;
        }
        return txtFilePathAndContent;
    }

    private String[] writeContentToTxt(){
        readFile = new ReadFile(filePath);
        String originalFileContent = readFile.readFileContent();

        txtFilePath = filePath.replace(suffixName,"txt");
        File txtFile = new File(txtFilePath);
        if (txtFile.exists()) {
            logger.info("the file is already exit");
            txtFile.delete();
        }

        try {
            txtFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(txtFile);
            fileOutputStream.write(originalFileContent.getBytes("UTF-8"));
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        txtFilePathAndContent[0] = txtFilePath;
        txtFilePathAndContent[1] = originalFileContent;
        return txtFilePathAndContent;
    }
}
