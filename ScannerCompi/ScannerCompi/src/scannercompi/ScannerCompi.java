/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannercompi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

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
        String path = System.getProperty("user.dir")+"\\src\\scannercompi\\Lexer.flex";   
       
        //String path = "/Users/orlandojose/NetBeansProjects/ScannerCompi/src/scannercompi/Lexer.flex";
        generarLexer(path);
        /*
        Scanner x = new Scanner();
        try
        {
            x.probarLexerFile();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        */
    }
    public static void generarLexer(String path)
    {
        File file=new File(path);
        jflex.Main.generate(file);
    }
    /*
    public void tablaResultado(){
        Object[][] matriz = new Object [tokenslist.size()][2];
        for(int i =0; i<tokenslist.size();i++){
            matriz[i][0] = tokenslist.get(i).nombre;
            matriz[i][1] = tokenslist.get(i).ID;
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(matriz,
            new String [] {
                "Nombre", "ID"
        }
        ));
    }
    */
}
