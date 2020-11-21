//ocupar la clase BigInteger para el calculo de los primos grandes

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class RSA{

    //variables para el algoritmo
    int tamPrimo;
    BigInteger p, q, n;
    BigInteger phi;
    BigInteger e, d;

    //constructor

    public RSA(int tamPrimo){
        this.tamPrimo = tamPrimo;
    }
    //1234567890
    //metodo que se encarga de generar primos
    public void generarPrimos(){
        p = new BigInteger(tamPrimo, 10, new Random());
        do q = new BigInteger(tamPrimo, 10, new Random());
            while(q.compareTo(p)==0);
    }

    //generar las claves
    public void generarClaves(){
        //n = p*q
        n = p.multiply(q);

        //phi = (p-1)*(q-1)
        phi = p.subtrac(BigInteger.valueOf(1));
        phi = phi.multiply(q.subtract(BigInteger.valueOf(1));

        //calcular el primo relativo o coprimo e y menor que n

        do e = new BigInteger(2*tamPrimo, new Random());
            //calcular el mcd e
            while ((e.compareTo(phi)!=-1)||(e.gcd(phi).compareTo(BigInteger.valueOf(1))!=0));
        //calcular d
        d = e.modInverse(phi);
    }

    //cifrar

    public BigInteger[] encriptar(String mensaje){

        //variables
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes;
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        //primero es recorrer el tamaño de bigdigitos para asignarlos al temp
        for(i = 0; i<bigdigitos.length; i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        //necesito un biginteger para el cifrado
        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        for(i = 0; i<bigdigitos.length; i++){
            //aplico el modulo para el cifrado
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }
        return cifrado;
    }

    //descifrar
    public String descifrar(BigInteger[] cifrado){

        BigInteger[] descifrado = new BigInteger[cifrado.length];

        //descifrar
        for(int i = 0; i<descifrado.length; i++){
            //aplico el descifrado
            descifrado[i] = cifrado[i].modPow(d, n);
        }

        //lo tengo que colocar en un arreglo de caracteres para despues pasarlo a una cadena
        char[] charArray = new char[descifrado.length];

        for(int i = 0; i <charArray.length; i++){
            charArray[i] =(char)(descifrado[i].intValue());
        }

        return (new String(charArray));
    }

    //enviar los datos

    public BigInteger damep(){
        return p;
    }

    public BigInteger dameq(){
        return q;
    }

    public BigInteger damen(){
        return n;
    }

    public BigInteger damephi(){
        return phi;
    }

    public BigInteger damee(){
        return e;
    }

    public BigInteger damed(){
        return d;
    }
}


