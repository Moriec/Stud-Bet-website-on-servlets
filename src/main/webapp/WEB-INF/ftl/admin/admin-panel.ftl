<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Административная панель</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            min-height: 100vh;
            padding: 40px 20px;
        }

        .container {
            max-width: 1000px;
            margin: 0 auto;
        }

        .header {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            margin-bottom: 30px;
            text-align: center;
        }

        .header h1 {
            color: #333;
            margin-bottom: 10px;
        }

        .header p {
            color: #666;
            font-size: 14px;
        }

        .admin-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .admin-card {
            background-color: white;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border-left: 5px solid #667eea;
        }

        .admin-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 12px rgba(0,0,0,0.2);
        }

        .admin-card h2 {
            color: #333;
            font-size: 18px;
            margin-bottom: 10px;
        }

        .admin-card p {
            color: #666;
            font-size: 14px;
            margin-bottom: 15px;
            min-height: 40px;
        }

        .admin-card a {
            display: inline-block;
            background-color: #667eea;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            font-weight: bold;
        }

        .admin-card a:hover {
            background-color: #764ba2;
        }

        .admin-card.create-subject {
            border-left-color: #4CAF50;
        }

        .admin-card.create-subject a {
            background-color: #4CAF50;
        }

        .admin-card.create-subject a:hover {
            background-color: #45a049;
        }

        .admin-card.create-user {
            border-left-color: #2196F3;
        }

        .admin-card.create-user a {
            background-color: #2196F3;
        }

        .admin-card.create-user a:hover {
            background-color: #0b7dda;
        }

        .admin-card.create-achievement {
            border-left-color: #FF9800;
        }

        .admin-card.create-achievement a {
            background-color: #FF9800;
        }

        .admin-card.create-achievement a:hover {
            background-color: #e68900;
        }

        .admin-card.create-event {
            border-left-color: #f44336;
        }

        .admin-card.create-event a {
            background-color: #f44336;
        }

        .admin-card.create-event a:hover {
            background-color: #da190b;
        }

        .admin-card.create-result {
            border-left-color: #9C27B0;
        }

        .admin-card.create-result a {
            background-color: #9C27B0;
        }

        .admin-card.create-result a:hover {
            background-color: #7b1fa2;
        }

        .back-button {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            background-color: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #764ba2;
        }

        .icon {
            font-size: 30px;
            margin-bottom: 10px;
            display: block;
        }
    </style>

</head>
<body>
<div class="container">
    <a href="." class="back-button">← Вернуться на главную</a>

    <div class="header">
        <h1>📊 Административная панель</h1>
        <p>Управление системой StudBet</p>
    </div>

    <div class="admin-grid">
        <!-- Создание предмета -->
        <div class="admin-card create-subject">
            <span class="icon">📚</span>
            <h2>Добавить предмет</h2>
            <p>Создание нового учебного предмета в системе.</p>
            <a href="admin/create-subject">Перейти →</a>
        </div>

        <!-- Создание пользователя -->
        <div class="admin-card create-user">
            <span class="icon">👤</span>
            <h2>Добавить пользователя</h2>
            <p>Регистрация нового пользователя или администратора.</p>
            <a href="admin/create-user">Перейти →</a>
        </div>

        <!-- Создание достижения -->
        <div class="admin-card create-achievement">
            <span class="icon">🏆</span>
            <h2>Добавить достижение</h2>
            <p>Создание новых достижений и наград для пользователей.</p>
            <a href="admin/create-achievement">Перейти →</a>
        </div>

        <!-- Создание события ставок -->
        <div class="admin-card create-event">
            <span class="icon">🎰</span>
            <h2>Создать событие ставок</h2>
            <p>Запуск нового периода ставок на предмет.</p>
            <a href="admin/create-betting-event">Перейти →</a>
        </div>

        <!-- Добавление результата студента -->
        <div class="admin-card create-result">
            <span class="icon">📝</span>
            <h2>Добавить результат студента</h2>
            <p>Внесение оценок и результатов студентов.</p>
            <a href="admin/create-student-result">Перейти →</a>
        </div>
    </div>
</div>
</body>
</html>
