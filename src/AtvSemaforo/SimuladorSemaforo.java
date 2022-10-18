package AtvSemaforo;

public class SimuladorSemaforo {

	public static void main(String[] args) {

		ThreadSemafaro semafaro = new ThreadSemafaro();

		for (int i=0; i<10; i++){
			System.out.print(semafaro.getCor());
			semafaro.esperaCorMudar();
		}

		semafaro.desligarSemafaro();



	}

}
