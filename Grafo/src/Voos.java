
import java.util.Scanner;

/**
 *
 * @author fred_
 */

class DistPai {
    public int preco;           //distancia do inicio ate este no
    public int verticePai;      //pai atual deste no
    
    public DistPai(int vp, int p){
        preco = p;
        verticePai = vp;
    }
}

class Vertice{
    public String cidade;
    public boolean estaNaArvore;

    public Vertice(String cid){
        cidade = cid;
        estaNaArvore = false;
    }
}

class Voos{
    public final int MAX_VERTS = 20;
    public final int INFINITO = 1000000;
    public Vertice listaVertices[];                                            //lista de nós
    public int matAdj[][];                                                     //matriz de adjacencia
    public int nVerts;                                                         //numero atual de nós
    public int vertAtual;
    public int nTotal;                                                         //numero de nós na arvore
    public DistPai[] caminhoCurto;
    public int inicioDoAtual;

    public Voos(){
        listaVertices = new Vertice[MAX_VERTS]; 
        matAdj = new int[MAX_VERTS][MAX_VERTS];                                 //matriz de adjacencia
        nVerts = 0;
        nTotal = 0;
        for(int j=0; j<MAX_VERTS; j++){
            for(int k=0; k<MAX_VERTS; k++){
                matAdj[j][k] = INFINITO;
            }
        }
        caminhoCurto = new DistPai[MAX_VERTS];
    }
    public void addVertice(String cid){
        listaVertices[nVerts++] = new Vertice(cid);
    }
    public void addAresta(Integer origem, Integer destino, Integer preco){
        matAdj[origem][destino] = preco;                                             //Orientado
        matAdj[destino][origem] = preco;
    }
    
    public void caminho(int inicio, int fim){                                   //encontra todos os caminhos mais curtos
            
        int inicioGrafo = inicio;                                               //começa do nó 0
        listaVertices[inicioGrafo].estaNaArvore = true;  
        nTotal = 1;                                                             //coloca na arvore
        //transfere linha de distancia de adjMat para sPath
        for(int j=0; j<nVerts; j++){
            int tempDist = matAdj[inicioGrafo][j];
            caminhoCurto[j] = new DistPai(inicioGrafo, tempDist);
        }
        //até todos os nós estarem na arvore
        while(nTotal < nVerts){
            int indexMin = obterMenor();                                        //obtem o minimo sPath
            int minDist = caminhoCurto[indexMin].preco;

            vertAtual = indexMin;
            inicioDoAtual = caminhoCurto[indexMin].preco;
            //a distancia minima a partir de startTree é ate currentVert com valor startToCurrent
            //coloca nó atual na arvore
            listaVertices[vertAtual].estaNaArvore = true;
            nTotal++;
            ajusteCaminho();
        }
        
        exibirCaminho(inicio,fim);
        nTotal = 0;
        for(int j=0; j<nVerts; j++){
            listaVertices[j].estaNaArvore = false;
        }
        
    }
    
    public int obterMenor(){                                                    //obtem entrada a partir de sPath com distancia minima
        int minDist = INFINITO;                                                 //assume um numero muito grande
        int indexMin = 0;
        for(int j=0; j<nVerts; j++){                                            //para cada nó, se ele nao estiver na arvore
                                                                                //e for menor que o antigo
            if( !listaVertices[j].estaNaArvore && caminhoCurto[j].preco < minDist){
                minDist = caminhoCurto[j].preco;
                indexMin = j;                                                   //atualiza o minimo
            }
        }
        return indexMin;
    }
    
    public void ajusteCaminho(){
        //ajusta valores no vetor de caminhos mais curtos sPath
        int coluna = 1;
        while(coluna < nVerts){
            //se a coluna deste no ja estiver na arvore, desconsidere-o
            if(listaVertices[coluna].estaNaArvore){
                coluna++;
                continue;
            }
            //calcula distancia para uma entrada de sPath e obtem aresta de currentVert para column
            int currentToFringe = matAdj[vertAtual][coluna];
            int startToFring = inicioDoAtual + currentToFringe;
            int sPathDist = caminhoCurto[coluna].preco;
            //compara distancia a partir do inicio com a entrada de sPath
            if(startToFring < sPathDist){
                caminhoCurto[coluna].verticePai = vertAtual;
                caminhoCurto[coluna].preco = startToFring;
            }
            coluna++;
        }
    }
    
    public void exibirCaminho(int inicio,int fim){
        
        int aux = fim;
        String pai;
        if(caminhoCurto[fim].preco == INFINITO)
            System.out.println("inf");
        else
            System.out.println(caminhoCurto[fim].preco);
    }
    
    public void exibirCidades(){
        for(int i = 0; i < listaVertices.length; i++){
            if(listaVertices[i] != null)
                System.out.println("Posicao: " +i+ " | cidade: "+listaVertices[i].cidade);
            else
                break;
        }
    }
    
    public int obterIndice(String cidade){
        for(int i=0; i < listaVertices.length; i++){
            if(listaVertices[i].cidade.equals(cidade))
                return i;
        }
        return -1;
    }
    
    public boolean contem(String cidad){
        for(int i=0; i<listaVertices.length; i++){
            if(listaVertices[i] == null)
                return false;
            if(listaVertices[i].cidade.equals(cidad))
                return true;
        }
        return false;
    }
}

class App{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Voos voo = new Voos();
        String entrada = " ";
        String origem;
        String destino;
        int preco;
        int keyOrigem=-1;
        int keyDestino=-1;
        
        while(!entrada.equals("##0") || !entrada.equals("# # 0")){
            entrada = scan.nextLine();
            if(entrada.equals("##0") || entrada.equals("# # 0"))
                break;
            String voos[] = entrada.split(" ");
            origem = voos[0];
            destino = voos[1];
            preco = Integer.parseInt(voos[2]);
            
            if(voo.listaVertices[0] == null){
                voo.addVertice(origem);
                voo.addVertice(destino);
            }else{
                if(voo.contem(origem) == false){
                    voo.addVertice(origem);
                }
                if(voo.contem(destino) == false){
                    voo.addVertice(destino);
                }
            }
            
            keyOrigem = voo.obterIndice(origem);
            keyDestino = voo.obterIndice(destino); 
            voo.addAresta(keyOrigem,keyDestino,preco);
        }
        
        //voo.exibirCidades();
        
        String viagens = " ";
        String inicio;
        String fim;
        
        while(!viagens.equals("##") || !viagens.equals("# #")){
            viagens = scan.nextLine();
            if(viagens.equals("##") || viagens.equals("# #"))
                break;
            String voos[] = viagens.split(" ");
            inicio = ""+ voos[0];
            fim = ""+ voos[1];
            
            if(voo.contem(inicio) && voo.contem(fim)){ 
                keyOrigem = voo.obterIndice(inicio);
                keyDestino = voo.obterIndice(fim); 

                voo.caminho(keyOrigem,keyDestino);
            }else{
                System.out.println("impossivel");
            }
        }
        System.out.println();
    }
}
