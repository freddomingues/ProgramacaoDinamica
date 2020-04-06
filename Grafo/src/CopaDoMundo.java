import java.util.Scanner;

/**
 *
 * @author fred_
 */

class DistPai {
    public int preco;           
    public int verticePai;      
    
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
    public final int MAX_VERTS = 30;
    public final int INFINITO = 2000000;
    public Vertice listaVertices[];                                            
    public int matAdj[][];                                                     
    public int nVerts;                                                         
    public int vertAtual;
    public int nTotal;                                                        
    public DistPai[] caminhoCurto;
    public int inicioDoAtual;
    public int totalGasto;

    public Voos(){
        listaVertices = new Vertice[MAX_VERTS]; 
        matAdj = new int[MAX_VERTS][MAX_VERTS];                                 
        nVerts = 0;
        nTotal = 0;
        for(int j=0; j<MAX_VERTS; j++){
            for(int k=0; k<MAX_VERTS; k++){
                matAdj[j][k] = INFINITO;
            }
        }
        caminhoCurto = new DistPai[MAX_VERTS];
        totalGasto = 0;
    }
    public void addVertice(String cid){
        listaVertices[nVerts++] = new Vertice(cid);
    }
    public void addAresta(Integer origem, Integer destino, Integer preco){
        matAdj[origem][destino] = preco;                                             
        matAdj[destino][origem] = preco;
    }
    
    public void caminho(int inicio, int fim){                                   
            
        int inicioGrafo = inicio;                                               
        listaVertices[inicioGrafo].estaNaArvore = true;  
        nTotal = 1;                                                             

        for(int j=0; j<nVerts; j++){
            int tempDist = matAdj[inicioGrafo][j];
            caminhoCurto[j] = new DistPai(inicioGrafo, tempDist);
        }

        while(nTotal < nVerts){
            int indexMin = obterMenor();                                        
            int minDist = caminhoCurto[indexMin].preco;

            vertAtual = indexMin;
            inicioDoAtual = caminhoCurto[indexMin].preco;
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
    
    public int obterMenor(){                                                    
        int minDist = INFINITO;                                                
        int indexMin = 0;
        for(int j=0; j<nVerts; j++){                                            
                                                                                
            if( !listaVertices[j].estaNaArvore && caminhoCurto[j].preco < minDist){
                minDist = caminhoCurto[j].preco;
                indexMin = j;                                                   
            }
        }
        return indexMin;
    }
    
    public void ajusteCaminho(){
        int coluna = 1;
        while(coluna < nVerts){
            
            if(listaVertices[coluna].estaNaArvore){
                coluna++;
                continue;
            }
            
            int currentToFringe = matAdj[vertAtual][coluna];
            int startToFring = inicioDoAtual + currentToFringe;
            int sPathDist = caminhoCurto[coluna].preco;

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
        if(caminhoCurto[fim].preco == INFINITO){
            System.out.println("Impossível");
            System.out.println("\n");
            return;
        }else{
            totalGasto += caminhoCurto[fim].preco;
            System.out.println("Preço total: " + caminhoCurto[fim].preco);
        }
        
        System.out.print("Caminho de "+ listaVertices[inicio].cidade +
                         " até " + listaVertices[fim].cidade + ": ");
        System.out.print(listaVertices[fim].cidade);
        while( aux != inicio){
            pai = listaVertices[caminhoCurto[aux].verticePai].cidade;
            aux = caminhoCurto[aux].verticePai;
            System.out.print(" <- " + pai);
        }
        System.out.println("\n");
    }
    
    public void exibirCidades(){
        for(int i = 0; i < listaVertices.length; i++){
            if(listaVertices[i] != null)
                System.out.println("Posicao: " +i+ " | cidade: "+listaVertices[i].cidade);
            else break;
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
    
    public void exibirMatriz(){
        for(int l = 0; l < 13; l++){
            for(int c = 0; c < 13; c++){
                System.out.print(matAdj[l][c] + "\t");
            }
            System.out.println();
        }
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
        
        System.out.println();
        voo.exibirCidades();
        System.out.println();
        
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
        System.out.println("Valor gasto com todas as passagens: " + voo.totalGasto);
    }
}