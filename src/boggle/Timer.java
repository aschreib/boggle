package boggle;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Timer extends Thread {

	private Client client;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;

	public Timer(Client client) {
		this.client = client;
	}

	public void startTimer() throws InterruptedException {

		startTime = new GregorianCalendar();
		endTime = new GregorianCalendar();
		endTime.set(Calendar.MINUTE, startTime.get(Calendar.MINUTE) + 3);

		System.out.println("Start time: " + startTime.get(Calendar.MINUTE) + ":" + startTime.get(Calendar.SECOND));
		System.out.println("End time: " + endTime.get(Calendar.MINUTE) + ":" + endTime.get(Calendar.SECOND));

		this.start();

	}

	@Override
	public void run() {

		int endMin = endTime.get(Calendar.MINUTE);
		int endSec = endTime.get(Calendar.SECOND);

		System.out.println(endMin + ":" + endSec);

		while (GregorianCalendar.getInstance().get(Calendar.MINUTE) != endMin
				|| GregorianCalendar.getInstance().get(Calendar.SECOND) != endSec) {

			// change text on gui
			TimePanel timePanel = client.getGui().getInputPanel().getTimePanel();
			int displayedSecond = timePanel.getSecond();
			int displayedMinute = timePanel.getMinute();

			if (displayedSecond == 0) {
				timePanel.setSecond(59);
				timePanel.setMinute(displayedMinute - 1);

			} else {
				timePanel.setSecond(displayedSecond - 1);
			}

			timePanel.setTimerLabelText();

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		// game over
		client.getGui().disableButtons();
		client.sendWords();// send words to server because timer is up

	}

}
