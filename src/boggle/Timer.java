package boggle;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.CountDownLatch;

public class Timer extends Thread {

	private Client client;
	private int startMinute;
	private int startSecond;
	private int timeElapsed;// 3 minutes

	private CountDownLatch latch;

	public Timer(Client client) {
		this.client = client;
		startMinute = 0;
		startSecond = 0;
		timeElapsed = 0;

		latch = new CountDownLatch(3);
	}

	public void startTimer() throws InterruptedException {
		GregorianCalendar startTime = (GregorianCalendar) GregorianCalendar.getInstance();
		startMinute = startTime.get(Calendar.MINUTE);
		startSecond = startTime.get(Calendar.SECOND);
		this.start();
		latch.await();
		// end game
	}

	@Override
	public void run() {
		do {
			// check current time
			GregorianCalendar currentTime = (GregorianCalendar) GregorianCalendar.getInstance();
			int currentMinute = currentTime.get(Calendar.MINUTE);
			int currentSecond = currentTime.get(Calendar.SECOND);

			// calculate difference
			int minuteDifference = currentMinute - startMinute;
			int secondDifference = currentSecond - startSecond;

			// change text on gui
			TimePanel timePanel = client.getGui().getWordsPanel().getTimePanel();
			timePanel.setMinute(timePanel.getMinute() - minuteDifference);
			timePanel.setSecond(timePanel.getSecond() - secondDifference);
			timePanel.setTimerLabelText();

			// update timeElapsed
			timeElapsed = minuteDifference;

			// decrement countdownlatch on certain condition

		} while (timeElapsed <= 3);
	}

}
