package com.twitter.finatra.http.marshalling

import com.fasterxml.jackson.databind.node.ObjectNode
import com.google.inject.Injector
import com.twitter.finagle.http.{MediaType, Message}
import com.twitter.finatra.http.annotations.JsonIgnoreBody
import com.twitter.util.jackson.ScalaObjectMapper

private object DefaultMessageBodyReaderImpl {
  private val EmptyObjectNode = new ObjectNode(null)
}

private class DefaultMessageBodyReaderImpl(injector: Injector, objectMapper: ScalaObjectMapper)
    extends DefaultMessageBodyReader {

  /* Public */

  override def parse[T: Manifest](message: Message): T = {
    val objectReader =
      objectMapper.reader[T].`with`(new MessageInjectableValues(injector, objectMapper, message))

    val hasMessageBody = message.contentLength match {
      case Some(length) if length > 0 => true
      case _ => false
    }

    if (hasMessageBody && !ignoresBody[T] && isBodyJsonEncoded(message)) {
      // the body of the message should be parsed by the object reader
      MessageBodyReader.parseMessageBody[T](message, objectReader)
    } else {
      // use the object reader simply to trigger the framework
      // case class deserializer over an empty object node
      objectReader.readValue[T](DefaultMessageBodyReaderImpl.EmptyObjectNode)
    }
  }

  /* Private */

  private def ignoresBody[T: Manifest]: Boolean = {
    manifest[T].runtimeClass.isAnnotationPresent(classOf[JsonIgnoreBody])
  }

  private def isBodyJsonEncoded(message: Message): Boolean =
    message.contentType.exists(_.startsWith(MediaType.Json))
}
