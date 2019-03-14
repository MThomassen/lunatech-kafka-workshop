package com.lunatech.kafka.streams

import java.util.Properties

import com.lunatech.kafka.common.scala.StockMessage
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object KafkaStreamsMain extends App {
  private val AUTHOR = _ // f.e. "michiel"
  private val BOOTSTRAP_SERVERS = "mtn.westeurope.cloudapp.azure.com:9092"
  private val INPUT_TOPIC = "stock-ticker"

  import org.apache.kafka.streams.scala.ImplicitConversions._
  import org.apache.kafka.streams.scala.Serdes._

  implicit val stockMessageSerdes = new StockMessageSerdes()

  val props = new Properties()
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS)
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, s"stock-ticker-$AUTHOR")

  val builder = new StreamsBuilder()

  val stockTickerStream: KStream[String, StockMessage] = builder
    .stream[String, StockMessage](INPUT_TOPIC)

  stockTickerStream

//    // Only emmit largest value
//      .groupByKey
//      .reduce { case (stockMessageA, stockMessageB) =>
//          Seq(stockMessageA, stockMessageB).maxBy(_.value)
//      }.toStream

//    // Processor API can be used to access Kafka Timestamp or State Store
//    .transformValues(() =>
//      new ValueTransformer[StockMessage, StockMessage] {
//        var ctx: ProcessorContext = _
//
//        override def init(context: ProcessorContext): Unit = ctx = context
//
//        override def transform(value: StockMessage): StockMessage = value
//
//        override def close(): Unit = ()
//    })

    .foreach {
      case (key, stockMessage) =>
        println(stockMessage)
    }

  val topology = builder.build()

  // Output a visual representation of the Topology
  println(topology.describe())

  val streams: KafkaStreams = new KafkaStreams(topology, props)

  streams.start()
}
