
package com.progetto.viniliprogetto.DAO;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConPool {
	private static DataSource datasource;

	public static Connection getConnection() throws SQLException {
		if (datasource == null) {
			PoolProperties p = new PoolProperties();
			p.setUrl("jdbc:mysql://localhost:3307/progetto?characterEncoding=UTF8&useUnicode=yes&serverTimezone=" + TimeZone.getDefault().getID());
			p.setDriverClassName("com.mysql.cj.jdbc.Driver");
			//ricordati di modificare il nome del database, perché sono diversi per i due computer
			p.setUsername("root");
			p.setPassword("root");
			p.setMaxActive(100);
			p.setInitialSize(10);
			p.setMinIdle(10);
			p.setRemoveAbandonedTimeout(60);
			p.setRemoveAbandoned(true);
			datasource = new DataSource();
			datasource.setPoolProperties(p);
		}
		return datasource.getConnection();
	}
}
