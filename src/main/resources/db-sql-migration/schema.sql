-- Схема базы данных для генерации jOOQ (только структура)

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    balance INT DEFAULT 1000,
    rating_points INT DEFAULT 0,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

CREATE TABLE subjects (
    subject_id SERIAL PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL,
    subject_code VARCHAR(20) UNIQUE NOT NULL,
    description TEXT,
    max_score INT DEFAULT 100
);

CREATE TABLE betting_events (
    event_id SERIAL PRIMARY KEY,
    target_user_id INT REFERENCES users(user_id),
    subject_id INT REFERENCES subjects(subject_id),
    semester INT NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    event_type VARCHAR(50), -- EXAM, ASSIGNMENT, etc.
    status VARCHAR(20) DEFAULT 'OPEN', -- OPEN, CLOSED, RESOLVED, CANCELLED
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    close_it TIMESTAMP
);

CREATE TABLE bets (
    bet_id SERIAL PRIMARY KEY,
    bettor_id INT REFERENCES users(user_id),
    event_id INT REFERENCES betting_events(event_id),
    bet_amount INT NOT NULL CHECK (bet_amount > 0),
    predicted_score_min INT NOT NULL,
    predicted_score_max INT NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, WON, LOST, CANCELLED
    payout_amount INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resolved_at TIMESTAMP
);

CREATE TABLE student_result (
    result_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    subject_id INT REFERENCES subjects(subject_id),
    actual_score INT NOT NULL,
    semester INT NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    is_finelized BOOLEAN DEFAULT FALSE,
    UNIQUE(user_id, subject_id, semester, academic_year)
);

CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    related_bet_id INT REFERENCES bets(bet_id),
    transaction_type VARCHAR(50) NOT NULL, -- BET_PLACED, BET_WIN, DEPOSIT, WITHDRAWAL
    amount INT NOT NULL,
    balance_before INT NOT NULL,
    balance_after INT NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE achievements (
    achievements_id SERIAL PRIMARY KEY,
    achevements_name VARCHAR(100) NOT NULL,
    description TEXT,
    icon_url VARCHAR(255),
    points_reward INT DEFAULT 0,
    achievement_type VARCHAR(50),
    criteria_json TEXT -- JSON string defining conditions
);

CREATE TABLE user_achievements (
    user_id INT REFERENCES users(user_id),
    achievements_id INT REFERENCES achievements(achievements_id),
    earned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, achievements_id)
);
