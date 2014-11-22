package com.EssencePVP.models

import com.jolbox.bonecp.BoneCPDataSource
import scala.slick.driver.MySQLDriver.simple._
import com.typesafe.config.ConfigFactory
import scala.util.Try


//Creates a Connection to the DB using the settings found in application.conf
//This will try to use MySQL first, then will fail over to SQLite

object DataSource {

  val config = ConfigFactory.load()
  val ds = new BoneCPDataSource()

  ds.setJdbcUrl(config.getString("Database.Config.main.url"))
  ds.setUsername(config.getString("Database.Config.main.user"))
  ds.setPassword(config.getString("Database.Config.main.password"))
  ds.setMinConnectionsPerPartition(config.getInt("Database.Config.main.minConnections"))
  ds.setMaxConnectionsPerPartition(config.getInt("Database.Config.main.maxConnections"))
  ds.setPartitionCount(config.getInt("Database.Config.main.partitionCount"))
  ds.setDriverClass("com.mysql.jdbc.Driver")

  println("[Data Source]: Trying to use MySQL Database...")

  Try {

    ds.getConnection
    println("[Data Source]: Connected to MySQL Database.")

  } getOrElse {

    println("[Data Source]: Failed to connect to MySQL Database.")
    println("[Data Source]: Using SQLite Instead...")

    ds.setJdbcUrl(config.getString("Database.Config.local.url"))
    ds.setDriverClass("org.sqlite.JDBC")

  }

  var DB = Database.forDataSource(ds)

}

