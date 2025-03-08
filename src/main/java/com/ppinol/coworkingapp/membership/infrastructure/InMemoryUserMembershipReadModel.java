package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.application.UserMembershipReadModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserMembershipReadModel implements UserMembershipReadModel {
    private final Map<String, String> userMembershipMap = new HashMap<>();

    @Override
    public boolean exists(String userId) {
        return this.userMembershipMap.containsKey(userId);
    }

    @Override
    public String getMembershipId(String userId) {
        return this.userMembershipMap.getOrDefault(userId, "");
    }

    public void saveMembership(String userId, String membershipId) {
        this.userMembershipMap.put(userId, membershipId);
    }

    // Clear for testing purposes.
    public void clear() {
        this.userMembershipMap.clear();
    }

    // Getter for testing purposes.
    public Map<String, String> getProjection() {
        return this.userMembershipMap;
    }
}
