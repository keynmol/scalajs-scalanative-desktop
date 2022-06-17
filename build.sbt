Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val native =
  project
    .in(file("native"))
    .enablePlugins(ScalaNativePlugin)
    .settings(common)
    .settings(
      nativeConfig := {
        val conf = nativeConfig.value

        conf
          .withLinkingOptions(
            conf.linkingOptions ++ Seq(
              ((ThisBuild / baseDirectory).value / "webview" / "webview.a").toString,
              "-framework",
              "WebKit"
            )
          )
          .withEmbedResources(true)
      },
      Compile / resourceGenerators += Def.task {
        val jsLinked = (js / Compile / fastLinkJS).value
        val path =
          (js / Compile / fastLinkJS / scalaJSLinkerOutputDirectory).value
        val file = path / "main.js"

        val dest =
          (Compile / unmanagedResourceDirectories).value.head / "app.js"

        IO.copyFile(file, dest)

        List(dest)
      }
    )

lazy val js =
  project
    .in(file("js"))
    .enablePlugins(ScalaJSPlugin)
    .settings(common)
    .settings(scalaJSUseMainModuleInitializer := true)
    .settings(libraryDependencies += "com.raquo" %%% "laminar" % "0.14.2")

val common = Seq(
  scalaVersion := "3.1.2"
)
