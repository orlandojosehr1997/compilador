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
import java.util.Scanner;


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
        
        
        ScannerABC x = new ScannerABC();
        
        //x.generarLexer(path);
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Digite la ruta del archivo fuente (Sin extención): ");
        String nombreArchivo = reader.nextLine();
        try
        {
            x.probarLexerFile(nombreArchivo);
            Source xml = new StreamSource(new File(nombreArchivo+".xml"));
            Source xslt = new StreamSource(System.getProperty("user.dir")+"/style.xsl");

            XML.XML.convertXMLToHTML(xml, xslt, nombreArchivo);
            File htmlFile = new File(nombreArchivo+".html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
    }
}
