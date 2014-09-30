import com.majecty.gdcRanking._
import org.scalatra._
import javax.servlet.ServletContext

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import scala.slick.jdbc.JdbcBackend.Database

class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)

  val cpds = new ComboPooledDataSource

  logger.info("Created c3p0 connection pool")

  override def init(context: ServletContext) {
    val db = Database.forDataSource(cpds)
    context.mount(new GdcRankingServlet(db), "/*")
  }

  private def closeDBConnection(): Unit = {
    logger.info("Closing c3po connection pool")
    cpds.close();
  }

  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    closeDBConnection
  }
}
