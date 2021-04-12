package com.quzzar.server.souls.character.souls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import com.quzzar.server.souls.Souls;

public class SoulsConnection {

	private Connection connection;
	private String host, database, username, password;
	private int port;
	
	public static final int defaultSoulCount = 1;

	public SoulsConnection() {

		host = "67.20.55.73";
		port = 3306;
		database = "apexMC125590";
		username = "apexMC125590";
		password = "47e852d6a2";

	}

	private void openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) {
			return;
		}

		synchronized (this) {
			if (connection != null && !connection.isClosed()) {
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		}
	}

	public void fillTableWithInfo() {

		BukkitRunnable r = new BukkitRunnable() {
			@Override
			public void run() {
				try {
					openConnection();
					Statement statement = connection.createStatement();
					
					ResultSet result = statement.executeQuery("select UUID, Souls from Souls;");
					while (result.next()) {
					    String uuid = result.getString("UUID");
						int soulCount = result.getInt("Souls");
					    SoulManager.getSoulTable().put(UUID.fromString(uuid), soulCount);
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		r.runTaskAsynchronously(Souls.instance);

	}
	
	public void addNewEntry(UUID pUUID) {
		
		BukkitRunnable r = new BukkitRunnable() {
			@Override
			public void run() {
				try {
					openConnection();
					Statement statement = connection.createStatement();
					
					statement.executeUpdate("insert into Souls (UUID, Souls) values ('"+pUUID.toString()+"', "+defaultSoulCount+");");
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		r.runTaskAsynchronously(Souls.instance);
		
	}
	
	public void updateEntry(UUID pUUID, int soulAmt) {
		
		BukkitRunnable r = new BukkitRunnable() {
			@Override
			public void run() {
				try {
					openConnection();
					Statement statement = connection.createStatement();
					
					statement.executeUpdate("update Souls set UUID = '"+pUUID.toString()
						+"', Souls = "+soulAmt+" where UUID = '"+pUUID.toString()+"';");
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		r.runTaskAsynchronously(Souls.instance);
		
	}

}
