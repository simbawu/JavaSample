package java8.datatime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TestDateTime {

	public static void main(String[] args) {
		//Instant
		Instant startInstant = Instant.now();
		System.out.println(startInstant);
		Instant endInstant = startInstant.plusMillis(1000);
		Duration duration = Duration.between(startInstant, endInstant);
		System.out.println(duration.plusMillis(1000).toMillis() + " Milliseconds.");

		//LocalDate without Timezone information
		LocalDate endDateOfYear = LocalDate.of(2018, Month.DECEMBER, 31);
		LocalDate date = LocalDate.now();
		System.out.println(date);
		long days = date.until(endDateOfYear, ChronoUnit.DAYS);
		System.out.println(days + " days left in this year.");

		//Temporal Adjusters.
		System.out.println(date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY))); // "> date"
		System.out.println(date.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))); // ">= date"
		System.out.println(date.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.TUESDAY))); // 2nd Tuesday in the month.

		//LocalTime without Timezone information
		LocalTime time = LocalTime.now();
		long hours = time.until(time.plusMinutes(65), ChronoUnit.SECONDS);
		System.out.println(hours);
		System.out.println(LocalTime.MAX);



	}

}
