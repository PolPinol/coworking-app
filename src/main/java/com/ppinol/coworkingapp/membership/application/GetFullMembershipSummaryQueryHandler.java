package com.ppinol.coworkingapp.membership.application;

import org.springframework.stereotype.Service;

@Service
public class GetFullMembershipSummaryQueryHandler {

    private final UserMembershipReadModel userMembershipReadModel;
    private final MembershipCreditsReadModel membershipCreditsReadModel;

    public GetFullMembershipSummaryQueryHandler(UserMembershipReadModel userMembershipReadModel,
                                                MembershipCreditsReadModel membershipCreditsReadModel) {
        this.userMembershipReadModel = userMembershipReadModel;
        this.membershipCreditsReadModel = membershipCreditsReadModel;
    }

    public MembershipSummary handle(GetFullMembershipSummaryQuery query) {
        String membershipId = this.userMembershipReadModel.getMembershipId(query.userId());
        if (membershipId.isEmpty()) {
            throw new MembershipNotFoundException("MembershipId not found with userId: " + query.userId());
        }

        int credits = this.membershipCreditsReadModel.getCredits(membershipId);

        return new MembershipSummary(membershipId, credits);
    }
}
