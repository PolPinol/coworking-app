package com.ppinol.coworkingapp.core.domain.membership;

public record ResponsePaymentMembershipDTO(MembershipId membershipId, Credits remainingCredits) {
    public ResponsePaymentMembershipDTO {
        if (membershipId == null) {
            throw new InvalidContractWithPaymentRequestException("MembershipId cannot be null");
        }
        if (remainingCredits == null) {
            throw new InvalidContractWithPaymentRequestException("RemainingCredits cannot be null");
        }
    }
}
