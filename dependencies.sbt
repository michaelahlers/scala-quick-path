ThisBuild / scalaVersion := "2.13.14"

/**
 * Parsley is a fast and modern parser combinator library for Scala.
 * @see [[https://github.com/j-mie6/parsley]]
 */
libraryDependencies +=
  "com.github.j-mie6" %% "parsley" % "4.5.2"

/**
 * ScalaTest is the most flexible and most popular testing tool in the Scala ecosystem.
 * @see [[https://www.scalatest.org/]]
 */
libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.2.18" % Test

/**
 * Readable deltas for Scala case classes.
 * @see [[https://github.com/softwaremill/diffx]]
 */
libraryDependencies ++=
  "com.softwaremill.diffx"   %% "diffx-scalatest-must"   % "0.9.0" % Test ::
    "com.softwaremill.diffx" %% "diffx-scalatest-should" % "0.9.0" % Test ::
    Nil

/**
 * Property-based testing for Scala, with support for ScalaTest.
 * Also includes support for automatic derivation of ScalaCheck instances.
 *
 * @see [[https://scalacheck.org]]
 * @see [[https://scalatest.org/plus/scalacheck]]
 * @see [[https://github.com/spotify/magnolify]]
 */
libraryDependencies ++=
  "org.scalacheck"      %% "scalacheck"           % "1.17.0"   % Test ::
    "org.scalatestplus" %% "scalacheck-1-17"      % "3.2.18.0" % Test ::
    "com.spotify"       %% "magnolify-scalacheck" % "0.7.2"    % Test ::
    Nil
