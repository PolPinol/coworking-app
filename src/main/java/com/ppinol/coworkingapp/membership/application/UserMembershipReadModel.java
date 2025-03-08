package com.ppinol.coworkingapp.membership.application;

public interface UserMembershipReadModel {
    boolean exists(String userId);
    String getMembershipId(String userId);
}
