package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;

public interface MeetingRoomRepository {
    void save(MeetingRoom meetingRoom);
    MeetingRoom findByName(MeetingRoomName name);
    void clear();
}
