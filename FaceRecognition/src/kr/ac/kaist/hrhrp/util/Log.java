package kr.ac.kaist.hrhrp.util;

import java.util.logging.Logger;

public class Log {
	
	private static final Logger logger = Logger.getLogger(Log.class.getName());
		
	public static void log(boolean isDebug, String info) {
		if (isDebug) {
			logger.info(info);
		}
	}	
}
