package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.membership.*;
import org.springframework.stereotype.Repository;

@Repository
public class HTTPMembershipRepository implements MembershipRepository {

    @Override
    public ResponseGetInformationFromMembershipDTO getInformation(RequestGetInformationFromMembershipDTO request) {
        return new ResponseGetInformationFromMembershipDTO("1234", 1234);
    }
}
