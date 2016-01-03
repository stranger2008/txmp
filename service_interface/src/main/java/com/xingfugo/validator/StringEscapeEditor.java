package com.xingfugo.validator;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;

public class StringEscapeEditor extends PropertyEditorSupport {
	private boolean escapeHTML;
	private boolean escapeJavaScript;
	private boolean escapeSQL;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript,
			boolean escapeSQL) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
		this.escapeSQL = escapeSQL;
	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				// value = StringEscapeUtils.escapeHtml(value);
				value = htmlEncode(value);
			}
			/*
			if (escapeJavaScript) {
				value = StringEscapeUtils.escapeJavaScript(value);
			}
			if (escapeSQL) {
				value = StringEscapeUtils.escapeSql(value);
			}
			*/
			setValue(value);
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	public static String htmlEncode(String source) {
		if (source == null) {
			return "";
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			switch (c) {
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			case '&':
				buffer.append("&amp;");
				break;
			case '"':
				buffer.append("&quot;");
				break;
			case 10:
			case 13:
				break;
			default:
				buffer.append(c);
			}
		}
		html = buffer.toString();
		return html;
	}
}