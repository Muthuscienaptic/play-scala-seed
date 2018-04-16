package com.scienaptic.app

import com.scienaptic.app.controllers.ProductController
import com.scienaptic.app.services.ServicesModule
import play.api.mvc.ControllerComponents

trait AppModule extends ServicesModule {

  import com.softwaremill.macwire._

  lazy val productController: ProductController = wire[ProductController]

  /**
    * Controller components dependencies that most controllers rely on.
    *
    * <strong>Dependent scope</strong>.
    * Create new instance of the dependency for each usage.
    */
  def controllerComponents: ControllerComponents
}
