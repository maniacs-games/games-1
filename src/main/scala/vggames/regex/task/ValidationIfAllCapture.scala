package vggames.regex.task;

import vggames.regex.task.MatcherTargets._
import java.util.regex.Matcher;
import vggames.shared.task.status.Ok
import vggames.shared.task.JudgedTask
import vggames.shared.task.status.Failed

class ValidationIfAllCapture(captureGroupTargets : String*) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    if (matcher.groupCount() != captureGroupTargets.length) {
      return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o cont&eacute;m "
        + captureGroupTargets.length + " grupo(s) de captura.");
    }
    return new Ok();
  }

}