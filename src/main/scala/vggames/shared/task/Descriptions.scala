package vggames.shared.task

import java.util.concurrent.ConcurrentHashMap
import java.util.Scanner
import scala.collection.JavaConverters
import scala.collection.JavaConversions

class Descriptions(game : String) {

  private val descriptions = JavaConversions.asScalaConcurrentMap(new ConcurrentHashMap[String, String])

  def forGroup(groupName : String) : String = {
    descriptions.get(groupName).getOrElse {
      val desc = Option(classOf[Descriptions].getResourceAsStream("/desc/" + game + "/" + groupName + ".html")).
        map(new Scanner(_).useDelimiter("$$").next).getOrElse("No description for group " + groupName)
      descriptions.put(groupName, desc)
      desc
    }
  }
}