package com.tengen;

import static spark.Spark.get;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldMongoDBSparkFreeMarkerStyle {

	public static void main(String[] args) throws TemplateException,
			IOException {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreeMarkerStyle.class, "/");

		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("m101j");
		final DBCollection collection = database.getCollection("course");

		get(new Route("/") {
			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration
							.getTemplate("hello.ftl");

					DBObject document = collection.findOne();
					helloTemplate.process(document, writer);
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
