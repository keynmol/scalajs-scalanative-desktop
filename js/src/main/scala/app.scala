import com.raquo.laminar.api.L._

import org.scalajs.dom

@main def hello =
  documentEvents.onDomContentLoaded.foreach { _ =>
    val appContainer = dom.document.querySelector("#appContainer")
    val agreed = Var(false)
    val appElement = div(
      h1(
        fontSize := "6rem",
        "You ",
        child <-- agreed.signal.map {
          case true  => "agree"
          case false => "disagree"
        }
      ),
      button(
        fontSize := "5rem",
        "Well ackchually",
        onClick --> { _ =>
          agreed.update(!_)
        }
      )
    )
    render(appContainer, appElement)
  }(unsafeWindowOwner)
