package com.tengen;

import static spark.Spark.get;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldSparkFreeMarkerStyle {

	public static void main(String[] args) throws TemplateException,
			IOException {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreeMarkerStyle.class, "/");

		get(new Route("/") {
			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration
							.getTemplate("hello.ftl");

					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "FreeMarker");
					helloTemplate.process(helloMap, writer);
					System.out.println(writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				}

				return writer;
			}
		});

	}

}
