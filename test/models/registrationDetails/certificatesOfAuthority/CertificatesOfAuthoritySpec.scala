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
import play.api.libs.json.{JsObject, Json}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.certificatesofauthority.{
  CertificatesOfAuthority,
  CertificatesOfAuthorityYesNo,
  FcaArticles,
  FinancialOrganisation
}

class CertificatesOfAuthoritySpec extends AnyWordSpec with Matchers {

  private val model = CertificatesOfAuthority(
    certificatesYesNo = Some(CertificatesOfAuthorityYesNo.Yes),
    fcaArticles = Some(Seq(FcaArticles.Article14, FcaArticles.Article21)),
    financialOrganisation = Some(Seq(FinancialOrganisation.Bank))
  )

  private val json: JsObject = Json.obj(
    "certificatesYesNo"      -> "yes",
    "fcaArticles"            -> Seq("article14", "article21"),
    "financialOrganisation"  -> Seq("bank")
  )

  "CertificatesOfAuthority" should {

    "serialise to JSON" in {
      Json.toJson(model) mustBe json
    }

    "deserialise from JSON" in {
      json.as[CertificatesOfAuthority] mustBe model
    }

    "deserialise from JSON with all fields missing" in {
      Json.obj().as[CertificatesOfAuthority] mustBe CertificatesOfAuthority(None, None, None)
    }
  }
}
