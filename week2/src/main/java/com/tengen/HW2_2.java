package com.tengen;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

public class HW2_2 {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));

		DB database = client.getDB("students");
		DBCollection collection = database.getCollection("grades");
		
		/* Trying to delete the lowest grade in homework for each student. Previously, 800 rows. After operation, 600 rows. */
		for (int i = 0; i < 200; i++) {
			QueryBuilder builder = QueryBuilder.start("student_id").is(i).and("type").is("homework");
			DBCursor cursor = collection.find(builder.get()).sort(new BasicDBObject("student_id",1).append("score",1));
			
			try {
				while (cursor.hasNext()) {

					DBObject cur = cursor.next();
					collection.remove(cur);
					break;
				}
			} finally {
				cursor.close();
			}
		}
		
		System.out.println(collection.count());
		

	}

}
