/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes5iv6;

/**
 *
 * @author demon
 */

import javax.crypto.Cipher;
//esta libreria nos sirve para identificar el tipo de instancia del cifrado

import javax.crypto.spec.SecretKeySpec;
//esta libreria nos ayuda a generar las subllaves necesarias el cifrado simetrico 
//identificado a partir de la instancia

import java.util.*;
import sun.misc.BASE64Encoder;

public class AES5IV6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        Para la progrmacion de un algoritmo de tipo AES, es necesario primero 
        entender que la primera regla viene a apartir de reconocer el tipo 
        de cifrado si es de
        
        128      llave debe de medir 16 caracteres 
        192      llave debe de medir 24 caracteres 
        256      llave debe de medir 32 caracteres 
        
        porque a partir de ello es como debemos configurar la clave secreta
        
        segundo dentro del aes, se debe de manejar los bloques de cifrado
        significa que debemos de tener arreglos de bytes
        */
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Ingresa la llave secreta para cifrar el mensaje: ");
        String llavesimetrica = entrada.nextLine();
        
        //tenemos que generar las subllaves del cifrado, para eso tenemos que ocupar
        //a la clase SecretKeySpec
        
        SecretKeySpec key = new SecretKeySpec(llavesimetrica.getBytes(), "AES");
        
        
        System.out.println("Ingresa el mensaje que quieres cifrar: ");
        String  mensaje = entrada.nextLine();
        
        Cipher cifrado;
        
        
        try{
            //tenemos que inicializar la instancia del cifrado
            
            cifrado = Cipher.getInstance("AES");
            
            //ciframos
            
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            
            //vamos a generar un bloque de bytes para el mensaje
            
            byte[] campoCifrado = cifrado.doFinal(mensaje.getBytes());
            
            System.out.println("El mensaje cifrado es: "+campoCifrado);
            
            //que se imprima como cadena
            
            System.out.println("El mensaje cifrado en formato de cadena: "+new String(campoCifrado));
            
            //hay que codificar el mensaje para que pueda ser interpreado por los seres humanos
            String base64 = new BASE64Encoder().encode(campoCifrado);
            
            System.out.println("Mensaje codificado: "+base64);
            
            //ahora a descifrar
            
            cifrado.init(Cipher.DECRYPT_MODE, key);
            
            //necesito un arreglo de byte para el mensaje que voy a descifrar
            
            byte[] mensajeDescifrado = cifrado.doFinal(campoCifrado);
            
            String mensajedesci = new String(mensajeDescifrado);
            
            System.out.println("Mensaje descifrado:" +mensajedesci);
                
        
        }catch(Exception e){
            
            System.out.println("Error la llave es chiquita o muy grandota");
            System.out.println(e.getMessage());
        
        }
        
       
        
    }
    
}
