package my.finance.authservice.core.data.otp

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OtpCodeRepository : JpaRepository<OtpCode, UUID> {

    @Modifying
    @Query("update OtpCode set status = 'REVOKED' where email = :email")
    fun revokeOtpByEmail(email: String)

    fun findByCodeAndEmail(code: String, email: String): OtpCode?
}