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

package models.registrationDetails.isaProducts

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsObject, Json}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.isaProducts.{InnovativeFinancialProduct, IsaProduct, IsaProducts}

class IsaProductsSpec extends AnyWordSpec with Matchers {

  private val model = IsaProducts(
    isaProducts = Some(Seq(IsaProduct.CashIsas, IsaProduct.InnovativeFinanceIsas)),
    innovativeFinancialProducts = Some(Seq(InnovativeFinancialProduct.CrowdFundedDebentures)),
    p2pPlatform = Some("Test Platform"),
    p2pPlatformNumber = Some("PN12345")
  )

  private val json: JsObject = Json.obj(
    "isaProducts"                 -> Seq("cashIsas", "innovativeFinanceIsas"),
    "innovativeFinancialProducts" -> Seq("crowdfundedDebentures"),
    "p2pPlatform"                 -> "Test Platform",
    "p2pPlatformNumber"           -> "PN12345"
  )

  "IsaProducts" should {

    "serialise to JSON" in {
      Json.toJson(model) mustBe json
    }

    "deserialise from JSON" in {
      json.as[IsaProducts] mustBe model
    }

    "deserialise from JSON with all fields missing" in {
      Json.obj().as[IsaProducts] mustBe IsaProducts(None, None, None, None)
    }
  }
}
