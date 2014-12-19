import sbt._

logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases"

resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

resolvers += "scalasbt" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"

resolvers += Classpaths.sbtPluginReleases

//addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

//addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.7.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.6.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-license-report" % "1.0.0")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.7")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.0.1")

