package AtvSemaforo;



public class ThreadSemafaro implements Runnable {

	private CorSemaforo cor;
	private boolean stop;
	private boolean alterarCor;

	public ThreadSemafaro(){
		this.cor = CorSemaforo.VERMELHO;

		this.stop = false;
		this.alterarCor = false;

		new Thread(this).start();
	}

	@Override
	public void run() {

		while(!stop){
			try {
				switch (this.cor) {
				case VERMELHO:
					Thread.sleep(2000);
					break;
				case AMARELO:
					Thread.sleep(300);
					break;
				case VERDE:
					Thread.sleep(1000);
					break;
				default:
					break;
				}
				Thread.sleep(this.cor.getTempoEspera());
				this.mudarCor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void mudarCor(){
		switch (this.cor) {
		case VERMELHO:
			this.cor = CorSemaforo.VERDE;
			break;
		case AMARELO:
			this.cor = CorSemaforo.VERMELHO;
			break;
		case VERDE:
			this.cor = CorSemaforo.AMARELO;
			break;
		default:
			break;
		}
		this.alterarCor = true;
		notify();
	}
	
	public synchronized void esperaCorMudar(){
		while(!this.alterarCor){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.alterarCor = false;
	}
	
	public synchronized void desligarSemafaro(){
		this.stop = true;
	}

	public CorSemaforo getCor() {
		return cor;
	}
}
