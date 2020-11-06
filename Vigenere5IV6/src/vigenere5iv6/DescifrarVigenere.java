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
public class DescifrarVigenere {
    char tablaCesar[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
                        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q',
                        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    
    public char TextoDescifrado(char TextoCifrado, char TextoClave){
        int indiceTextoCifrado = 0;
        int indiceTextoClave = 0;
        
        for(int i = 0; i<tablaCesar.length; i++){
            if(TextoCifrado == tablaCesar[i]){
                indiceTextoCifrado = i;
                break;
            }
        }
        
        for(int j = 0; j<tablaCesar.length; j++){
            if(TextoClave == tablaCesar[j]){
                indiceTextoClave = j;
                break;
            }
        }
        if(indiceTextoCifrado >= indiceTextoClave){
            return tablaCesar[(indiceTextoCifrado-indiceTextoClave)%27];
        }else{
            return tablaCesar[27-(indiceTextoClave-indiceTextoCifrado)];
        }
        
        
    }
}
