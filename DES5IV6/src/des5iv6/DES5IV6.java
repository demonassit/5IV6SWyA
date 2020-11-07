/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des5iv6;

/**
 *
 * @author demon
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.*;

import javax.crypto.interfaces.*;
//que nos ayuda a generar las interfaces para las operaciones del algoritmo

import javax.crypto.spec.*;
//nos ayuda a generar las subllaves para el algoritmo

import java.security.*;
//nos ayuda a identificar el tipo de algoritmo simetrico o asimetrico a programar


public class DES5IV6 {

    /**
     * @param args the command line arguments
     * 
     * Vamos a crear un programa que se encargue de leer un archivo txt, y mediante
     * una clave autoasignada cifrar el archivo y descifrarlo
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {
        // TODO code application logic here
        
        if(args[0].length() != -1){
            mensajeAyuda();
            System.exit(1);
        }
        
        //si hay archivo entonces wiiii
        
        /*
        Para poder cifrar un archivo, es necesario crear una instancia llamada "provider", la cual
        designa el tipo de algoritmo simetrico o asimetrico, que servira para la generacion de las claves
        y asi poder realizar su funcion
        
        Como primer paso dbemos de crear dicha instancia a traves del uso de la clase llamada KeyGenerator
        que viene por parte de la libreria security
        */
        
        
        System.out.println("1.- Generacion de clave DES:");
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        
        //vamos a inicializar la llave con un tamaño de 56 bits
        generadorDES.init(56);
        
        //a apartir de ello empezamos a crear las subllaves
        SecretKey clave = generadorDES.generateKey();
        //wiiiii las 16 rondas wiiiiiii
        
        
        System.out.println("Clave :"+clave);
        //tenemos que crear un metodo para pasarlo a bytes
        mostrarBytes(clave.getEncoded());
        System.out.println("La clave codificada: "+clave.getEncoded());
        
        System.out.println();
        
        /*
        Paso 2 vamos a cifrar, ahi se preparan sus tablitas de permutacion wiiiii
        
        El primer elemento que debo de saber es el tipo de algoritmo para cifrar
        DES
        El segundo paso es el modo de operacion del algoritmo
        ECB Electronic code book
        El tercer paso, entender si los bloques necesitan o no ser rellenados
        Si vamos a aplicar el relleno entonces ocupamos 
        
        PKCS5Padding
        
        
        */
        
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
        
        //ahora vamos a cifrar
        
        System.out.println("2.- Cifrar el archivo con DES : "+args[0]+
                " el archivo cifrado es: "+args[0]+".cifrado");
        
        cifrado.init(Cipher.ENCRYPT_MODE, clave);
        //wiiiii ya cifre 
        
        //ahora falta dar el tratamiento al archivo
        
        byte[] buffer = new byte[1000];
        byte[] buffercifrado;
        
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
        
        //mientras el archivo no llegue al fin debe de leerlo y cifrarlo
        
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            buffercifrado = cifrado.update(buffer, 0, bytesleidos);
            out.write(buffercifrado);
            bytesleidos = in.read(buffer, 0, 1000);
        }
        
        buffercifrado = cifrado.doFinal();
        out.write(buffercifrado);
        
        in.close();
        out.close();
        
        
        //vamos a descifrar
         System.out.println("3.- Descifrar el archivo con DES : "+args[0]+
                ".cifrado"+" y el archivo descifrado es: "+args[0]+".descifrado");
        
        cifrado.init(Cipher.DECRYPT_MODE, clave);
        //wiiiii ya cifre 
        
        //ahora falta dar el tratamiento al archivo
        
        //byte[] buffer = new byte[1000];
        byte[] bufferplano;
        
        in = new FileInputStream(args[0]+".cifrado");
        out = new FileOutputStream(args[0]+".descifrado");
        
        //mientras el archivo no llegue al fin debe de leerlo y cifrarlo
        
        bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            bufferplano = cifrado.update(buffer, 0, bytesleidos);
            out.write(bufferplano);
            bytesleidos = in.read(buffer, 0, 1000);
        }
        
        bufferplano = cifrado.doFinal();
        out.write(bufferplano);
        
        in.close();
        out.close();
    }

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de algoritmo DES para cifrar un archivo");
        System.out.println("Por fis pon un archivo porque sino no sirvo");
        System.out.println("Cooperen para el xbox serie x (uwu)/");
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
