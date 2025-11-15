package com.studbet.dao;

import com.studbet.dao.impl.*;
import com.studbet.enums.*;
import com.studbet.model.*;
import com.studbet.util.dataSource.DataSourceFabric;
import com.studbet.util.dataSource.impl.HicaryDataSorceFabric;

import javax.sql.DataSource;
import java.time.LocalDateTime;

public class TestDao {
    public static void main(String[] args) {
        DataSourceFabric dataSourceFabric = new HicaryDataSorceFabric();
        DataSource dataSource;
        try {
            dataSource = dataSourceFabric.getDataSource(
                    System.getenv("STUDBET_DB_URL"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        UserDao userDao = new UserDaoImpl(dataSource);
        UserAchievementDao userAchievementDao  = new UserAchievementDaoImpl(dataSource);
        TransactionDao transactionDao = new TransactionDaoImpl(dataSource);
        SubjectDao  subjectDao = new SubjectDaoImpl(dataSource);
        StudentResultDao  studentResultDao = new StudentResultDaoImpl(dataSource);
        BettingEventDao bettingEventDao = new BettingEventDaoImpl(dataSource);
        BetDao  betDao = new BetDaoImpl(dataSource);
        AchevementsDao achevementsDao = new AchievementDaoImpl(dataSource);

        //Начало
        User user =  new User("1", "1", "1", Role.STUDENT.name());
        user = userDao.save(user);
        System.out.println(userDao.findByUsername(user.getUsername()));

        user.setUsername("2");
        userDao.update(user);
        System.out.println(userDao.findByUsername(user.getUsername()));

        //Добавим ачивку
        Achievement achievement = new Achievement("1", "1", "1", 10, AchievementType.SOCIAL.name(), "{}");
        achievement = achevementsDao.save(achievement);
        System.out.println(achevementsDao.findById(achievement.getId()));

        //User выполнил ачивку
        UserAchievement userAchievement = new UserAchievement(user.getId(), achievement.getId(), LocalDateTime.now());
        userAchievement = userAchievementDao.save(userAchievement);
        System.out.println(userAchievementDao.findByAchievementId(achievement.getId()));

        //Предмет
        Subject subject = new  Subject("1", "1", "Предмет");
        subject = subjectDao.save(subject);
        System.out.println(subjectDao.findById(subject.getId()));

        //Результат студента
        StudentResult studentResult = new StudentResult(user.getId(), subject.getId(), 100, 3, "2025", false);
        studentResult = studentResultDao.save(studentResult);
        System.out.println(studentResultDao.findByUserId(user.getId()));
        studentResult.setFinelized(true);
        studentResult = studentResultDao.update(studentResult);
        System.out.println(studentResultDao.findByUserId(user.getId()));

        //Ставка
        BettingEvent bettingEvent = new BettingEvent(user.getId(), subject.getId(), 3, "2025", EventType.EXAM.name(), EventStatus.OPEN.name());
        bettingEvent = bettingEventDao.save(bettingEvent);
        System.out.println(bettingEventDao.findById(bettingEvent.getId()));

        //UserBet
        Bet bet = new Bet(user.getId(), bettingEvent.getId(), 200, BetStatus.PENDING.name(), 300, 50, 100);
        bet = betDao.save(bet);
        System.out.println(betDao.findById(bet.getId()));

        //транзакции
        Transaction transaction = new Transaction(user.getId(), bettingEvent.getId(), TransactionType.BET_WIN.name(), 200, 100, 1200, "Descript");
        transaction = transactionDao.save(transaction);
        System.out.println(transactionDao.findById(transaction.getId()));

        //Конец

        transactionDao.delete(transaction);
        System.out.println("Транзакция" + transactionDao.findById(transaction.getId()));

        betDao.delete(bet);
        System.out.println("bet: " + betDao.findById(bet.getId()));

        bettingEventDao.delete(bettingEvent);
        System.out.println("BettingEvent: " + bettingEventDao.findById(bettingEvent.getId()));

        studentResultDao.delete(studentResult);
        System.out.println("Student Result" + studentResultDao.findByUserId(user.getId()));

        subjectDao.delete(subject);
        System.out.println("Subject: " + subjectDao.findById(subject.getId()));

        userAchievementDao.delete(userAchievement);
        System.out.println("userAchievements: " + userAchievementDao.findByAchievementId(achievement.getId()));

        achevementsDao.delete(achievement);
        System.out.println("achievements: " + achevementsDao.findById(achievement.getId()));

        userDao.delete(user);
        System.out.println("user: " + userDao.findByUsername(user.getUsername()));

    }
}
