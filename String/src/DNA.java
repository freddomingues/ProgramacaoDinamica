/**
 *
 * @author fred_
 */

public class DNA{ 
    
    static void LCS(char X[], char Y[]){ 
        if(X.length != Y.length) System.out.println("DNAs Inconsistentes");
        
        int m = X.length;
        int K[][] = new int[m + 1][m + 1]; 
        int result = 0;  
          
        for (int i = 0; i <= m; i++){ 
            for (int j = 0; j <= m; j++){ 
                if (i == 0 || j == 0) 
                    K[i][j] = 0; 
                else if (X[i - 1] == Y[j - 1]){ 
                    K[i][j] = K[i - 1][j - 1] + 1; 
                    result = Integer.max(result, K[i][j]); 
                }  
                else
                    K[i][j] = 0; 
            } 
        } 
        saida(verificarSemelhanca(K, m), result);
    } 
    
    static double verificarSemelhanca(int[][] matrix, int m){
        double sem = 0.0;
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= m; j++){
                if(i == j && matrix[i][j] != 0){
                    sem++;
                }
            }
        }
        return sem*100/m;
    }
    
    static void saida(double semelhanca, int maiorSubs){
        System.out.println("Semelhança: " + semelhanca);
        System.out.println("Tamanho da Maior SubString encontrada: " + maiorSubs);
    }
    
    public static void main(String args[]){ 
        String X = "AACTCGTACCAAAAAGACTCTACGAGGTTCAAA"; 
        String Y = "ACGTAGCTAGGCTATCTGATCTATGCTAGCACC"; 
        String Z = "TGCTAGATCGATCGATGCGATGCGATGCTAAAA"; 
        
        LCS(X.toCharArray(), Y.toCharArray());
        LCS(X.toCharArray(), Z.toCharArray());
    } 
} 