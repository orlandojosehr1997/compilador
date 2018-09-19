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
 * @author Orlando José Hidalgo Ramírez
 * @author Aymarú Castillo Flores
 */
public class Scanner 
{
    ArrayList<Identificador> tokenslist;
    ArrayList<String> nombreTokens;
    
    public Scanner() {}
    
    public static void generarLexer(String path)
    {
        File file=new File(path);
        jflex.Main.generate(file);
    }
    
    public void probarLexerFile() throws IOException
    {
        int lineCount=1;
        tokenslist = new ArrayList();
        nombreTokens = new ArrayList();
        //Obtener nombre archivo 
        
        String nombreArchivo = "pruebaScanner";//"archivo.rtf";
        //Obtener nombre archivo resultado
        String nombreArchivoResultado = "resultado.txt";
        
        Reader r = new BufferedReader(new FileReader(nombreArchivo));
        scannercompi.Lexer lexer = new scannercompi.Lexer(r);
        
        while (true)
        {
            Token token = lexer.yylex();
            if (token == null)
            {
                File archivoResultado = new File (nombreArchivoResultado);
                PrintWriter writer;
                try 
                {
                    writer = new PrintWriter(archivoResultado);
                    writer.print(imprimir());
                    writer.close();
                } 
                catch (FileNotFoundException ex) 
                {
                    System.out.println("Error en Archivo");
                }
                return;
            }
            switch (token)
            {
                case PALABRAS_RESERVADAS:
                    agregarToken(lexer.lexeme,token,lexer.getYyline()+1);
                    nombreTokens.add(lexer.lexeme);
                    break;
                case OPERADOR:
                    agregarToken(lexer.lexeme,token,lexer.getYyline()+1);
                    nombreTokens.add(lexer.lexeme);
                    break;
                case LITERAL_ENTERO:
                    agregarToken(lexer.lexeme,token,lexer.getYyline()+1);
                    nombreTokens.add(lexer.lexeme);
                    break;
                case LITERAL_FLOTANTE:
                    agregarToken(lexer.lexeme,token,lexer.getYyline()+1);
                    nombreTokens.add(lexer.lexeme);
                    break;
                case LITERAL_STRING:
                    agregarToken(lexer.lexeme,token,lexer.getYyline()+1);
                    nombreTokens.add(lexer.lexeme);
                    break;
                case LITERAL_CHAR:
                    agregarToken(lexer.lexeme,token,lexer.getYyline()+1);
                    nombreTokens.add(lexer.lexeme);
                    break;
                case ERROR:
                    System.out.println("Error, simbolo " + lexer.lexeme + " no reconocido. Linea: " + (1+ lexer.getYyline()));
                    break;
                case IDENTIFICADOR: 
                {
                    String identificador = lexer.lexeme;
                    if(!(identificador.length()<128))
                    {
                        System.out.println("Error, el identificador " + identificador + " es más grande que 127 caracteres. Linea: " + (lexer.getYyline()+1));
                    }
                    else if(!Character.isLetter(identificador.charAt(0)))
                    {
                        System.out.println("Error, el identificador " + identificador + " debe comenzar en una letra. Linea: " + (1+lexer.getYyline()));
                    }
                    else
                    {
                        agregarToken(identificador,token,lexer.getYyline()+1);
                        nombreTokens.add(lexer.lexeme);
                    }
                    break;
                }
                default:
                    //resultado=resultado+ "<"+ lexer.lexeme + "> ";
            }
        }
    }
    
    private boolean agregarToken(String nombre, Token tipoToken, Integer linea)
    {
        if(nombreTokens.contains(nombre))
        {
            for(Identificador token:tokenslist)
            {
                if(token.getNombre().equals(nombre))
                {
                    token.nuevaAparicion(linea);
                    break;
                }
            }
        }
        else
        {
            tokenslist.add(new Identificador(nombre,tipoToken,linea));
        }
        return true;
    }
    
    private String imprimir()
    {
        String resultado="|        Token        |     Tipo de Token    |    Linea\n";
        for(Identificador token: tokenslist)
        {
            resultado+=token.imprimir();
        }
        return resultado;
    }
}
