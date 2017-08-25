package GetKeyMessage;

import OperationBeExtract.FilterStructuredContent;
import OperationBeExtract.GetDirectory;

import java.util.List;

/**
 * Created by yujian on 2017/7/29.
 */
public class GetKeyMessageTest {

    public static void main(String[] args){


        //分类文件
        new GetDirectory().classifyFile("","","");
        //提取结构化文档中的关键信息并且入库
        //时间、地点、人物、问题描述、原因描述、解决方法、文档名称
        new Traversal().obtain("E:\\程序分类\\结构化文档");
        //将数据库中所有的问题描述提取出来写入txt文档作为训练集
        new GetPromDecFromDB().getMessageFromDB();
    }
}
