package com.scienaptic.app

import play.api.Logger
import play.api.mvc.{BaseController, ControllerComponents, Result}

import scala.util.{Failure, Success, Try}

/**
  * An abstract implementation of [[play.api.mvc.BaseController]] to make it slightly easier to use
  * with all application controllers.
  *
  * @see [[play.api.mvc.AbstractController]] for reference
  * @param controllerComponents Base controller components dependencies that most controllers rely on.
  */
abstract class AppController(
    protected val controllerComponents: ControllerComponents)
    extends BaseController
    with play.api.libs.circe.Circe {

  /** Distinct logger per class using the class name. */
  // https://www.playframework.com/documentation/2.6.x/ScalaLogging#Creating-your-own-loggers
  protected val logger = Logger(getClass)

  /**
    * Successful json response
    * <strong>
    * Passing a json string directly to this function misses any errors which can occur
    * during serialisation.
    * </strong>
    *
    * @param json Function which encodes the data to [[io.circe.Json]]
    * @return [[play.api.mvc.Results.Ok]] response upon successfully serialising json,
    *         [[play.api.mvc.Results.InternalServerError]] upon error in serialising json.
    */
  protected final def response(json: => io.circe.Json): Result =
    Try(Ok(json)) match {
      case Success(result) => result
      case Failure(e) =>
        val message = "Error converting response to json."
        logger.error(message, e)
        InternalServerError(message)
    }



}
