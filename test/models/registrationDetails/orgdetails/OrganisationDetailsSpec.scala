/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package models.registrationDetails.orgdetails

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsObject, Json}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.CorrespondenceAddress
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.orgdetails.OrganisationDetails

class OrganisationDetailsSpec extends AnyWordSpec with Matchers {

  private val model = OrganisationDetails(
    zRefNumber = Some("Z1234"),
    tradingName = Some("Acme Ltd"),
    fcaNumber = Some("FCA123456"),
    correspondenceAddress = Some(
      CorrespondenceAddress(Some("1 Test Street"), Some("Test Town"), Some("Testshire"), Some("AB1 2CD"))
    ),
    orgTelephoneNumber = Some("01234567890")
  )

  private val json: JsObject = Json.obj(
    "zRefNumber"             -> "Z1234",
    "tradingName"            -> "Acme Ltd",
    "fcaNumber"              -> "FCA123456",
    "correspondenceAddress"  -> Json.obj(
      "addressLine1" -> "1 Test Street",
      "addressLine2" -> "Test Town",
      "addressLine3" -> "Testshire",
      "postCode"     -> "AB1 2CD"
    ),
    "orgTelephoneNumber"     -> "01234567890"
  )

  "OrganisationDetails" should {

    "serialise to JSON" in {
      Json.toJson(model) mustBe json
    }

    "deserialise from JSON" in {
      json.as[OrganisationDetails] mustBe model
    }

    "deserialise from JSON with all fields missing to the default values" in {
      Json.obj().as[OrganisationDetails] mustBe OrganisationDetails()
    }
  }
}
