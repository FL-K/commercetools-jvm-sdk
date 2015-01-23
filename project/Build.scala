import de.johoop.jacoco4sbt.JacocoPlugin.{itJacoco, jacoco}
import sbt._
import sbt.Keys._
import sbtunidoc.Plugin.UnidocKeys._
import sbtunidoc.Plugin._

import scala.language.postfixOps

object Build extends Build {

  lazy val commonSettings: Seq[sbt.Def.Setting[_]] =
    Defaults.itSettings ++ jacoco.settings ++ itJacoco.settings ++ Release.publishSettings ++ Seq(
      parallelExecution in IntegrationTest := false,
      parallelExecution in itJacoco.Config:= false,
      testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
    )

  //the project definition have to be in .scala files for the module dependency graph
  lazy val `jvm-sdk` = (project in file(".")).configs(IntegrationTest)
    .settings(unidocSettings:_*)
    .settings(javaUnidocSettings:_*)
    .settings(documentationSettings:_*)
    .settings(commonSettings:_*)
    .aggregate(common, `java-client`, models, `test-lib`)
    .dependsOn(common, `java-client`, models, `test-lib`)

  lazy val `java-client` = project.configs(IntegrationTest).dependsOn(common).settings(commonSettings:_*)
  .settings(libraryDependencies ++= Seq("com.ning" % "async-http-client" % "1.8.7"))

  lazy val common = project.configs(IntegrationTest).settings(writeVersionSettings: _*).settings(commonSettings:_*)

  lazy val models = project.configs(IntegrationTest).dependsOn(`test-lib` % "test,it", common).settings(commonSettings:_*)

  lazy val `test-lib` = project.configs(IntegrationTest).dependsOn(`java-client`, common).settings(commonSettings:_*)
    .settings(
      libraryDependencies ++=
        festAssert ::
        junitDep ::
        junitInterface ::
        Nil
    )


  lazy val junitDep = "junit" % "junit-dep" % "4.11"
  lazy val junitInterface = "com.novocode" % "junit-interface" % "0.11"
  lazy val festAssert = "org.easytesting" % "fest-assert" % "1.4"

  val genDoc = taskKey[Seq[File]]("generates the documentation")

  val moduleDependencyGraph = taskKey[Unit]("creates an image which shows the dependencies between the SBT modules")

  val writeVersion = taskKey[Unit]("Write the version into a file.")

  val documentationSettings = Seq(
    writeVersion := {
      IO.write(target.value / "version.txt", version.value)
    },
    moduleDependencyGraph in Compile := {
      val projectToDependencies = projects.map { p =>
        val id = p.id
        val deps = p.dependencies.map(_.project).collect { case LocalProject(id) => id} filterNot(_.toLowerCase.contains("test"))
        (id, deps)
      }.toList
      val x = projectToDependencies.map { case (id, deps) =>
        deps.map(dep => '"' + id + '"' + "->" + '"' + dep + '"').mkString("\n")
      }.mkString("\n")
      val content = s"""digraph TrafficLights {
                          |$x
                          |
                          |
                          |overlap=false
                          |label="Module Structure in JVM SDK"
                          |fontsize=12;
                          |}
                          |""".stripMargin
      val graphvizFile = target.value / "deps.txt"
      val imageFile = baseDirectory.value / "documentation-resources" / "architecture" / "deps.png"
      IO.write(graphvizFile, content)
      //requires graphviz http://www.graphviz.org/Download_macos.php
      s"neato -Tpng $graphvizFile" #> imageFile !
    },
    unidoc in Compile <<= (unidoc in Compile).dependsOn(writeVersion),
    genDoc <<= (baseDirectory, target in unidoc) map { (baseDir, targetDir) =>
      val destination = targetDir / "javaunidoc" / "documentation-resources"
      IO.copyDirectory(baseDir / "documentation-resources", destination)
      IO.listFiles(destination)
    },
    genDoc <<= genDoc.dependsOn(unidoc in Compile)
  )

  val writeVersionSettings = Seq(
//sbt buildinfo plugin cannot be used since the generated class requires Scala
    sourceGenerators in Compile <+= (sourceManaged in Compile, version) map { (outDir, v) =>
      val file = outDir / "io" / "sphere" / "sdk" / "meta" / "BuildInfo.java"
      IO.write(file, """
package io.sphere.sdk.meta;

// Don't edit this file - it is autogenerated by sbt
public final class BuildInfo {
    private BuildInfo() {
      //utility class
    }

    public static String version() {
      return """" + v + """";
    }
}
""")
      Seq(file)
    }
  )
}
