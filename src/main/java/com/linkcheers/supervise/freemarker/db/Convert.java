package com.linkcheers.supervise.freemarker.db;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author bxk
 */
public class Convert {
	/**
	 * oracle字段类型转java类型
	 *
	 * @param fieldType oracle字段类型
	 * @return
	 */
	public static DbColumnType processTypeOracleConvert(String fieldType) {
		String t = fieldType.toUpperCase();
		if (t.contains("CHAR")) {
			return DbColumnType.STRING;
		} else if (!t.contains("DATE") && !t.contains("TIMESTAMP")) {
			if (t.contains("NUMBER")) {
				if (t.matches("NUMBER\\(+\\d\\)")) {
					return DbColumnType.INTEGER;
				} else {
					return t.matches("NUMBER\\(+\\d{2}+\\)") ? DbColumnType.LONG : DbColumnType.DOUBLE;
				}
			} else if (t.contains("FLOAT")) {
				return DbColumnType.FLOAT;
			} else if (t.contains("clob")) {
				return DbColumnType.CLOB;
			} else if (t.contains("BLOB")) {
				return DbColumnType.OBJECT;
			} else if (t.contains("binary")) {
				return DbColumnType.BYTE_ARRAY;
			} else {
				return t.contains("RAW") ? DbColumnType.BYTE_ARRAY : DbColumnType.STRING;
			}
		} else {
			return DbColumnType.DATE;
		}
	}

	/**
	 * oracle字段类型转JDBC类型代码
	 *
	 * @param fieldType oracle字段类型
	 * @return
	 */
	public static DbColumnType processTypeJdbcConvert(String fieldType) {
		String t = fieldType.toUpperCase();
		if (t.contains("CHAR")) {
			return DbColumnType.VARCHAR;
		} else if (!t.contains("DATE") && !t.contains("TIMESTAMP")) {
			if (t.contains("NUMBER")) {
				if (t.matches("NUMBER\\(+\\d\\)")) {
					return DbColumnType.INTEGER;
				} else {
					return t.matches("NUMBER\\(+\\d{2}+\\)") ? DbColumnType.JDBC_BIGINT : DbColumnType.JDBC_DOUBLE;
				}
			} else if (t.contains("FLOAT")) {
				return DbColumnType.JDBC_FLOAT;
			} else if (t.contains("clob")) {
				return DbColumnType.JDBC_CLOB;
			} else if (t.contains("BLOB")) {
				return DbColumnType.JDBC_BLOB;
			} else {
				return t.contains("RAW") ? DbColumnType.JDBC_BINARY : DbColumnType.VARCHAR;
			}
		} else {
			return DbColumnType.JDBC_DATE;
		}
	}

	/**
	 * mysql字段类型转java类型
	 *
	 * @param fieldType mysql字段类型
	 * @return
	 */
	public DbColumnType processTypeMySqlConvert(String fieldType) {
		String t = fieldType.toLowerCase();
		if (!t.contains("char") && !t.contains("text")) {
			if (t.contains("bigint")) {
				return DbColumnType.LONG;
			} else if (t.contains("int")) {
				return DbColumnType.INTEGER;
			} else if (!t.contains("date") && !t.contains("time") && !t.contains("year")) {
				if (t.contains("text")) {
					return DbColumnType.STRING;
				} else if (t.contains("bit")) {
					return DbColumnType.BOOLEAN;
				} else if (t.contains("decimal")) {
					return DbColumnType.BIG_DECIMAL;
				} else if (t.contains("clob")) {
					return DbColumnType.CLOB;
				} else if (t.contains("blob")) {
					return DbColumnType.BLOB;
				} else if (t.contains("binary")) {
					return DbColumnType.BYTE_ARRAY;
				} else if (t.contains("float")) {
					return DbColumnType.FLOAT;
				} else if (t.contains("double")) {
					return DbColumnType.DOUBLE;
				} else {
					return !t.contains("json") && !t.contains("enum") ? DbColumnType.STRING : DbColumnType.STRING;
				}
			} else {
				return DbColumnType.DATE;
			}
		} else {
			return DbColumnType.STRING;
		}
	}

	/**
	 * 对象属性转换为字段  例如：userName to user_name
	 *
	 * @param property 字段名
	 * @return
	 */
	public static String propertyToField(String property) {
		if (null == property) {
			return "";
		}
		char[] chars = property.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char c : chars) {
			if (CharUtils.isAsciiAlphaUpper(c)) {
				sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 字段转换成对象属性 例如：user_name to userName
	 *
	 * @param field
	 * @return
	 */
	public static String fieldToProperty(String field) {
		if (null == field) {
			return "";
		}
		field = field.toLowerCase();
		char[] chars = field.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '_') {
				int j = i + 1;
				if (j < chars.length) {
					sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 表名称转换
	 *
	 * @param str
	 * @return
	 */
	public static String tableNameConvert(String str) {
		String result = "";
		str = str.toLowerCase();
		if (str.indexOf("_") > -1) {
			String[] arr = str.split("_");
			List<String> list = Arrays.asList(arr);
			for (int i = 0; i < list.size(); i++) {
				String cap = initCap(list.get(i));
				result = result + cap;
			}
		} else {
			result = initCap(str);
		}
		return result;
	}

	/**
	 * 表名称转换
	 *
	 * @param str
	 * @return
	 */
	public static String initCap(String str) {
		char[] chars = str.toCharArray();
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] = (char) (chars[0] - 32);
		}
		return new String(chars);
	}

	public static String subString(String str, String flag) {
		return str.substring(0, str.indexOf(flag));
	}
}
