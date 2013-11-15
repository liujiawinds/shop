package org.liujia.core.util;
import org.compass.gps.CompassGps;
import org.springframework.beans.factory.InitializingBean;


public class CompassIndexBuilder implements InitializingBean {

	private int lazyTime = 30;// 索引操作线程延时，单位s
	private CompassGps compassGps;

	private Thread indexThread = new Thread() {
		@Override
		public void run() {
			try {
				Thread.sleep(lazyTime * 1000);
				compassGps.index();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	public void afterPropertiesSet() throws Exception {
		indexThread.setDaemon(true);
		indexThread.setName("Compass Indexer");
		indexThread.start();
	}

	public void index() {
		compassGps.index();
	}

	public void setLazyTime(int lazyTime) {
		this.lazyTime = lazyTime;
	}

	public void setCompassGps(CompassGps compassGps) {
		this.compassGps = compassGps;
	}
}