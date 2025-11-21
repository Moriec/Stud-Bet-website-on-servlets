package com.studbet.service.liderboard;

import java.util.List;
import java.util.Map;

public interface LeaderboardService {
    List<Map<String, Object>> getTop10Users();
    Map<String, Object> getUserRankInfo(int userId);
}
