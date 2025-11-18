package com.studbet.listener;

import com.studbet.dao.*;
import com.studbet.dao.impl.*;
import com.studbet.security.password.PasswordEncrypt;
import com.studbet.security.password.impl.PasswordEncryptBCrypt;
import com.studbet.service.auth.CheckLoginService;
import com.studbet.service.auth.LoginService;
import com.studbet.service.auth.RegistrationService;
import com.studbet.service.auth.impl.CheckLoginServiceImpl;
import com.studbet.service.auth.impl.LoginServiceImpl;
import com.studbet.service.auth.impl.RegistrationServiceImpl;
import com.studbet.service.entity.SubjectService;
import com.studbet.service.entity.impl.SubjectServiceImpl;
import com.studbet.service.main.MainPageService;
import com.studbet.service.main.impl.MainPageServiceImpl;
import com.studbet.service.session.SessionService;
import com.studbet.service.session.impl.SessionServiceImpl;
import com.studbet.util.dataSource.DataSourceFabric;
import com.studbet.util.dataSource.impl.HicaryDataSorceFabric;
import com.studbet.util.validate.UserValidate;
import com.studbet.util.validate.impl.UserValidateImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //datasource
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

        //dao
        UserDao userDao = new UserDaoImpl(dataSource);
        BettingEventDao bettingEventDao = new BettingEventDaoImpl(dataSource);
        AchevementsDao achevementsDao = new AchievementDaoImpl(dataSource);
        BetDao  betDao = new BetDaoImpl(dataSource);
        StudentResultDao studentResultDao =  new StudentResultDaoImpl(dataSource);
        SubjectDao subjectDao = new SubjectDaoImpl(dataSource);
        TransactionDao transactionDao = new TransactionDaoImpl(dataSource);
        UserAchievementDao userAchievementDao = new UserAchievementDaoImpl(dataSource);

        //utils
        PasswordEncrypt passwordEncrypt = new PasswordEncryptBCrypt();
        SessionService sessionService = new SessionServiceImpl();
        UserValidate userValidate = new UserValidateImpl();
        sce.getServletContext().setAttribute("userValidate", userValidate);
        sce.getServletContext().setAttribute("sessionService", sessionService);

        //auth service
        CheckLoginService checkLoginService = new CheckLoginServiceImpl(userDao);
        LoginService loginService = new LoginServiceImpl(userDao, passwordEncrypt);
        RegistrationService registrationService = new RegistrationServiceImpl(userDao, passwordEncrypt);
        sce.getServletContext().setAttribute("registrationService", registrationService);
        sce.getServletContext().setAttribute("loginService", loginService);
        sce.getServletContext().setAttribute("checkLoginService", checkLoginService);

        //main page service
        MainPageService mainPageService = new MainPageServiceImpl(bettingEventDao);
        sce.getServletContext().setAttribute("mainPageService", mainPageService);

        //admin service
        SubjectService subjectService = new SubjectServiceImpl(subjectDao);
        sce.getServletContext().setAttribute("subjectService", subjectService);



    }
}
