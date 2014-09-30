package com.majecty.gdcRanking

import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by juhyeong on 2014. 9. 30..
 */

object Tables {

  case class Ranking(appname: String, name: String, score: Int)

  class Rankings(tag: Tag) extends Table[Ranking](tag, "RANKINGS") {
    def appname = column[String]("APP_NAME")
    def name = column[String]("RANKER_NAME")
    def score = column[Int]("SCORE", O.Default(0))
    def pk = primaryKey("pk_ranking", (appname, name))

    def * = (appname, name, score) <> (Ranking.tupled, Ranking.unapply)
  }

  val rankings = TableQuery[Rankings]
}
