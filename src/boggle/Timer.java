package boggle;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Timer extends Thread {

	private Client client;
	private Date startTime;
	//private int startMinute;
	//private int startSecond;
	private int timeElapsed;// 3 minutes

	private CountDownLatch latch;

	public Timer(Client client) {
		this.client = client;
		//startMinute = 0;
		//startSecond = 0;
		timeElapsed = 0;

		latch = new CountDownLatch(3);
	}

	public void startTimer() throws InterruptedException {
		//GregorianCalendar startTime = (GregorianCalendar) GregorianCalendar.getInstance();
		startTime = GregorianCalendar.getInstance().getTime();
		//startMinute = startTime.get(Calendar.MINUTE);
		//startSecond = startTime.get(Calendar.SECOND);
		this.start();
		latch.await();
		// end game
	}

	@Override
	public void run() {

		do {

			// check current time
			//GregorianCalendar currentTime = (GregorianCalendar) GregorianCalendar.getInstance();
			//int currentMinute = currentTime.get(Calendar.MINUTE);
			//int currentSecond = currentTime.get(Calendar.SECOND);
			
			Date currentTime = GregorianCalendar.getInstance().getTime();
			long difference = currentTime.getTime() - startTime.getTime();

			// calculate difference
			//int minuteDifference = currentMinute - startMinute;
			//int secondDifference = currentSecond - startSecond;

			// change text on gui
			TimePanel timePanel = client.getGui().getInputPanel().getTimePanel();
			timePanel.setMinute((int) (timePanel.getMinute() - TimeUnit.MILLISECONDS.toMinutes(difference)));
			timePanel.setSecond((int) (timePanel.getSecond() - TimeUnit.MILLISECONDS.toSeconds(difference)));
			timePanel.setTimerLabelText();

			// update timeElapsed
			//timeElapsed = minuteDifference;

			// decrement countdownlatch on certain condition

			// only check thread every second
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while (timeElapsed <= 3);

		client.sendWords();//send words to server because timer is up
	}

}
