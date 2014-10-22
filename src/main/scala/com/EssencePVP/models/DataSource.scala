package com.EssencePVP.models

import com.jolbox.bonecp.BoneCPDataSource
import scala.slick.driver.MySQLDriver.simple._
import com.typesafe.config.ConfigFactory

object DataSource {
  val config = ConfigFactory.load()
  //Class.forName("com.mysql.jdbc.Driver")

  val ds = new BoneCPDataSource()
  ds.setJdbcUrl(config.getString("Database.Config.main.url"))
  ds.setUsername(config.getString("Database.Config.main.user"))
  ds.setPassword(config.getString("Database.Config.main.password"))
  ds.setMinConnectionsPerPartition(config.getInt("Database.Config.main.minConnections"))
  ds.setMaxConnectionsPerPartition(config.getInt("Database.Config.main.maxConnections"))
  ds.setPartitionCount(config.getInt("Database.Config.main.partitionCount"))
  ds.setDriverClass("com.mysql.jdbc.Driver")

  val DB = Database.forDataSource(ds)
}
