val dottyVersion = dottyLatestNightlyBuild.get

lazy val root = (project in file(".")).
  settings(
    name := "dotty-simple",

    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "ch.epfl.lamp" % "dotty_0.7" % dottyVersion % "test->runtime"),
      
    testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
  )
