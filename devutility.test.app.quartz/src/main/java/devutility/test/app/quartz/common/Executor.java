package devutility.test.app.quartz.common;

import java.util.Date;

import devutility.internal.text.format.DateFormatUtils;

public class Executor {
	public static void execute(String name, long costMillis) {
		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + costMillis);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("[%s]: ", DateFormatUtils.format(startDate, "yyyy-MM-dd HH:mm:ss:SSS")));
		stringBuilder.append(String.format("%s executing on %s, ", name, Thread.currentThread().getName()));
		stringBuilder.append(String.format("will finish at %s.", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss:SSS")));
		System.out.println(stringBuilder.toString());

		try {
			Thread.sleep(costMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}