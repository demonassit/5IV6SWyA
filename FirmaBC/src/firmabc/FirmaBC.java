/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmabc;

/**
 *
 * @author demon
 */

import java.security.*;
import sun.misc.BASE64Encoder;


public class FirmaBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //como security no tiene soporte para BC debemos agregarlo
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        
        //la instancia
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA", "BC");
        
        //inicializamos
        
        generador.initialize(2048, new SecureRandom());
        
        KeyPair llaves = generador.genKeyPair();
        
        Signature firma = Signature.getInstance("SHA1WithRSA", "BC");
        
        firma.initSign(llaves.getPrivate(), new SecureRandom());
        
        byte[] dato = "Habia una vez otro patito que decia miau miau".getBytes();
        
        firma.update(dato);
        
        byte[] firmabytes = firma.sign();
        
        System.out.println("Firma: " + new BASE64Encoder().encode(firmabytes));
        
        //verificamos
        
        firma.initVerify(llaves.getPublic());
        
        firma.update(dato);
        
        System.out.println(firma.verify(firmabytes));
    }
    
}
