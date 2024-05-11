import org.typelevel.scalacoptions.ScalacOptions

tpolecatExcludeOptions += ScalacOptions.warnUnusedImports

Test / tpolecatExcludeOptions += ScalacOptions.warnValueDiscard
Test / tpolecatExcludeOptions += ScalacOptions.warnNonUnitStatement
