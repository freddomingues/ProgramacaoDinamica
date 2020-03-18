import java.util.Scanner;

/**
 *
 * @author fred_
 */

public class Main {
    
    public static int enigma(String mensagem, String crib){        
        int qtd = 0;
        int tamCrib = crib.length();
        int tamMensagem = mensagem.length();
        int inicio = 0;
        int fim = tamCrib;
        
        while(fim <= tamMensagem){
            String string = mensagem.substring(inicio, fim);
            for(int a = 0; a <= fim-1; a++){
                String aux1 = string.substring(a,a+1); 
                String aux2 = crib.substring(a,a+1); 
                if( aux1.equals(aux2))  break;
                else if(a == string.length()-1){
                    if(mensagem.substring(a) != crib.substring(a)){
                        qtd++;
                        break;
                    }
                }
            }
            inicio++;
            fim++;
        }
        return qtd;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String mensagem = scan.nextLine();
        String crib = scan.nextLine();
        System.out.println(Main.enigma(mensagem, crib));
    }
}
