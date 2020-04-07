package mochila;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author fred_
*/

public class LojaEletrodomesticos {
    static int max(int a, int b) { return (a > b)? a : b; } 

    static void valorMax(int W, int wt[], int val[], int n){ 

        int K[][] = new int[n+1][W+1]; 

        for (int i = 0; i <= n; i++){ 
            for (int j = 0; j <= W; j++){
                if (i==0 || j==0){
                    K[i][j] = 0; 
                }else if (wt[i-1] <= j){ 
                    K[i][j] = max(val[i-1] + K[i-1][j-wt[i-1]],  K[i-1][j]); 
                }else{
                    K[i][j] = K[i-1][j]; 
                } 
            }
        } 
        
        List<Integer> itens = new ArrayList();
        int i = n;
        int j = W;
        
        while(i>0){
            if(K[i][j] != K[i-1][j]){
                itens.add(wt[i-1]);
                j = j-(wt[i-1]);
                i--;
            }else{
                i--;
            }
        }
        
        System.out.println("Itens levados: " + itens.toString());
        System.out.println("Valor máximo: " + K[n][W]); 
    } 

    public static void main(String args[]){ 
        int valores[] = new int[]{2500,1800,1000,800,600,400}; 
        int pesos[] = new int[]{100,80,40,120,10,50};  
        int  pesoCarrinho = 300; 
        int qtdItens = valores.length; 
        valorMax(pesoCarrinho, pesos, valores, qtdItens); 
    } 
}
