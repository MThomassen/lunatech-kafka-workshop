//ensimeIgnoreScalaMismatch in ThisBuild := true

lazy val kafkaWorkshop = (project in file("."))
  .settings(
    name := "Lunatech Kakfa Workshop"
  )
  .aggregate(kafkaProducer, kafkaConsumer, kafkaStreams)

lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  version := "1.0.0",
  organization := "com.lunatech",
  organizationName := "lunatech",
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalatest" %% "scalatest" % "3.0.5" % Test 
  )
)

lazy val common = (project in file("common"))
  .settings(commonSettings)
  .settings(
    name := "Common Domain Model",
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8"
    )
  )

lazy val kafkaProducer = (project in file("producer"))
  .settings(commonSettings)
  .settings(
    name := "Kafka Producer",
    libraryDependencies ++= Seq(
      "org.apache.kafka" % "kafka-clients" % "2.1.1"
    )
  )
  .dependsOn(common)

lazy val kafkaConsumer = (project in file("consumer"))
  .settings(commonSettings)
  .settings(
    name := "Kafka Consumer",
    libraryDependencies ++= Seq(
      "org.apache.kafka" % "kafka-clients" % "2.1.1"
    )
  )
  .dependsOn(common)

lazy val kafkaStreams = (project in file("streams"))
  .settings(commonSettings)
  .settings(
    name := "Kafka Streams",
    libraryDependencies ++= Seq(
      "org.apache.kafka" %% "kafka-streams-scala" % "2.1.1"
    )
  )
  .dependsOn(common)
