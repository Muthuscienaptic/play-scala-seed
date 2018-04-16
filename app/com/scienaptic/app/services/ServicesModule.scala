package com.scienaptic.app.services

trait ServicesModule {

  import com.softwaremill.macwire._

  lazy val productService: ProductService = wire[ProductService]

}
