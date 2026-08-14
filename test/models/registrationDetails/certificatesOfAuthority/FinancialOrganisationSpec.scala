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

package models.registrationDetails.certificatesofauthority

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsError, JsString, Json}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.certificatesofauthority.FinancialOrganisation

class FinancialOrganisationSpec extends AnyWordSpec with Matchers {

  "FinancialOrganisation" should {

    "round-trip every value through JSON" in {
      FinancialOrganisation.values.foreach { value =>
        Json.toJson[FinancialOrganisation](value).as[FinancialOrganisation] mustBe value
      }
    }

    "serialise each value to its name" in {
      FinancialOrganisation.values.foreach { value =>
        Json.toJson[FinancialOrganisation](value) mustBe JsString(value.toString)
      }
    }

    "fail to deserialise an invalid value" in {
      JsString("invalid").validate[FinancialOrganisation] mustBe a[JsError]
    }
  }
}
