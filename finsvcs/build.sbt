// set the main Scala source directory to be <base>/src
scalaSource in Compile := baseDirectory.value / "src"

// set the Scala test source directory to be <base>/test
scalaSource in Test := baseDirectory.value / "test"
