package com.devdaily.sarah.plugin.googlesearch

import java.util.regex.Pattern
import java.net.URLEncoder
import javax.script.ScriptEngineManager

object SearchUtils {

  def matchesSearchPattern(phrase: String) = phrase.matches("google.*")

  def getSearchString(phrase: String): Option[String] = {
      val p = Pattern.compile("google (.*)")
      val m = p.matcher(phrase)
      if (m.find) {
          Some(m.group(1).trim)
      } else {
          None
      }
  }
  
  def buildUrl(searchText: String) = {
      "https://www.google.com/#q=" + URLEncoder.encode(searchText, "UTF-8") 
  }

  def runAppleScriptCommand(command: String) = {
      val scriptEngineManager = new ScriptEngineManager
      val engine = scriptEngineManager.getEngineByName("AppleScript")
      engine.eval(command)
  }


}






