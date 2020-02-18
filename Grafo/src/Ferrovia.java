/**
 *
 * @author fred_
 */

class DistPai {
    public int distancia;        //distancia do inicio ate este no
    public int verticePai;      //pai atual deste no
    
    public DistPai(int vp, int d){
        distancia = d;
        verticePai = vp;
    }
}

class Vertice{
    public char legenda;
    public boolean estaNaArvore;

    public Vertice(char leg){
        legenda = leg;
        estaNaArvore = false;
    }
}

class Ferrovia{
    public final int MAX_VERTS = 20;
    public final int INFINITO = 1000000;
    public Vertice listaVertices[];                                            //lista de nós
    public int matAdj[][];                                                     //matriz de adjacencia
    public int nVerts;                                                         //numero atual de nós
    public int vertAtual;
    public int nTotal;                                                         //numero de nós na arvore
    public DistPai[] caminhoCurto;
    public int inicioDoAtual;
    public int flag = 0;

    public Ferrovia(){
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
    public void addVertice(char lab){
        listaVertices[nVerts++] = new Vertice(lab);
    }
    public void addAresta(int inicio, int fim, int peso){
        matAdj[inicio][fim] = peso;                                             //Orientado
        //adjMat[end][start] = weight;
    }
    
    public void caminho(int inicio, int fim){                                            //encontra todos os caminhos mais curtos
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
            int minDist = caminhoCurto[indexMin].distancia;
            
//            if(minDist == INFINITO){                                            //se todos forem infinitos
//                System.out.println("Nao existe caminho possivel entre essas duas cidades");
//                break;
//            }
//            else{
                vertAtual = indexMin;
                inicioDoAtual = caminhoCurto[indexMin].distancia;
                //a distancia minima a partir de startTree é ate currentVert com valor startToCurrent
            //}
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
            if( !listaVertices[j].estaNaArvore && caminhoCurto[j].distancia < minDist){
                minDist = caminhoCurto[j].distancia;
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
            int sPathDist = caminhoCurto[coluna].distancia;
            //compara distancia a partir do inicio com a entrada de sPath
            if(startToFring < sPathDist){
                caminhoCurto[coluna].verticePai = vertAtual;
                caminhoCurto[coluna].distancia = startToFring;
            }
            coluna++;
        }
    }
    
    public void exibirCaminho(int inicio,int fim){

        int aux = fim;
        char pai;
        System.out.print("Custo do caminho mais barato de " + listaVertices[inicio].legenda + " ate "
                + listaVertices[fim].legenda + ": ");
        
        if(caminhoCurto[fim].distancia == INFINITO)
            System.out.println("Impossivel");
        else
            System.out.println(caminhoCurto[fim].distancia);        
        
        System.out.print("Caminho: (");
        while( aux != inicio){
            pai = listaVertices[caminhoCurto[aux].verticePai].legenda;
            aux = caminhoCurto[aux].verticePai;
            System.out.print(pai+", ");
            
        }
        System.out.print(")");
    }
}

class App{
    public static void main(String[] args) {
        Ferrovia theGraph = new Ferrovia();
        theGraph.addVertice('A');    //0 
        theGraph.addVertice('B');    //1
        theGraph.addVertice('C');    //2
        theGraph.addVertice('D');    //3
        theGraph.addVertice('E');    //4
        
        theGraph.addAresta(0, 1, 50);
        theGraph.addAresta(0, 3, 80);
        theGraph.addAresta(1, 2, 60);
        theGraph.addAresta(1, 3, 90);
        theGraph.addAresta(2, 4, 40);
        theGraph.addAresta(3, 2, 20);
        theGraph.addAresta(3, 4, 70);
        theGraph.addAresta(4, 1, 50);
        
        theGraph.caminho(1,4);
        System.out.println();
    }
}