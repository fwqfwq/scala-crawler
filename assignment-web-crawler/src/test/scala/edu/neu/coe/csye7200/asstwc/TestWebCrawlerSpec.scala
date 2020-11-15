package edu.neu.coe.csye7200.asstwc

import java.net.{MalformedURLException, URL}

import org.scalatest._
import org.scalatest.concurrent.{Futures, ScalaFutures}
import org.scalatest.matchers.should
import org.scalatest.tagobjects.Slow
import org.scalatest.time._

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util._

/**
 * @author scalaprof
 */
class TestWebCrawlerSpec extends FlatSpec with should.Matchers with Futures with ScalaFutures with TryValues with Inside {

  val goodURL = "http://www1.coe.neu.edu/~rhillyard/indexSafe.html"
  val badURL = "http://www1.coe.neu.edu/junk"

  //From 'WebCrawlerSpec', last two parts in annotations
  "crawler(Seq[URL])" should "succeed for test.html, depth 2" in {
    val project = "/Users/scalaprof/ScalaClass/FunctionalProgramming"
    val dir = "src/test/scala"
    val pkg = "edu/neu/coe/scala/crawler"
    val file = "test.html"
    val args = List(s"file://$project/$dir/$pkg/$file")
    val tries = for (arg <- args) yield Try(new URL(arg))
    //    println(s"tries: $tries")
    val usft = for {us <- MonadOps.sequence(tries)} yield WebCrawler.crawler(2, us)
    whenReady(MonadOps.flatten(usft), timeout(Span(20, Seconds))) { s => Assertions.assert(s.length == 1) } //2
  }
  it should "succeed for test.html, depth 3" in {
    val project = "/Users/scalaprof/ScalaClass/FunctionalProgramming"
    val dir = "src/test/scala"
    val pkg = "edu/neu/coe/scala/crawler"
    val file = "test.html"
    val args = List(s"file://$project/$dir/$pkg/$file")
    val tries = for (arg <- args) yield Try(new URL(arg))
    println(s"tries: $tries")
    val usft = for {us <- MonadOps.sequence(tries)} yield WebCrawler.crawler(3, us)
    val usf = MonadOps.flatten(usft)
    whenReady(usf, timeout(Span(60, Seconds))) { us =>
      println(us)
      us.size shouldBe 1 //177
    }
  }
}
