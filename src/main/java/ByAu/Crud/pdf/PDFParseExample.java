package ByAu.Crud.pdf;

import ByAu.Crud.CrudDemoApplication;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFParseExample {
    public static void main(String[] args) throws IOException, TikaException, SAXException {
        ///////////////
        // ONLY USE CrudDemoApplication's ONE OPTION
        //PDFBOX LIBRARY PDF PARSING JAVA 1. OPTION
        /////////////
        File file = new File(".pdf");
        PDFManager pdfManager = new PDFManager();
        pdfManager.setParser(new org.apache.pdfbox.pdfparser.PDFParser(new RandomAccessFile(file, "r")));
        // parse document
        PDDocument cosDoc = pdfManager.getParser().getPDDocument();

        pdfManager.getParser().parse();
        // get parsed document
        cosDoc = pdfManager.getParser().getPDDocument();
        // initiate new PDFTextStripper()
        PDFTextStripper pdfStripper = new PDFTextStripper();

        // set start page to parse page
        pdfStripper.setStartPage(0);

        // set end page to be parsed
        pdfStripper.setEndPage(5);

        // get parsed text
        String parsedText = pdfStripper.getText(cosDoc);
        System.out.println(parsedText);

        //////////////////////////////////////////
        ///// TİKA LIBRARY PDF PARSING JAVA 2. OPTION
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(
                ".pdf"));

        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);

        //getting the content of the document
        System.out.println("Contents of the PDF :" + handler.toString());

        ////////////////////////////
        //// PDFBOX LIBRARY PDF PARSING JAVA 3 .OPTION
        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath(".pdf");
        try {
            String text = pdfManager.toText();
            System.out.println(text);
        } catch (IOException ex) {
            Logger.getLogger(CrudDemoApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
