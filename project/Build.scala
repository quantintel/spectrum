import sbt._
import Process._
import Keys._

object Spectrum extends Build {

  lazy val root = Project(id = "spectrum", 
        base = file(".")) aggregate(financial, finsvcs)
  
   lazy val financial = Project(id = "financial", 
       base = file("financial"))
  
   lazy val finsvcs = Project(id = "finsvcs", 
       base = file("finsvcs")) 


}