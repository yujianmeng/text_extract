package OperationBeExtract;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yujian on 2017/8/10.
 */
public class ReadStructuredFile {

    Logger logger = LoggerFactory.getLogger(ReadStructuredFile.class);

    public List getStructuredContent(String filePath){
        return readWord(filePath);
    }

    private List readWord(String filePath){
        List<String> structuredContent = new ArrayList<>();
        try{
            FileInputStream in = new FileInputStream(filePath);
            if(filePath.toLowerCase().endsWith("docx")){
                //word 2007 docx the picture will not be read
                XWPFDocument xwpf = new XWPFDocument(in);
                //List<XWPFParagraph> listParagraphs = xwpf.getParagraphs();//get the paragraphs message
                Iterator<XWPFTable> it = xwpf.getTablesIterator();
                while(it.hasNext()){
                    XWPFTable table = it.next();
                    List<XWPFTableRow> rows=table.getRows();
                    for (int i = 0; i < rows.size()-2; i++) {
                        XWPFTableRow  row = rows.get(i);
                        List<XWPFTableCell> cells = row.getTableCells();
                        if (cells.size()==6){
                            for (int j = 0; j < cells.size()-2; j++) {
                                XWPFTableCell cell=cells.get(j);
//                            System.out.println(cell.getText());
                                structuredContent.add(cell.getText());
                            }
                        }
                    }

                }
            }else if (filePath.toLowerCase().endsWith("doc")){
                //office2003  doc
                POIFSFileSystem pfs = new POIFSFileSystem(in);
                HWPFDocument hwpf = new HWPFDocument(pfs);
                Range range = hwpf.getRange();
                TableIterator it = new TableIterator(range);
                while (it.hasNext()) {
                    Table tb = (Table) it.next();
                    for (int i = 0; i < tb.numRows(); i++) {
                        TableRow tr = tb.getRow(i);
                        if (tr.numCells()==6) {
                            for (int j = 0; j < tr.numCells()-2; j++) {
                                TableCell td = tr.getCell(j);

                                String s = "";
                                for (int k = 0; k < td.numParagraphs(); k++) {
                                    Paragraph para = td.getParagraph(k);
                                    s = s+para.text();
                                    if (null != s && !"".equals(s)) {
                                        s = s.substring(0, s.length() - 1);
                                    }
//                                    System.out.println(s);
                                }
                                structuredContent.add(s);
                            }
                        }
                    }
                }
            }else {
                logger.info("error,the format is not correct！");
//                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return structuredContent;
    }
}
