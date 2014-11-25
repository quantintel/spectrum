name := "spectrum-financial"

version := "0.0.1-SNAPSHOT"

organization := "org.quantintel"

licenses     += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.11.4"

scalaSource in Compile := baseDirectory.value / "src/main/scala"

scalaSource in Test := baseDirectory.value / "src/test/scala"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test"

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