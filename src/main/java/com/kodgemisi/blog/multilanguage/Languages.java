package com.kodgemisi.blog.multilanguage;

import org.springframework.context.i18n.LocaleContextHolder;

enum Languages {

	TR, EN, FR;

	public static String getDefaultLanguage() {
		return LocaleContextHolder.getLocale().getLanguage().toUpperCase();
	}

}
