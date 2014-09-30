package com.majecty.gdcRanking

import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by juhyeong on 2014. 9. 30..
 */

object Tables {

  class Rankings(tag: Tag) extends Table[(Int, String, Int)](tag, "RANKINGS") {
    def id = column[Int]("RANKING_ID", O.PrimaryKey)
    def name = column[String]("RANKER_NAME")
    def score = column[Int]("SCORE")

    override def * : ProvenShape[(Int, String, Int)] = (id, name, score)
  }

  val rankings = TableQuery[Rankings]
}
