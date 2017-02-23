
public class Pilha {
	No topo;
	
	public void push(int x, int y){
		if(this.topo == null){
			this.topo = new No();
			this.topo.x = x;
			this.topo.y = y;
			this.topo.ante = null;
		}else{
			No aux = new No();
			aux.x = x;
			aux.y = y;
			aux.ante = this.topo;
			this.topo = aux;
		}
	}
	
	public No pop(){
		No retorno = this.topo;
		this.topo = this.topo.ante;		
		return retorno;
	}
	
	public void imprimirPilha(){
		System.out.println("Pilha\n");
		No aux = this.topo;
		while(aux != null){
			System.out.println("x: "+aux.x+",y: "+aux.y+"\n");
			aux = aux.ante;
		}
	}	
	
	public boolean equals(Object o){
		boolean result = false;
		if(o instanceof No){
			if(((No) o).x == this.topo.x && ((No) o).y == this.topo.y){
				result = true;
			}
		}
		return result;
	}
	
	
	
	
}

class No{
	int x;
	int y;
	No ante;
	
	public No(){}
	
	public No(int x, int y){
		this.x = x;
		this.y = y;
		this.ante = null;
	}
	
	public String toString(){
		return "No pos X: "+this.x+", pos Y:"+this.y;
	}
	
	
}
