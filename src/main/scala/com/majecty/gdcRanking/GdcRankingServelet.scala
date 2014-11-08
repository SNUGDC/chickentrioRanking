package com.majecty.gdcRanking

import org.json4s.JsonAST.JArray
import org.json4s.jackson.Json
import org.scalatra._
import scalate.ScalateSupport
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession
import Tables._

import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read, write}

import org.json4s._
import org.json4s.jackson.Serialization


class GdcRankingServlet(db: Database) extends GdcrankingStack {

//  val db: Database

  get("/create-tables") {
    db withDynSession {
      rankings.ddl.create
    }
  }

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
        Hello juhyeong.
      </body>
    </html>
  }

  before("/app/*") {
    contentType = "application/json"
  }

  post("/app/:appname/rankings") {
    object formatChecker {
      implicit def stringToInt(x: String) = x.toInt

      def apply[A](name: String, score: String)(block: (String, Int) => A) = block(name, score)
    }

    db withDynSession {
      formatChecker(params("name"), params("score")) { (name, score) =>
        val appName = params("appname")

        val searched = rankings.filter(ranking => ranking.name === name.take(15)).firstOption
        searched match {
          case Some(ranking) =>
            println (ranking.score)
            if (score > ranking.score) {
              rankings.filter(ranking => ranking.name === name.take(15))
                .map(ranking => ranking.score)
                .update(score)
            }
          case None =>
            rankings += Ranking(Option.empty, appName.take(30), name.take(15), score)
        }
        if (rankings.length.run > 7) {
          val toDelete = rankings.sortBy(_.score.desc).drop(7).list
          toDelete.foreach { deletedRanker => {
            rankings.filter(ranker => ranker.rank_id === deletedRanker.rank_id).delete
          }}
        }


        val json = ("status" -> "ok")
        compact(render(json))
      }
    }
  }

  get("/app/:appname/rankings") {
    db withDynSession {
      implicit val formats = Serialization.formats(NoTypeHints)
      val allRankings = rankings.filter(_.appname === params("appname").take(30)).sortBy(_.score.desc).list
      val json = Extraction.decompose(allRankings)

      compact(render(json))
    }
  }

}
