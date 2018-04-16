package com.scienaptic.app.services

class ProductService {

  def greetingMessage(language: String): String = language match {
    case "it" => "Messi"
    case _    => "Hello"
  }

}
