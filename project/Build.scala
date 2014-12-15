import sbt._
import sbt.Keys._
import sbtassembly.Plugin._
import sbtassembly.Plugin.AssemblyKeys._


object Spectrum extends Build {


  lazy val root = Project(id = "spectrum", 
        base = file(".")) aggregate(financial)
  
   lazy val financial = Project(id = "financial", 
       base = file("financial"))




}