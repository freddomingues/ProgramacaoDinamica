
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 *
 * @author fred_
 */
public class VaquinhasUenp {
    public static void main(String[] args) {
        java.util.PriorityQueue<Funcionario> fila;
        java.util.Scanner scanner = new Scanner(System.in);
        fila = new PriorityQueue<>();
        int velocidade[] = new int[10005];
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        // ler a velocidade de cada id
        for (int i = 1; i <= n; i++) {
            velocidade[i] = scanner.nextInt();
        }
        // resposta
        int resposta = 0;
        // fila de ids: livre, numero
        // inicialmente todos estao livres no livre zero
        for (int i = 1; i <= n; i++) {
            fila.add(new Funcionario(0, i));
        }
        //ler quantos litros tem cada vaca
        int litros;
        while (m > 0) {
            litros = scanner.nextInt();
            // qual funcionario vai pegar esta vaca?
            // o que esta livre (no comeco da fila)
            Funcionario aux = fila.peek();
            int id = aux.id;
            int livre = aux.livre;

            // devolve o funcionario na fila, depois que eleacabar
            fila.add(new Funcionario((livre + velocidade[id] * litros), id));
            resposta = Integer.max(resposta, livre + velocidade[id] * litros); 
            m--;
            fila.remove(aux);
        }
        System.out.println(resposta);
    }
    static class Funcionario implements Comparable<Funcionario> {
        public int livre;
        public int id;
        Funcionario(){}
        Funcionario(int l, int id) {
            this.livre = l;
            this.id = id;
        }
        @Override
        public int compareTo(Funcionario p) {
            if (this.livre > p.livre)
                return 1;
            else if (this.livre < p.livre) 
                return -1;
            else // desempata pelo id{
                if (this.id > p.id)
                    return 1;
                else if (this.id < p.id) 
                    return -1;
                else 
                    return 0;
        }
    }
}