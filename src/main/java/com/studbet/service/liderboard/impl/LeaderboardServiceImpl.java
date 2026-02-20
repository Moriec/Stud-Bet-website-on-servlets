package com.studbet.service.liderboard.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;
import com.studbet.service.liderboard.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {
    private final UserDao userDao;

    public List<Map<String, Object>> getTop10Users() {
        List<User> topUsersList = userDao.findTop10ByRating();

        List<Map<String, Object>> topUsers = new ArrayList<>();
        for (int i = 0; i < topUsersList.size(); i++) {
            User user = topUsersList.get(i);
            Map<String, Object> userData = new HashMap<>();
            userData.put("rank", i + 1);
            userData.put("user", user);
            userData.put("username", user.getUsername());
            userData.put("ratingPoints", user.getRatingPoints());
            userData.put("balance", user.getBalance());
            topUsers.add(userData);
        }

        return topUsers;
    }

    public Map<String, Object> getUserRankInfo(int userId) {
        List<User> allUsers = userDao.findAll();
        User currentUser = userDao.findById(userId).orElse(null);

        if (currentUser == null) {
            return new HashMap<>();
        }

        allUsers.sort((u1, u2) -> Integer.compare(u2.getRatingPoints(), u1.getRatingPoints()));

        int rank = 0;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getId() == userId) {
                rank = i + 1;
                break;
            }
        }

        Map<String, Object> rankInfo = new HashMap<>();
        rankInfo.put("rank", rank);
        rankInfo.put("ratingPoints", currentUser.getRatingPoints());
        rankInfo.put("totalUsers", allUsers.size());

        int currentIndex = rank - 1;
        if (currentIndex > 0) {
            User userAbove = allUsers.get(currentIndex - 1);
            rankInfo.put("pointsToNextRank", userAbove.getRatingPoints() - currentUser.getRatingPoints());
        } else {
            rankInfo.put("pointsToNextRank", 0);
        }

        return rankInfo;
    }
}