/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannercompi;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author orlandojose
 */
public class Identificador 
{
    String nombre;
    Token tipoToken;
    ArrayList<Integer> apariciones;

    public Identificador(String name, Token tipoToken, Integer linea) 
    {
        this.nombre = name;
        this.tipoToken = tipoToken;
        apariciones = new ArrayList();
        apariciones.add(linea);
    }

    public String getNombre() 
    {
        return nombre;
    }
    
    public boolean nuevaAparicion(int linea)
    {
        apariciones.add(linea);
        return true;
    }
    
    public String getApariciones()
    {
        String resultado="";
        
        Collections.sort(apariciones);//Sorting list
        
        for(int contador=0;contador<apariciones.size();contador++)
        {
            int linea = apariciones.get(contador);
            int contadorTemp = apariciones.lastIndexOf(linea);
            int cantidadApariciones = contadorTemp-contador+1;
            if(contador!=contadorTemp)
            {
                resultado+=linea +"(" + (cantidadApariciones) + ") "; 
                contador=contadorTemp;
            }
            else
                resultado+=linea + " ";
        }
        return resultado;
    }
    public String imprimir()
    {
        return "|    " + nombre + "    |    " + tipoToken.name() + "    |    " + getApariciones() + "    \n";
    }
    
}
