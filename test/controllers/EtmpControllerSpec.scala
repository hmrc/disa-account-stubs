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

package controllers

import play.api.test.FakeRequest
import play.api.test.Helpers.*
import uk.gov.hmrc.disaaccountstubs.controllers.routes.EtmpController
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.RegistrationDetails
import utils.BaseUnitSpec

class EtmpControllerSpec extends BaseUnitSpec {

  "EtmpController.retrieveRegistrationDetails" should {

    "return 200 with a RegistrationDetails payload containing the requested zref" in {
      val zref    = "Z1234"
      val request = FakeRequest(GET, EtmpController.retrieveRegistrationDetails(zref).url)

      val result = route(app, request).get

      status(result) mustBe OK
      contentType(result) mustBe Some("application/json")

      val journeyData = contentAsJson(result).as[RegistrationDetails]
      journeyData.organisationDetails.flatMap(_.zRefNumber) mustBe Some(zref)
      journeyData.signatories.flatMap(_.signatories.headOption).flatMap(_.email) mustBe Some("signatory@example.com")
    }

    "return 404 when the zref is not found" in {
      val request = FakeRequest(GET, EtmpController.retrieveRegistrationDetails("Z0404").url)

      val result = route(app, request).get

      status(result) mustBe NOT_FOUND
    }

    "flag the ISA products as under review for the under-review test zref" in {
      val request = FakeRequest(GET, EtmpController.retrieveRegistrationDetails("Z0202").url)

      val journeyData = contentAsJson(route(app, request).get).as[RegistrationDetails]

      journeyData.isaProducts.map(_.underReview) mustBe Some(true)
    }

    "not flag the ISA products as under review for any other zref" in {
      val request = FakeRequest(GET, EtmpController.retrieveRegistrationDetails("Z1234").url)

      val journeyData = contentAsJson(route(app, request).get).as[RegistrationDetails]

      journeyData.isaProducts.map(_.underReview) mustBe Some(false)
    }
  }
}
