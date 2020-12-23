package book;

import java.util.Date;

import com.bs.platform.core.utils.DateUtil;

public class UtilsTest {

	public static void main(String[] args) {
		Date date = DateUtil.addDay(new Date(), 1);
		System.out.println(date);
	}

}
