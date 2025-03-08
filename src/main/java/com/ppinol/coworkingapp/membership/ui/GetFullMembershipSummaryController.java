package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.application.GetFullMembershipSummaryQuery;
import com.ppinol.coworkingapp.membership.application.GetFullMembershipSummaryQueryHandler;
import com.ppinol.coworkingapp.membership.application.MembershipSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberships")
public class GetFullMembershipSummaryController {

    private final GetFullMembershipSummaryQueryHandler queryHandler;

    public GetFullMembershipSummaryController(GetFullMembershipSummaryQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping({"/{userId}", "/"})
    public ResponseEntity<GetFullMembershipSummaryResponse> handle(@PathVariable(value = "userId", required = false) String userId) {
        if (userId == null) {
            throw new InvalidGetFullMembershipInputException("userId should not be null");
        }

        if (userId.trim().isEmpty()) {
            throw new InvalidGetFullMembershipInputException("userId should not be empty");
        }

        MembershipSummary summary =
                this.queryHandler.handle(new GetFullMembershipSummaryQuery(userId));

        return ResponseEntity.ok(
                new GetFullMembershipSummaryResponse(summary.membershipId(), userId, summary.credits()));
    }
}
