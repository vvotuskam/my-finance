package my.finance.authservice.core.data.otp

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OtpCodeRepository : JpaRepository<OtpCode, UUID> {
}