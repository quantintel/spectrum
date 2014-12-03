import sbt._
import Process._
import Keys._


object Spectrum extends Build {


  lazy val root = Project(id = "spectrum", 
        base = file(".")) aggregate(financial)
  
   lazy val financial = Project(id = "financial", 
       base = file("financial"))
  



}