name := "spectrum"

version := "0.0.1-SNAPSHOT"

organization := "org.quantintel"

licenses     += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.11.0")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq("-unchecked", "-deprecation")

scalacOptions in (Compile, doc) ++= Seq("-unchecked", "-deprecation", "-diagrams", "-groups", "-implicits", "-skip-packages", "samples")

resolvers ++= Seq(
        "snapshots-repo" at "http://scala-tools.org/repo-snapshots",
				"Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
				"apache-releases" at "http://repository.apache.org/content/repositories/releases/",
				"oss-sonatype-releases" at "http://oss.sonatype.org/content/repositories/releases",
				"central" at "http://repo1.maven.org/maven2/",
        "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
				"snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
				"scala-tools.org" at "https://oss.sonatype.org/content/groups/scala-tools/")


libraryDependencies ++= Seq(
  "org.scala-lang"        % "scala-library"         % "2.11.4",
  "org.scala-lang"        % "scala-compiler"        % "2.11.4",
  "org.scala-lang"        % "scalap"                % "2.11.4",
  "org.scalatest"         % "scalatest_2.11"        % "2.2.2"       % "test",
  "org.scalastyle"        % "scalastyle_2.11"       % "0.6.0"       % "test"
)

instrumentSettings

ScoverageKeys.minimumCoverage := 70

ScoverageKeys.failOnMinimumCoverage := false

ScoverageKeys.highlighting := {
  if (scalaBinaryVersion.value == "2.11.4") false
  else false
}

publishArtifact in Test := false

parallelExecution in Test := false

