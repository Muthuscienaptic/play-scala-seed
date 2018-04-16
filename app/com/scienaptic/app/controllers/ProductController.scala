package com.scienaptic.app.controllers

import com.scienaptic.app.AppController
import com.scienaptic.app.models.Product
import com.scienaptic.app.services.ProductService
import io.circe.generic.auto._
import io.circe.syntax.EncoderOps
import play.api.mvc._

class ProductController(greetingService: ProductService,
                        cc: ControllerComponents)
    extends AppController(cc) {

  val greetingsList = Seq(
    Product(1, greetingService.greetingMessage("en"), null),
    Product(2, greetingService.greetingMessage("it"), "S")
  )

  def index = Action { implicit request =>
    logger.debug(s"A ${request.method} request was received at ${request.uri}")
    response(greetingsList.asJson)
  }

}
