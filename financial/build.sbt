name := "spectrum-financial"

version := "0.0.1-SNAPSHOT"

organization := "org.quantintel"

licenses     += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.11.0")

scalaSource in Compile := baseDirectory.value / "src/main/scala"

scalaSource in Test := baseDirectory.value / "src/test/scala"

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
  "scala-tools.org" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/",
  "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/")


libraryDependencies ++= Seq(
  "org.scala-lang"        % "scala-library"         % "2.11.4",
  "org.scala-lang"        % "scala-compiler"        % "2.11.4",
  "org.scala-lang"        % "scalap"                % "2.11.4",
  "com.typesafe.play"     % "play_2.11"             % "2.3.5",
  "org.scalatest"         % "scalatest_2.11"        % "2.2.2"       % "test",
  "org.scalastyle"        % "scalastyle_2.11"       % "0.6.0"       % "test"
)


publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>http://www.quantintel.com</url>
    <licenses>
      <license>
        <name>Apache 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:pauljbernard/scala-arm.git</url>
      <connection>scm:git:git@github.com:pauljbernard/quantintel/spectrum.git</connection>
    </scm>
    <developers>
      <developer>
        <id>pauljbernard</id>
        <name>Paul Bernard</name>
        <url>http://www.quantintel.com</url>
      </developer>
    </developers>)