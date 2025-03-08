package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.application.MembershipCreditsReadModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryMembershipCreditsReadModel implements MembershipCreditsReadModel {
    private final Map<String, Integer> membershipCreditsMap = new HashMap<>();

    @Override
    public int getCredits(String membershipId) {
        return this.membershipCreditsMap.getOrDefault(membershipId, 0);
    }

    public void addCredits(String membershipId, int credits) {
        this.membershipCreditsMap.put(membershipId, getCredits(membershipId) + credits);
    }

    // Clear for testing purposes.
    public void clear() {
        this.membershipCreditsMap.clear();
    }

    // Getter for testing purposes.
    public Map<String, Integer> getProjection() {
        return membershipCreditsMap;
    }
}
