/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere5iv6;

/**
 *
 * @author demon
 */
public class OperacionesVigi {
    
    //variables
    
    char[] mensaje;
    char[] clave;
    char[] resultado;
    char matriz [][];  //nuestra tabla del abc
    
    //creamos el constructor
    public OperacionesVigi(){
    
    }
    
    //SOBREcarga del constructor PARA EL PASO DE PARAMETROS MSJ Y CLAVE
    
    public OperacionesVigi(String msj, String clave){
        
        this.mensaje = msj.toCharArray();
        char[] claveTemp = clave.toCharArray();
        this.clave = new char[mensaje.length];
        
        //tenemos que recordar que dentro del algotirmo vigenere debemos rellenar
        //la clave acorde al tamaño del mensaje
        
        int cont = 0;
        
        for(int i = 0; i<mensaje.length; i++){
            this.clave[i] = claveTemp[cont];
            cont++;
            if(cont == claveTemp.length)
                cont = 0;
        }
        
        this.matriz = generarMatrizABC();
        Cifrar();
        
    }

    private char[][] generarMatrizABC() {
        int contador;
        char abcTemp[] = this.generarAbecedario();
        char abc[] = new char[abcTemp.length*2];
        
        for(int c = 0; c<26; c++){
            abc[c] = abcTemp[c];
            abc[c+26] = abcTemp[c];
        }
        
        char [][] matriz = new char[26][26];
        for(int i = 0; i<26; i++){
            contador = 0;
            for(int j = 0; j<26; j++){
                matriz[i][j] = abc[contador+i];
                contador++;
            }
        }
        
        return matriz;
    }

    private void Cifrar() {
        char[] cifrado = new char[mensaje.length];
        int i, j;
        //para poder saber la posicion de los caracteres del mensaje y la clave
        for(int cont = 0; cont<mensaje.length; cont++){
            //vamos a asignar codigos ASCII a las posiciones de los caracteres
            i = (int)this.mensaje[cont]-97;
            j = (int)this.clave[cont]-97;
            cifrado[cont]=this.matriz[i][j];
            //obtenemos las posiciones
        }
        this.resultado = cifrado;
        //necesitamos recorrer en un bucle las posiciones para las letras a - z
        for(int k = 0; k<26; k++){
            System.out.println(this.matriz[k]);
            System.out.println(this.mensaje);
            System.out.println(this.clave);
            System.out.println(cifrado);
        }
    }
    
    public String getMensajeCifrado(){
        String result = "";
        for(int i = 0; i<resultado.length; i++){
            result += this.resultado;
        }
        return result;
    }

    private char[] generarAbecedario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
