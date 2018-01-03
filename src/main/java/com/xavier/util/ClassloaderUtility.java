package com.xavier.util;


import com.xavier.exception.AppRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassloaderUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassloaderUtility.class);

	private ClassloaderUtility() {
	}

	public static ClassLoader getCustomClassloader(String basePath, List<String> entries) {
		List<URL> urls = new ArrayList<>();
		File file;

		if (entries != null) {
			for (String classPathEntry : entries) {
				String jarPath = (basePath + classPathEntry).replaceAll("%20", " ");
				file = new File(jarPath);
				LOGGER.debug("Loading jar : " + file.getPath());
				if (!file.exists()) {
					throw new AppRuntimeException("文件路径出错：" + classPathEntry);
				}

				try {
					urls.add(file.toURI().toURL());
				} catch (MalformedURLException e) {
					throw new AppRuntimeException("文件路径出错：" + classPathEntry);
				}
			}
		}

		ClassLoader parent = Thread.currentThread().getContextClassLoader();

		return new URLClassLoader(urls.toArray(new URL[urls.size()]), parent);
	}
}
