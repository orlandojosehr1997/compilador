/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannercompi;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author orlandojose
 */
public class ScannerCompi 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String path = System.getProperty("user.dir")+ "/src/scannercompi/Lexer.flex";
        
        
        Scanner x = new Scanner();
        
        //x.generarLexer(path);
        
        try
        {
            x.probarLexerFile();
            Source xml = new StreamSource(new File(System.getProperty("user.dir")+"/scanner.xml"));
            Source xslt = new StreamSource(System.getProperty("user.dir")+"/style.xsl");

            XML.XML.convertXMLToHTML(xml, xslt);
            File htmlFile = new File("scanner.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
    }
}
