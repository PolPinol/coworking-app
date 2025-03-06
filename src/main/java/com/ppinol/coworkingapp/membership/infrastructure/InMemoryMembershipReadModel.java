package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.application.MembershipReadModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryMembershipReadModel implements MembershipReadModel {
    private final Map<String, String> userMembershipMap = new HashMap<>();

    @Override
    public boolean existsByUserId(String userId) {
        return this.userMembershipMap.containsKey(userId);
    }

    public void saveMembership(String userId, String membershipId) {
        this.userMembershipMap.put(userId, membershipId);
    }

    // Clear for testing purposes.
    public void clear() {
        this.userMembershipMap.clear();
    }

    // Getter for testing purposes.
    public Map<String, String> getStore() {
        return userMembershipMap;
    }
}
