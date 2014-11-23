name := "spectrum"

version := "0.1"	

organization := "org.quantintel"

licenses     += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.11.0")

resolvers ++= Seq(
        "snapshots-repo" at "http://scala-tools.org/repo-snapshots",
				"Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
				"apache-releases" at "http://repository.apache.org/content/repositories/releases/",
				"oss-sonatype-releases" at "http://oss.sonatype.org/content/repositories/releases",
				"central" at "http://repo1.maven.org/maven2/",
        "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
				"snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
				"scala-tools.org" at "https://oss.sonatype.org/content/groups/scala-tools/")


libraryDependencies <<= scalaVersion { scala_version => Seq(
  "org.scala-lang" % "scala-library" % "2.11.4",
  "org.scala-lang" % "scala-compiler" % "2.11.4",
  "org.scala-lang" % "scala-reflect" % "2.11.4",
  "org.scala-lang" % "scalap" % "2.11.4",
  "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test",
  "org.scalastyle" % "scalastyle_2.11" % "0.6.0" % "test")
}


publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false

javacOptions ++= Seq()

javaOptions += "-Xmx2G"

scalacOptions ++= Seq("-deprecation", "-unchecked")

maxErrors := 20 

pollInterval := 1000

logBuffered := false

cancelable := true

