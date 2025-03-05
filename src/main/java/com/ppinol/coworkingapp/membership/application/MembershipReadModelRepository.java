package com.ppinol.coworkingapp.membership.application;

public interface MembershipReadModelRepository {
    boolean exists(String userId);
    void saveMembership(String userId, String membershipId);
    void clear();
}
