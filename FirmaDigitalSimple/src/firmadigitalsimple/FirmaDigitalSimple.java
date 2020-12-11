/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigitalsimple;

/**
 *
 * @author demon
 */

import java.security.*;
import sun.misc.BASE64Encoder;

public class FirmaDigitalSimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        //tenemos que generar la instancia de rsa
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        //inicializamos la llaves 
        generador.initialize(2048);
        
        //generar las llaves
        KeyPair llaves = generador.genKeyPair();
        
        //vamos a suponer que el sig mensaje es el doc a firmar
        byte[] dato = "Habia una vez un gatito que decia cuack cuack".getBytes();
        
        
        byte[] dato1 = "Habia una vez un gatito que decia cuack cuack ".getBytes();
        
        //uso de la firma
        Signature firma = Signature.getInstance("SHA1WithRSA");
        //debemos inicializar la llave privada
        firma.initSign(llaves.getPrivate());
        
        //firmamos el documento
        firma.update(dato1);
        
        //vamos a imprimir la firma
        
        byte[] firmabytes = firma.sign();
        System.out.println("Firma: " + new BASE64Encoder().encode(firmabytes));
        
        //ahora viene la etapa de la verificacion
        //necesitamos saber que la firma sea valida
        
        firma.initVerify(llaves.getPublic());
        
        firma.update(dato);
        
        System.out.println("Si es verdadera ?" + firma.verify(firmabytes));
    }
    
}
