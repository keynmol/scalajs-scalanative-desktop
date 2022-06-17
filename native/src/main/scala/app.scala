import webview.functions.*
import webview.types.*

import scala.scalanative.unsafe.*
import scala.io.Source

@main def hello =
  val w = webview_create(0, null)
  webview_set_size(w, 1024, 600, 0)
  webview_set_title(w, c"Test")

  val html =
    c"""
  <!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" 
            content="width=device-width, initial-scale=1.0" />
      <title>Document</title>
    </head>
    <body>
      <div id = "appContainer" />
    </body>
  </html>"""

  webview_set_html(w, html)

  val js = Source
    .fromInputStream(
      this.getClass().getResourceAsStream("app.js")
    )
    .getLines()
    .mkString("\n")

  Zone { implicit z =>
    webview_init(w, toCString(js))
  }

  webview_run(w)
  webview_destroy(w)
