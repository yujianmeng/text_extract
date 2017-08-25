package OperationBeExtract;

import java.io.File;
import java.util.List;

/**
 * Created by yujian on 2017/8/11.
 */
public class GetDirectory {

    public void classifyFile(String path,String structPath,String unStructPath){
        File file = new File(path);
        getDirectory(file,structPath,unStructPath);
    }
    // 递归遍历
    private void getDirectory(File file,String structPath,String unStructPath) {
        int count = 0;
        File flist[] = file.listFiles();
        if (flist == null || flist.length == 0) {
//            return null;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                //这里将列出所有的文件夹
                System.out.println("Dir==>" + f.getAbsolutePath());
                getDirectory(f,structPath,unStructPath);
            } else {
                //这里将列出所有的文件
                System.out.println("file==>" + f.getAbsolutePath());
                List<String> structuredContent = new ReadStructuredFile().getStructuredContent(f.getAbsolutePath());
                if (structuredContent.size()>3)
                    moveFile(f.getAbsolutePath(),structPath);
                else
                    moveFile(f.getAbsolutePath(),unStructPath);
                count++;
            }
        }
        System.out.println(count);
    }

    private void moveFile(String absolutePath, String moveDirectory) {
        try {
            File afile = new File(absolutePath);
            System.out.println(absolutePath+"-----"+afile.getName());
            if (afile.renameTo(new File(moveDirectory + afile.getName()))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
