/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsalibr;

/**
 *
 * @author demon
 */

import java.security.*;
import javax.crypto.*;
import java.io.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
//esta libreria nos ayudara a generar un RSA por flujo y
//nos ayudara a generar numeros mucho mas grandes que las otras librerias

public class RSALibr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //añadir el provider ya que el rsa por si solo no lo soporta
        Security.addProvider(new BouncyCastleProvider()); //significa que podemos cargar
        //un nuevo proveedor del algoritmo
        
        System.out.println("Ejemplo de RSA con el uso de BouncyCastle");
        System.out.println("1.- Creando las llaves publicas y privadas");
        
        //para poder crear las llaves es necesario iniciar la instancia
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA", "BC");
        
        //aqui es donde le decimos el tamaño que queremos que genere la llave
        generador.initialize(8192);
        
        KeyPair clavesRSA = generador.generateKeyPair();
        
        //con esto se genera el par de llaves, e y d publica y la privada
        
        PublicKey llavepublica = clavesRSA.getPublic();
        PrivateKey llaveprivada = clavesRSA.getPrivate();
        
        
        System.out.println("2.- Introduce el texto que deseas cifrar maximo 64 caracteres: ");
        byte[] bufferplano = leerLinea(System.in);
        
        /*
        Porque solo maximo 64 caracteres, simple, los modos de operacion normales por parte
        de security que hemos ocupado es ECB que es un cifrado por bloques, cuando
        añadimos BC no tiene soporte para manejo de bloques de ciframiento, por lo 
        tanto su flujo de bytes es limitado, por lo tanto solo puede cifrar
        mediante flujo
        
        si necesitaramos los bloques tendriamos que generar un metodo donde
        se obtenga el flujo de bytes y se separe cada 64 para generar un bloque
        
        */
        
        Cipher cifrador = Cipher.getInstance("RSA", "BC");
        
        //cifrar
        cifrador.init(Cipher.ENCRYPT_MODE, llavepublica);
        System.out.println("Ciframos con llave publica: ");
        byte[] buffercifrado = cifrador.doFinal(bufferplano);
        System.out.println("El Texto cifrado es:");
        mostrarBytes(buffercifrado);
        System.out.println("\n");
        
        //descifrar con privada
        
        cifrador.init(Cipher.DECRYPT_MODE, llaveprivada);
        System.out.println("Desciframos con llave privada: ");
        byte[] bufferdescifrado = cifrador.doFinal(buffercifrado);
        System.out.println("El texto descifrado es: ");
        mostrarBytes(bufferdescifrado);
        System.out.println("\n");
        
        
        //hagamos la comparativa si ciframos con privada y desciframos con publica
        
        //cifrar
        cifrador.init(Cipher.ENCRYPT_MODE, llaveprivada);
        System.out.println("Ciframos con llave privada: ");
        buffercifrado = cifrador.doFinal(bufferplano);
        System.out.println("El Texto cifrado es:");
        mostrarBytes(buffercifrado);
        System.out.println("\n");
        
        //descifrar con publica
        
        cifrador.init(Cipher.DECRYPT_MODE, llavepublica);
        System.out.println("Desciframos con llave publica: ");
        bufferdescifrado = cifrador.doFinal(buffercifrado);
        System.out.println("El texto descifrado es: ");
        mostrarBytes(bufferdescifrado);
        System.out.println("\n");
    }

    public static byte[] leerLinea(InputStream in) throws Exception {
        
        byte[] buffer1 = new byte[1000];
        int i=0;
        byte c;
        
        c = (byte)in.read();
        while((c!='\n')&&(i<1000)){
            buffer1[i] = c;
            c = (byte)in.read();
            i++;
        }
        
        byte[] buffer2 = new byte[i];
        for(int j=0; j<i; j++){
            buffer2[j] = buffer1[j];
        }
        return buffer2;
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
