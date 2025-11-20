<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --primary: #1e3a8a;
            --primary-light: #2563eb;
            --accent: #ff8c00;
            --success: #10b981;
            --error: #ef4444;
            --bg-light: #f3f4f6;
            --white: #ffffff;
            --text-dark: #1f2937;
            --text-secondary: #6b7280;
            --border: #e5e7eb;
        }

        body {
            font-family: 'Inter', 'Segoe UI', sans-serif;
            background-color: var(--bg-light);
            color: var(--text-dark);
            line-height: 1.5;
        }

        /* Header */
        .header {
            background-color: var(--primary);
            height: 64px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 24px;
            position: sticky;
            top: 0;
            z-index: 100;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .header-brand {
            font-size: 28px;
            font-weight: 700;
            color: var(--white);
            text-decoration: none;
            letter-spacing: 1px;
            transition: opacity 0.3s;
        }

        .header-brand:hover {
            opacity: 0.85;
            color: var(--white);
        }

        .header-nav {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .header-nav a,
        .header-nav button {
            color: var(--white);
            font-weight: 500;
            text-decoration: none;
            cursor: pointer;
            transition: opacity 0.3s;
            background: none;
            border: none;
            font-size: 16px;
            padding: 8px 16px;
            border-radius: 4px;
        }

        .header-nav a:hover,
        .header-nav button:hover {
            opacity: 0.8;
            background-color: rgba(255, 255, 255, 0.1);
        }

        /* Main Container */
        .main-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 32px 24px;
        }

        .profile-card {
            background-color: var(--white);
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 32px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            margin-bottom: 24px;
        }

        .profile-header {
            display: flex;
            align-items: center;
            gap: 24px;
            margin-bottom: 32px;
            padding-bottom: 24px;
            border-bottom: 1px solid var(--border);
        }

        .profile-avatar {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            background-color: var(--primary);
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--white);
            font-size: 48px;
            font-weight: 700;
        }

        .profile-info h1 {
            font-size: 32px;
            font-weight: 700;
            color: var(--text-dark);
            margin-bottom: 8px;
        }

        .profile-info .username {
            font-size: 18px;
            color: var(--text-secondary);
            margin-bottom: 4px;
        }

        .profile-info .email {
            font-size: 16px;
            color: var(--text-secondary);
        }

        .profile-section {
            margin-bottom: 24px;
        }

        .profile-section h2 {
            font-size: 20px;
            font-weight: 600;
            color: var(--primary);
            margin-bottom: 16px;
        }

        .info-row {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px solid var(--border);
        }

        .info-row:last-child {
            border-bottom: none;
        }

        .info-label {
            font-weight: 600;
            color: var(--text-dark);
        }

        .info-value {
            color: var(--text-secondary);
        }

        .edit-form {
            margin-top: 24px;
            padding-top: 24px;
            border-top: 1px solid var(--border);
        }

        .form-group {
            margin-bottom: 16px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: var(--text-dark);
        }

        .form-group input {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid var(--border);
            border-radius: 6px;
            font-size: 16px;
            font-family: inherit;
        }

        .form-group input:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(30, 58, 138, 0.1);
        }

        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
        }

        .btn-primary {
            background-color: var(--accent);
            color: var(--white);
        }

        .btn-primary:hover {
            background-color: #e67e00;
        }

        .btn-secondary {
            background-color: var(--bg-light);
            color: var(--text-dark);
            border: 1px solid var(--border);
        }

        .btn-secondary:hover {
            background-color: var(--border);
        }

        .button-group {
            display: flex;
            gap: 12px;
            margin-top: 24px;
        }

        .message {
            padding: 12px 16px;
            margin-bottom: 20px;
            border-radius: 6px;
        }

        .message.success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .message.error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 16px;
            margin-top: 24px;
        }

        .stat-card {
            background-color: var(--bg-light);
            padding: 20px;
            border-radius: 8px;
            text-align: center;
        }

        .stat-value {
            font-size: 32px;
            font-weight: 700;
            color: var(--primary);
            margin-bottom: 8px;
        }

        .stat-label {
            font-size: 14px;
            color: var(--text-secondary);
            text-transform: uppercase;
        }
    </style>
</head>
<body>
<!-- Header -->
<header class="header">
    <a href=".." class="header-brand">StudBet</a>
    <div class="header-left">
        <nav class="header-nav">
            <a href="leaderboards">Рейтинги</a>
            <a href="rules">Правила</a>
            <a href="faq">FAQ</a>
        </nav>
    </div>
    <nav class="header-nav">
        <#if user??>
            <a href="?id=${user.id}" class="user-avatar">
                <span>${user.username}</span>
            </a>
            <a href="logout">Выход</a>
        <#else>
            <a href="sign_up">Регистрация</a>
            <a href="login">Войти</a>
        </#if>
    </nav>
</header>

<!-- Main Content -->
<main class="main-container">
    <#if profileUser??>
        <div class="profile-card">
            <#if message??>
                <div class="message <#if messageType?? && messageType == 'success'>success<#else>error</#if>">
                    ${message}
                </div>
            </#if>

            <div class="profile-header">
                <div class="profile-avatar">
                    ${profileUser.username?substring(0, 1)?upper_case}
                </div>
                <div class="profile-info">
                    <h1>
                        <#if profileUser.firstname?? && profileUser.firstname != "">
                            ${profileUser.firstname}
                        </#if>
                        <#if profileUser.lastname?? && profileUser.lastname != "">
                            ${profileUser.lastname}
                        </#if>
                        <#if (!profileUser.firstname?? || profileUser.firstname == "") && (!profileUser.lastname?? || profileUser.lastname == "")>
                            Пользователь
                        </#if>
                    </h1>
                    <div class="username">@${profileUser.username}</div>
                </div>
            </div>

            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-value">${profileUser.ratingPoints}</div>
                    <div class="stat-label">Рейтинг</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value">${profileUser.balance}</div>
                    <div class="stat-label">Баланс</div>
                </div>
            </div>

            <div class="profile-section">
                <h2>Информация</h2>
                <div class="info-row">
                    <span class="info-label">Имя пользователя:</span>
                    <span class="info-value">${profileUser.username}</span>
                </div>
                <#if profileUser.firstname?? && profileUser.firstname != "">
                    <div class="info-row">
                        <span class="info-label">Имя:</span>
                        <span class="info-value">${profileUser.firstname}</span>
                    </div>
                </#if>
                <#if profileUser.lastname?? && profileUser.lastname != "">
                    <div class="info-row">
                        <span class="info-label">Фамилия:</span>
                        <span class="info-value">${profileUser.lastname}</span>
                    </div>
                </#if>
                <div class="info-row">
                    <span class="info-label">Рейтинг:</span>
                    <span class="info-value">${profileUser.ratingPoints} очков</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Баланс:</span>
                    <span class="info-value">${profileUser.balance} StudCoins</span>
                </div>
                <#if profileUser.registrationDate??>
                    <div class="info-row">
                        <span class="info-label">Дата регистрации:</span>
                        <span class="info-value">${profileUser.registrationDate?substring(0, 10)}</span>
                    </div>
                </#if>
                <#if profileUser.lastLoginDate??>
                    <div class="info-row">
                        <span class="info-label">Последний вход:</span>
                        <span class="info-value">${profileUser.lastLoginDate?substring(0, 10)}</span>
                    </div>
                </#if>
            </div>

            <#if isOwnProfile?? && isOwnProfile>
                <div class="edit-form">
                    <h2>Редактировать профиль</h2>
                    <form method="POST" action="">
                        <input type="hidden" name="userId" value="${profileUser.id}">
                        <div class="form-group">
                            <label for="firstname">Имя</label>
                            <input type="text" id="firstname" name="firstname" value="${profileUser.firstname!""}" maxlength="100">
                        </div>
                        <div class="form-group">
                            <label for="lastname">Фамилия</label>
                            <input type="text" id="lastname" name="lastname" value="${profileUser.lastname!""}" maxlength="100">
                        </div>
                        <div class="button-group">
                            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                            <a href="profile/transactions" class="btn btn-secondary" style="text-decoration: none; display: inline-flex; align-items: center; justify-content: center;">Транзакции</a>
                        </div>
                    </form>
                </div>
            </#if>
        </div>
    <#else>
        <div class="profile-card">
            <h1>Пользователь не найден</h1>
            <p>Запрошенный профиль не существует.</p>
        </div>
    </#if>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

