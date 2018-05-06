package authorize.jaeho.yoon.common.util;

import java.io.FileInputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;   
import org.apache.poi.xwpf.usermodel.XWPFParagraph;   
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.w3c.dom.Document;

public class MicrosoftWordDocReader {

    /*public static String[] readDocFile(String fileName) {
    	 String[] paragraphs = null;
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor we = new WordExtractor(doc);
            paragraphs = we.getParagraphText();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paragraphs;
    }*/
     
     public static void newWordDoc(String filename, String[] fileContent)   
           throws Exception {   
    	 
    	 System.out.println(fileContent);
         XWPFDocument document = new XWPFDocument();   
         XWPFParagraph tmpParagraph = document.createParagraph();   
         XWPFRun tmpRun = tmpParagraph.createRun();  

        for (int i = 0; i < fileContent.length; i++) {
        
        	tmpRun.setText(fileContent[i]);
        	tmpRun.addCarriageReturn();
        }
         FileOutputStream fos = new FileOutputStream(new File("C:/"+filename + ".doc"));   
         document.write(fos);   
         fos.close();   
     }   
 

	public static String[] readDocFile(String fileName) {
        /*String FilePath = fileName+".doc";*/
		String[] paragraphs = null;
        try {
            FileInputStream fis = new FileInputStream("C:/"+fileName);
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor we = new WordExtractor(doc);
            paragraphs = we.getText().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paragraphs;
    }
    public static String convertDocFile(String fileName){
    	String result = null;
    	try {
    		HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("근로계약서.doc"));
    		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
    				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
    		wordToHtmlConverter.processDocument(wordDocument);
    		Document htmlDocument = wordToHtmlConverter.getDocument();
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		DOMSource domSource = new DOMSource(htmlDocument);
    		StreamResult streamResult = new StreamResult(out);
    		TransformerFactory tf = TransformerFactory.newInstance();
    		Transformer serializer = tf.newTransformer();
    		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
    		serializer.setOutputProperty(OutputKeys.METHOD, "html");
    		serializer.transform(domSource, streamResult);
    		out.close();
    		result = new String(out.toByteArray());
    		System.out.println(result);
    	} catch (Exception e) {
		}
    	return result;
    }
    
}

  