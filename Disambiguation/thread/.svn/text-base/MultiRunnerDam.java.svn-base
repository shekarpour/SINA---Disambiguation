package org.aksw.sina.thread;

/**
 * 
 * @author http://www.emarx.org
 *
 */
public class MultiRunnerDam {
	
	Integer numberOfFinishedThreads = 0;
	Runnable runnables[];
	
	public MultiRunnerDam(Runnable... runnables) {		
		this.runnables = runnables;
	}
	
	public void run(){
		Integer threadLength = runnables.length;
		for(Runnable runnable: runnables){
			Thread thread = new Thread(new DamRunner(this, runnable));			
			thread.start();
		}
		
		synchronized (this) {			
			while(threadLength != numberOfFinishedThreads) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void release(){
		numberOfFinishedThreads++;
		notify();
	}
}
