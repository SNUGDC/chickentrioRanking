package com.majecty.gdcRanking

import org.scalatra._
import scalate.ScalateSupport
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession
import Tables._

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
  
}
