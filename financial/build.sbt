scalaVersion := "2.11.4"

// set the main Scala source directory to be <base>/src
scalaSource in Compile := baseDirectory.value / "src/main/scala"

// set the Scala test source directory to be <base>/test
scalaSource in Test := baseDirectory.value / "src/test/scala"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test"