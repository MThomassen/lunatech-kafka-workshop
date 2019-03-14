package com.lunatech.kafka.streams

import java.util

import com.lunatech.kafka.common.scala.StockMessage
import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}

/**
  * Boilerplate Serdes for custom Message types
  */
class StockMessageSerdes extends Serde[StockMessage] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()

  override def deserializer(): Deserializer[StockMessage] = {
    new Deserializer[StockMessage] {
      override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()

      override def deserialize(topic: String, data: Array[Byte]): StockMessage = StockMessage.deserialize(data)

      override def close(): Unit = ()
    }
  }

  override def serializer(): Serializer[StockMessage] = {
    new Serializer[StockMessage] {
      override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()

      override def serialize(topic: String, data: StockMessage): Array[Byte] = StockMessage.serialize(data)

      override def close(): Unit = ()
    }
  }

  override def close(): Unit = ()
}
