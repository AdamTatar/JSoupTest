package pl.coderslab.examples.ZaawansowanaTestJSoup;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.math.IntMath;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		// z guawy
		Joiner joiner = Joiner.on("; ").skipNulls();
		System.out.println(joiner.join("Harry", null, "Ron", "Hermione").toString());

		System.out.println(Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux"));
		System.out.println(Splitter.onPattern("n").split("Endomondo"));
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));

		List<String> places = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
		System.out.println(IntMath.isPowerOfTwo(4));
		System.out.println(IntMath.divide(5, 2, RoundingMode.HALF_UP));

		System.out.println("*****************JSOUP*****************");

		Connection connect = Jsoup.connect("http://www.onet.pl/");
		try {
			Document document = connect.get();
			Elements links = document.select("span.title");
			for (Element elem : links) {
				System.out.println(elem.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(getBirthMonthText(new LocalDate("1983-03-24")));
		System.out.println("Days to New Year: "+daysToNewYear(new LocalDate().now()));
	}

	public boolean isAfterPayDay(DateTime datetime) {
		if (datetime.getMonthOfYear() == 2) { // February is month 2!!
			return datetime.getDayOfMonth() > 26;
		}
		return datetime.getDayOfMonth() > 28;
	}

	public static Days daysToNewYear(LocalDate fromDate) {
		LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
		return Days.daysBetween(fromDate, newYear);
	}

	public boolean isRentalOverdue(DateTime datetimeRented) {
		Period rentalPeriod = new Period().withDays(2).withHours(12);
		return datetimeRented.plus(rentalPeriod).isBeforeNow();
	}

	public static String getBirthMonthText(LocalDate dateOfBirth) {
		return dateOfBirth.monthOfYear().getAsText(Locale.getDefault());
	}

}

// #### Zadanie 3
//
// 1. Za pomocą pliku pom.xml dołącz do projektu bibliotekę `guava`.
// 2. Zapoznaj się z jej możliwościami i wypróbuj wybraną z nich.
// Więcej informacji znajdziesz:
// * https://github.com/google/guava/wiki
// * http://zetcode.com/articles/guava/
// * https://www.tutorialspoint.com/guava/index.htm

// #### Zadanie 2
//
// 1. Za pomocą pliku pom.xml dołącz do projektu bibliotekę `jsoup` - poznaną
// podczas pierwszych warsztatów.
// 2. Przetestuj działanie korzystając z poniższego kodu:
// ````java
// Connection connect = Jsoup.connect("http://www.onet.pl/");
// try {
// Document document = connect.get();
// Elements links = document.select("span.title");
// for (Element elem : links) {
// System.out.println(elem.text());
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
