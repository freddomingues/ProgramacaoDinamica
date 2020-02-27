package mochila;

/**
 *
 * @author fred_
 */
public class MochilaPD {
    //funcao que recebe dois inteiros como parametro e retorna o maior deles
    static int max(int a, int b) { return (a > b)? a : b; } 
       
    //metodo que recebe como parametro:
    //um inteiro representando a capacidade maxima da mochila
    //um vetor com os pesos dos itens
    //um vetor com os valores dos itens
    //um inteiro representando a quantidade de itens
    static int mochilaPD(int W, int wt[], int val[], int n){ 
        //matriz de armazenamento com a quantidade de linhas igual a quantidade de itens
        //e a quantidade de colunas igual a capacidade maxima da mochila
        int K[][] = new int[n+1][W+1]; 
       
        // Laço que constroi a tabela de resolução 
        for (int i = 0; i <= n; i++){ 
           for (int j = 0; j <= W; j++){
               //preenchendo a primeira linha e coluna com o valor 0
               if (i==0 || j==0) 
                   K[i][j] = 0; 
               //verifica se o peso do item é menor que o peso da coluna
               else if (wt[i-1] <= j) 
                   //se couber, é armazenado na celula o valor maximo entre
                   //a soma do valor do item + o valor armazenado na linha superior e coluna w-peso do item
                   //e o valor que esta na celula a cima
                   K[i][j] = max(val[i-1] + K[i-1][j-wt[i-1]],  K[i-1][j]); 
               //se nao for menor, preenche com o valor da celula da linha de cima
               else
                   K[i][j] = K[i-1][j]; 
            } 
        } 
        //retorna o valor contido na ultima celula, que representa o valor maximo possivel na mochila
        return K[n][W]; 
    } 
  
    public static void main(String args[]){ 
        int val[] = new int[]{4,6,8,10,12,14,16,18,20,22,24,26}; 
        int wt[] = new int[]{2,3,4,5,6,7,8,9,10,11,12,13};  
        int  W = 100; 
        int n = val.length; 
        System.out.println(mochilaPD(W, wt, val, n)); 
    } 
}
