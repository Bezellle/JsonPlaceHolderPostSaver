package TestUtils

import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.{EitherValues, GivenWhenThen}

abstract class UnitSpec extends AnyFlatSpec with MockFactory with GivenWhenThen with EitherValues {

}
