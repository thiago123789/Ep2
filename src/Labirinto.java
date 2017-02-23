
public class Labirinto {
	static boolean entrouNaPorraDoIf = false;
	static No entrada = new No(1, 0);
	static No saida = new No(4, 5);
	static int[][] matriz = {
			{1,1,1,1,1,1},
			{0,0,1,0,0,1},
			{1,0,1,0,1,1},
			{1,0,0,0,0,1},
			{1,0,1,1,0,0},
			{1,1,1,1,1,1}
	};

	public static void main(String[] args) {
		Pilha p = new Pilha();
		andar(matriz, p, entrada.x, entrada.y, false, 6);
		p.imprimirPilha();
	}

	public static boolean iguais(No x, No y){
		boolean result = false;
		if((x.x == y.x) && (x.y == y.y)){
			result = true;
		}
		return result;
	}
	
	public static void imprimirMatriz(int[][] matriz, int tamanho){
		String print = "";
		for(int i = 0; i <= tamanho-1; i++){
			for(int j = 0; j <= tamanho-1; j++){
				print += matriz[i][j]+" ";
			}
			print += "\n";
		}
		System.out.println(print);
	}

	public static void andar(int[][] matriz, Pilha p, int x, int y, boolean erro, int tamanhoMatriz){
		int auxC = (x-1);
		int auxB = (x+1);
		int auxD = (y+1);
		int auxE = (y-1);
		//cria 'No' auxilar com os valores de x e y parados no metodo; 
		No aux = new No(x, y);
		//se o todo da pilha for null vai inserir os valores passados no metodo;
		if(p.topo == null){
			p.push(x, y);
		}
		//se a posicao da leitura na for -1 ele começa a operar
		if(matriz[x][y] != -1){
			//se a coordeanada que ele está for igual ao ponto de saida de labirinto nao executa mais nada
			if(iguais(aux, saida)){
				//marca posicao atual com 2 para indicar que faz parte do caminho;
				matriz[x][y] = 2;
				//adiciona a posicao atual na pilha;
				p.push(x, y);
				//variavel que ira parar a recusão caso ele chege no fim do labirinto
				entrouNaPorraDoIf = true;
				//metodo auxiliar que imprime a matriz
				imprimirMatriz(matriz, tamanhoMatriz);
			}else{
				//codicao que ira ver se é possivel andar para cima
				//se a posicao de cima está dentro da matriz e a posicao de cima é 0 e se a posicao de cima nao é o topo da pilha
				if(auxC >= 0 && matriz[auxC][y] == 0 && (auxC != p.topo.x || y != p.topo.y)){
					//se o No auxilar nao for igual a entrada ira ser adicionado na pilha
					if(!iguais(aux, entrada)){
						p.push(x, y);
					}
					//coloca 2 na posicao atual para indicar que ja passou por ela
					matriz[x][y] = 2;
					//imprime a matriz do mapa
					imprimirMatriz(matriz, tamanhoMatriz);
					//imprime a pilha;
					p.imprimirPilha();
					//chama a funcao recursivamente
					andar(matriz, p, auxC, y, false, tamanhoMatriz);
				//codicao que ira verificar se é possivel andar para esquerda;
				//se a posicao da direita esta dentro da matriz && se ela possui zero && se ela nao é o topo da pilha
				}else if(auxE >= 0 && matriz[x][auxE] == 0 && (x != p.topo.x || auxE != p.topo.y)){ ///andar para esquerda
					//se o No auxilar nao for igual a entrada ira ser adicionado na pilha
					if(!iguais(aux, entrada)){
						p.push(x, y);
					}
					//coloca 2 na posicao atual para indicar que ja passou por ela
					matriz[x][y] = 2;
					//imprema a matriz do mapa
					imprimirMatriz(matriz, tamanhoMatriz);
					//imprime a pilha do caminho
					p.imprimirPilha();
					//chama a função recursivamente
					andar(matriz, p, x, auxE, false, tamanhoMatriz);
				}else if(matriz[x][auxD] == 0 && auxD <= (tamanhoMatriz-1) && (x != p.topo.x || auxD != p.topo.y)){ ///andar para direita
					if(!iguais(aux, entrada)){
						p.push(x, y);
					}
					matriz[x][y] = 2;
					imprimirMatriz(matriz, tamanhoMatriz);
					p.imprimirPilha();
					andar(matriz, p, x, auxD, false, tamanhoMatriz);
					
				}else if(matriz[auxB][y] == 0 && auxB <= (tamanhoMatriz-1) && ((auxB != p.topo.x) || (y != p.topo.y))){ ///andar para baixo
					if(!iguais(aux, entrada)){
						p.push(x, y);
					}
					matriz[x][y] = 2;
					imprimirMatriz(matriz, tamanhoMatriz);
					p.imprimirPilha();
					andar(matriz, p, auxB, y, false, tamanhoMatriz);
				//se ele nao conseguir andar para nenhum lado ele ira entrar nessa condicao
				}else{
					//se o ponto atual for a saida
					if(iguais(aux, saida)){
						//empilha a coordenada do ponto atual
						p.push(x, y);
						//variavel erro auxiliar = false;
						erro = false;
						//imprime a matriz do mapa
						imprimirMatriz(matriz, tamanhoMatriz);
					//se o ponto atual nao for a saida do labirinto
					}else{
						//a posicao atual recebe -1, indicando que é um caminho invalido;
						matriz[x][y] = -1;
						//erro = true indicando que esse ponto nao faz parte do caminho/
						erro = true;	
					}
				}
				//se erro for true e ele nao chegou no final
				if(erro && !entrouNaPorraDoIf){
					//a coordanada do topo da pilha vai receber -1, indicando que nao faz parte do caminho
					matriz[p.topo.x][p.topo.y] = -1;
					//remove o topo da pilha
					p.pop();
					//imprime a matriz
					imprimirMatriz(matriz, tamanhoMatriz);
					//imprime a pilha
					p.imprimirPilha();
					//chama a funcao recursivamente passando erro como true;
					andar(matriz, p, p.topo.x, p.topo.y, true, tamanhoMatriz);
				}
			}
		}
	}
}	




