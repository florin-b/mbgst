package websfa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

	private static final Logger logger = LogManager.getLogger(Utils.class);

	public static String getStackTrace(Exception ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

	public String getConnectionData() {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource/db_connect.txt");

		StringBuilder result = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			while (reader.ready()) {
				String line = reader.readLine();
				result.append(line);
				result.append("#");
			}
		} catch (IOException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return result.toString();

	}

}
