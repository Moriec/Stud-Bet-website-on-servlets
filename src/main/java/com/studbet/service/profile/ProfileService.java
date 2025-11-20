package com.studbet.service.profile;

import com.studbet.dto.ProfileUser;

public interface ProfileService {
    ProfileUser findById(int userId);
    void updateName(ProfileUser profileUser);
}
