package extension.thread;

/**
 * 
 * @author http://www.emarx.org
 *
 */
public class DamRunner implements Runnable {
	MultiRunnerDam dam;
	java.lang.Runnable runnable;
	
	public DamRunner(MultiRunnerDam dam, java.lang.Runnable runnable) {
		this.dam = dam;
		this.runnable = runnable;
	}
	
	public MultiRunnerDam getDam() {
		return dam;
	}
	
	public void setDam(MultiRunnerDam dam) {
		this.dam = dam;
	}
		
	public void run() {
		runnable.run();
		synchronized (dam) {
			dam.release();
		}
	}
	
}
