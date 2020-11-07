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
public class CifradoVigenere {
    
    char tablaCesar[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
                        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q',
                        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    
    public char TextoCifrado(char TextoClaro, char TextoClave){
        int indiceX = 0;
        int indiceY = 0;
        
        for(int i = 0; i<tablaCesar.length; i++){
            if(TextoClaro == tablaCesar[i]){
                indiceX = i;
                break;
            }
        }
        
        for(int j = 0; j<tablaCesar.length; j++){
            if(TextoClave == tablaCesar[j]){
                indiceY = j;
                break;
            }
        }
        return tablaCesar[(indiceX+indiceY)%27];
        
    }
    
}
