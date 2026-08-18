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
import play.api.libs.json.Json
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.{BusinessVerification, OrganisationEmail, RegistrationDetails}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.certificatesofauthority.{CertificatesOfAuthority, CertificatesOfAuthorityYesNo}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.isaProducts.{IsaProduct, IsaProducts}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.liaisonofficers.{LiaisonOfficer, LiaisonOfficers}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.orgdetails.OrganisationDetails
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.signatories.{Signatories, Signatory}
import uk.gov.hmrc.disaaccountstubs.models.registrationDetails.thirdparty.ThirdPartyOrganisations

import java.time.Instant

class RegistrationDetailsSpec extends AnyWordSpec with Matchers {

  private val fullModel = RegistrationDetails(
    groupId = "group-1",
    enrolmentId = "enrolment-1",
    businessVerification = Some(BusinessVerification(Some("1234567890"), Some("Acme Ltd"), None, None)),
    organisationDetails = Some(OrganisationDetails(zRefNumber = Some("Z1234"))),
    organisationEmail = Some(OrganisationEmail(Some("test@example.com"), Some(true))),
    isaProducts = Some(IsaProducts(Some(Seq(IsaProduct.CashIsas)), None, None, None)),
    certificatesOfAuthority = Some(CertificatesOfAuthority(Some(CertificatesOfAuthorityYesNo.No), None, None)),
    liaisonOfficers = Some(LiaisonOfficers(Seq(LiaisonOfficer("1", Some("Jane Smith"), None, Set.empty, None)))),
    signatories = Some(Signatories(Seq(Signatory("1", Some("John Doe"), None)))),
    thirdPartyOrganisations = Some(ThirdPartyOrganisations()),
    lastUpdated = Some(Instant.parse("2026-08-14T09:30:00Z"))
  )

  "RegistrationDetails" should {

    "round-trip a fully populated model through JSON" in {
      Json.toJson(fullModel).as[RegistrationDetails] mustBe fullModel
    }

    "round-trip a minimal model, containing only the required groupId, through JSON" in {
      val minimalModel = RegistrationDetails(groupId = "group-1", enrolmentId = "enrolment-1")

      Json.toJson(minimalModel).as[RegistrationDetails] mustBe minimalModel
    }

    "generate a random enrolmentId when one is not supplied" in {
      RegistrationDetails(groupId = "group-1").enrolmentId must not be empty
    }
  }

  "RegistrationDetails.taskListJourneyHandlers" should {

    "expose a handler for every task list journey" in {
      RegistrationDetails.taskListJourneyHandlers.keySet mustBe Set(
        "businessVerification",
        "organisationDetails",
        "organisationEmail",
        "isaProducts",
        "certificatesOfAuthority",
        "liaisonOfficers",
        "signatories",
        "thirdPartyOrganisations"
      )
    }
  }
}
