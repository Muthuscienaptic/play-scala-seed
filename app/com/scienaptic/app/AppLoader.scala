package com.scienaptic.app

import com.softwaremill.macwire._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.{
  Application,
  ApplicationLoader,
  BuiltInComponentsFromContext,
  LoggerConfigurator
}
import router.Routes

/**
  * Application loader that wires up the application dependencies using Macwire
  */
class AppLoader extends ApplicationLoader {

  private class AppComponents(context: Context)
      extends BuiltInComponentsFromContext(context)
      with AppModule
      with play.filters.HttpFiltersComponents {

    // Configuring logging.
    // https://www.playframework.com/documentation/2.6.x/ScalaCompileTimeDependencyInjection#Configuring-Logging
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
    }

    // https://www.playframework.com/documentation/2.6.x/ScalaCompileTimeDependencyInjection#Providing-a-router
    lazy val router: Router = {
      // add the prefix string in local scope for the Routes constructor
      lazy val prefix: String = "/"
      wire[Routes]
    }
  }

  def load(context: ApplicationLoader.Context): Application =
    new AppComponents(context).application
}
