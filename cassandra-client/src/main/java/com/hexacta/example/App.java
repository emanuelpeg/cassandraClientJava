package com.hexacta.example;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.List;

/**
 *
 */
public class App {
	private final static String IP = "192.168.0.106";

	public static void main(String[] args) {

		/*
		 * Cluster cluster = null; try { cluster = Cluster.builder()
		 * .addContactPoint(IP) .build(); Session session = cluster.connect();
		 * 
		 * ResultSet rs = session.execute("select release_version from system.local");
		 * Row row = rs.one(); System.out.println(row.getString("release_version"));
		 * }finally { if (cluster != null) cluster.close(); }
		 */

		Cluster cluster = null;
		try {
			cluster = Cluster.builder().addContactPoint(IP).build();
			Session session = cluster.connect();

			session.execute("BEGIN BATCH " 
			        + "INSERT INTO hexactaGeeklunch.Empleado (id, userName, dni, legajo) "
					+ "VALUES (45, 'MarioBros', 34547689, 56 );"
					+ "INSERT INTO hexactaGeeklunch.EmpleadoPorNombre (id, userName) " + "VALUES (45, 'MarioBros');"
					+ "APPLY BATCH;  ");

		} finally {
			if (cluster != null)
				cluster.close();
		}

	}
}
