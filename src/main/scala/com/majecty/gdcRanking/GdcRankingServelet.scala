package com.majecty.gdcRanking

import org.scalatra._
import scalate.ScalateSupport

class GdcRankingServelet extends GdcrankingStack {

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
