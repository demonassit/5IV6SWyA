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
public class TextosVigi {
    
    private String textoCifrado = "";
    private String textoClaro = "";
    
    CifradoVigenere cifradovigi = new CifradoVigenere();
    DescifrarVigenere descifradovigi = new DescifrarVigenere();
    
    //metodos que van a mandar a llamar en el principal
    
    public String encryptTextoClaro(String textoClaro, String clave){
        String claveCompeltada="";
        
        int indice = 0;
        
        for(int i = indice; i < textoClaro.length(); i++){
            for(int j = indice; j < clave.length(); j++){
                if(claveCompeltada.length() < textoClaro.length()){
                    if(textoClaro.charAt(indice) != ' '){
                        claveCompeltada += clave.charAt(j) + "";
                        
                    }else{
                        claveCompeltada += " ";
                        j--;
                    }
                    indice++;
                }
            }
        }
        
        
        for(int i = 0; i<textoClaro.length(); i++){
            char TextoClaro = textoClaro.charAt(i);
            char claveCompletada = claveCompeltada.charAt(i);
            if(TextoClaro != ' '){
                textoCifrado += cifradovigi.TextoCifrado(TextoClaro, claveCompletada)+ "";
            }else{
                textoCifrado += " ";
            }
        }
        
        return textoCifrado;
    }
    
    
    public String desencryptTextoClaro(String textoCifrado, String clave){
        String claveCompeltada="";
        
        int indice = 0;
        
        for(int i = indice; i < textoCifrado.length(); i++){
            for(int j = indice; j < clave.length(); j++){
                if(claveCompeltada.length() < textoCifrado.length()){
                    if(textoCifrado.charAt(indice) != ' '){
                        claveCompeltada += clave.charAt(j) + "";
                        
                    }else{
                        claveCompeltada += " ";
                        j--;
                    }
                    indice++;
                }
            }
        }
        
        
        for(int i = 0; i<textoCifrado.length(); i++){
            char TextoCifrados = textoCifrado.charAt(i);
            char claveCompletada = claveCompeltada.charAt(i);
            if(TextoCifrados != ' '){
                textoClaro += descifradovigi.TextoDescifrado(TextoCifrados, claveCompletada)+ "";
            }else{
                textoClaro += " ";
            }
        }
        
        return textoClaro;
    }
    
}
