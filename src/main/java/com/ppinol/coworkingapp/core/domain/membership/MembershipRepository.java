package com.ppinol.coworkingapp.core.domain.membership;

public interface MembershipRepository {
    ResponseGetInformationFromMembershipDTO getInformation(RequestGetInformationFromMembershipDTO request);
}
