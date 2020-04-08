package com.linkcheers.supervise.freemarker.schema;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bxk
 */
public class StrategyFactory {
	private static Map<String, Object> dataSource = new ConcurrentHashMap<String, Object>();

	public static Object getDataSource(String type) {
		return dataSource.get(type);
	}

	public static void register(String sourceType, Object dataSourceHandle) {
		Assert.notNull(sourceType, "sourceType can't be null");
		dataSource.put(sourceType, dataSourceHandle);
	}
}
