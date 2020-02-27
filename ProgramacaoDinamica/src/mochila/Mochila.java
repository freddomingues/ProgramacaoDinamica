package mochila;

/**
 *
 * @author fred_
 */
public class Mochila {
    static int max(int a, int b) { return (a > b)? a : b; } 
       
    static int mochila(int W, int wt[], int val[], int n){ 
        // caso base
        if (n == 0 || W == 0) 
            return 0; 
       
        // se o peso do n-ésimo item é maior do que a capacidade da mochila W
        //entao esse item nao pode ser incluido numa solução otima
        if (wt[n-1] > W) 
           return mochila(W, wt, val, n-1); 
       
        // retorna o maximo de dois casos: (1) n-ésimo item incluido (2) nao incluido
        else return max(val[n-1] + mochila(W-wt[n-1], wt, val, n-1), 
                        mochila(W, wt, val, n-1)); 
          } 
  
    public static void main(String args[]){ 
        int val[] = new int[]{4,6,8,10,12,14,16,18,20,22,24,26}; 
        int wt[] = new int[]{2,3,4,5,6,7,8,9,10,11,12,13};  
        int  W = 100;  
        int n = val.length; 
        System.out.println(mochila(W, wt, val, n)); 
    } 
}
