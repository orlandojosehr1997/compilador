/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannercompi;

/**
 *
 * @author orlandojose
 */
public enum Token 
{
    IDENTIFICADOR, 
    OPERADOR, 
    PALABRAS_RESERVADAS,
    LITERAL_ENTERO,
    LITERAL_FLOTANTE,
    LITERAL_STRING,
    LITERAL_CHAR,
    ERROR, 
    CAMBIO_LINEA
}
