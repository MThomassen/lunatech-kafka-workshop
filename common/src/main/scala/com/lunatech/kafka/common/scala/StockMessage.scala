package com.lunatech.kafka.common.scala

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.math.BigDecimal.RoundingMode

case class StockMessage private (symbol: String, value: BigDecimal)

object StockMessage {
  private val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def apply(symbol: String, value: BigDecimal): StockMessage =
    new StockMessage(symbol, value.setScale(4, RoundingMode.HALF_EVEN))

  def serialize(m: StockMessage): Array[Byte] = mapper.writeValueAsBytes(m)

  def deserialize(m: Array[Byte]): StockMessage =
    mapper.readValue(m, classOf[StockMessage])
}
