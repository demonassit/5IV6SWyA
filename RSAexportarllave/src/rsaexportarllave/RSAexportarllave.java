/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaexportarllave;

/**
 *
 * @author demon
 */

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;



public class RSAexportarllave {
    
    private static Cipher rsa;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //primeramente vamsoa  generar las llaves del algoritmo de rsa
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        
        //para obtener las llaves para e y d publica y privada
        KeyPair llavecitas = generador.generateKeyPair();
        
        //asignar publica y la privada
        
        PublicKey llavepublica = llavecitas.getPublic();
        PrivateKey llaveprivada = llavecitas.getPrivate();
        
        /*
        vamos a realizar que el programa pueda cifrar un mensaje, pero que se
        importe y exporten las llaves para el ciframiento, significa que con ello
        podremos verificar que el mensaje este cifrado y unicamente con los
        archivos se puede cifrar y descifrar, dandole un impacto en la seguridad;
        la desventaja de esto, es que tenemos que implementar certificados o estandares
        para la verificacion de que la llave es correcta
        */
        
        //para guardar y cargar un fichero que contenga la llave publica y privada
        guardarKey(llavepublica, "publickey.key");
        llavepublica = cargarLlavePublica("publickey.key");
        
        guardarKey(llaveprivada, "privatekey.key");
        llaveprivada = cargarLlavePrivada("privatekey.key");
        
        //obtener el cifrado
        
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        
        String texto = "habia una vez un patito que decia miau miau";
        
        //cifrar
        rsa.init(Cipher.ENCRYPT_MODE, llavepublica);
        byte[] encriptar = rsa.doFinal(texto.getBytes());
        
        //para que podamos visualizar que estamos cifrando
        for(byte b : encriptar){
            System.out.print(Integer.toHexString(0xFF & b));
        }
        System.out.println();
        
        //descifrar
        rsa.init(Cipher.DECRYPT_MODE, llaveprivada);
        byte[] descifrar = rsa.doFinal(encriptar);
        String descifrartexto = new String(descifrar);
        
        System.out.println(descifrartexto);
        
        
        
        
    }

    private static void guardarKey(Key llave, String archivo) throws FileNotFoundException, IOException {
        /*
        literal es fos estamos escribiendo en un archivo,
        lo que estamos escribiendo es el contenido de la llave
       
        */
        
        byte[] llavebytes = llave.getEncoded();
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavebytes);
        fos.close();
    }

    private static PublicKey cargarLlavePublica(String archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        /*
        debemos entender que para la carga de una llave, debe de estar estandarizada
        o certificada por medio de un codificador, para ello cuando utilizamos
        una llave publica se utiliza el estandar X509, el cual nos sirve
        para la verificacion de la llave publica
        */
        //generar el archivo
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        //viene la comprobacion de esa llave
        //con la generacion de subllaves y su verificacion
        KeyFactory kecfactory = KeyFactory.getInstance("RSA");
        //las subllaves
        KeySpec spec = new X509EncodedKeySpec(bytes);
        PublicKey llavepublic = kecfactory.generatePublic(spec);
        return llavepublic;
        
    }

    private static PrivateKey cargarLlavePrivada(String archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        //comprobacion debe de utilizar el estandar
        //PKCS8 para su verificacion de las subllaves generadas
         KeyFactory kecfactory = KeyFactory.getInstance("RSA");
        //las subllaves
        KeySpec spec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey llaveprivate = kecfactory.generatePrivate(spec);
        return llaveprivate;
    }
    
}
