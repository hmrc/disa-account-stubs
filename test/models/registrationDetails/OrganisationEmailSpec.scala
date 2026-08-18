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

package models.registrationDetails

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsObject, Json}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.OrganisationEmail

class OrganisationEmailSpec extends AnyWordSpec with Matchers {

  private val model = OrganisationEmail(organisationEmail = Some("test@example.com"), verified = Some(true))

  private val json: JsObject = Json.obj(
    "organisationEmail" -> "test@example.com",
    "verified"          -> true
  )

  "OrganisationEmail" should {

    "serialise to JSON" in {
      Json.toJson(model) mustBe json
    }

    "deserialise from JSON" in {
      json.as[OrganisationEmail] mustBe model
    }

    "deserialise from JSON with all fields missing to the default values" in {
      Json.obj().as[OrganisationEmail] mustBe OrganisationEmail()
    }
  }
}
