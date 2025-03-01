package com.ppinol.coworkingapp.core.domain.membership;

public record ResponseGetInformationFromMembershipDTO(String membershipId, Integer remainingCredits) {
    public ResponseGetInformationFromMembershipDTO {
        if (membershipId == null) {
            throw new InternalErrorProcessingMembershipException("MembershipId cannot be null");
        }
        if (remainingCredits == null) {
            throw new InternalErrorProcessingMembershipException("RemainingCredits cannot be null");
        }
    }
}
