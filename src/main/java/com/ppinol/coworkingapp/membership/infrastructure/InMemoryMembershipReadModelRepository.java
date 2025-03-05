package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.application.MembershipReadModelRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryMembershipReadModelRepository implements MembershipReadModelRepository {
    private final Map<String, String> userMembershipMap = new HashMap<>();

    @Override
    public boolean exists(String userId) {
        return this.userMembershipMap.containsKey(userId);
    }

    @Override
    public void saveMembership(String userId, String membershipId) {
        this.userMembershipMap.put(userId, membershipId);
    }

    @Override
    public void clear() {
        this.userMembershipMap.clear();
    }
}
