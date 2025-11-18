<#-- Главная страница StudBet -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StudBet — Ставки на учёбу</title>
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
            --warning: #fbbf24;
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

        .user-avatar {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            color: var(--white);
            text-decoration: none;
            padding: 4px 12px;
            border-radius: 6px;
            transition: background-color 0.3s;
        }

        .user-avatar:hover {
            background-color: rgba(255, 255, 255, 0.15);
            color: var(--white);
        }

        .user-avatar img {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            object-fit: cover;
        }

        /* Main Container */
        .main-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 32px 24px;
        }

        .page-title {
            font-size: 32px;
            font-weight: 700;
            color: var(--primary);
            margin-bottom: 8px;
            line-height: 1.2;
        }

        .page-subtitle {
            font-size: 16px;
            color: var(--text-secondary);
            margin-bottom: 32px;
        }

        /* Bet Cards Grid */
        .bet-cards-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 24px;
        }

        /* Bet Card */
        .bet-card {
            background-color: var(--white);
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 16px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .bet-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15);
        }

        .bet-card-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 12px;
            gap: 12px;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 8px;
            flex: 1;
        }

        .user-info img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
        }

        .user-info h4 {
            font-size: 16px;
            font-weight: 600;
            color: var(--text-dark);
            margin: 0;
        }

        .user-info small {
            font-size: 12px;
            color: var(--text-secondary);
        }

        .bet-card-odds {
            background-color: var(--accent);
            color: var(--white);
            padding: 6px 14px;
            border-radius: 4px;
            font-size: 18px;
            font-weight: 700;
            font-family: 'Courier New', monospace;
            white-space: nowrap;
        }

        .subject-info {
            margin-bottom: 12px;
        }

        .subject-info h4 {
            font-size: 18px;
            font-weight: 600;
            color: var(--primary);
            margin: 0 0 4px 0;
        }

        .subject-info small {
            font-size: 12px;
            color: var(--text-secondary);
        }

        .bet-card-body {
            margin-bottom: 12px;
        }

        .bet-card-body p {
            font-size: 14px;
            color: var(--text-dark);
            margin: 4px 0;
        }

        .bet-card-body strong {
            color: var(--text-dark);
            font-weight: 600;
        }

        .odds-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 8px 0;
            border-top: 1px solid var(--border);
            margin-top: 8px;
        }

        .odds-section .label {
            font-size: 12px;
            color: var(--text-secondary);
            text-transform: uppercase;
        }

        .bet-card-action {
            display: flex;
            gap: 8px;
            margin-top: 12px;
        }

        .btn-primary {
            flex: 1;
            background-color: var(--accent);
            color: var(--white);
            border: none;
            padding: 12px 24px;
            height: 40px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }

        .btn-primary:hover {
            background-color: #e67e00;
            transform: translateY(-1px);
        }

        .btn-primary:active {
            background-color: #cc6700;
            transform: translateY(0);
        }

        .btn-secondary {
            flex: 1;
            background-color: var(--bg-light);
            color: var(--text-dark);
            border: 1px solid #d1d5db;
            padding: 12px 24px;
            height: 40px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-secondary:hover {
            background-color: var(--border);
        }

        /* Empty State */
        .empty-state {
            text-align: center;
            padding: 64px 24px;
            color: var(--text-secondary);
        }

        .empty-state h3 {
            font-size: 24px;
            font-weight: 600;
            color: var(--text-dark);
            margin-bottom: 12px;
        }

        .empty-state p {
            font-size: 16px;
            margin-bottom: 24px;
        }

        /* Badge */
        .badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 600;
        }

        .badge-open {
            background-color: var(--success);
            color: var(--white);
        }

        .badge-closed {
            background-color: var(--text-secondary);
            color: var(--white);
        }

        /* Responsive */
        @media (max-width: 768px) {
            .header {
                padding: 0 16px;
                height: 56px;
            }

            .header-brand {
                font-size: 24px;
            }

            .header-nav a,
            .header-nav button {
                padding: 6px 12px;
                font-size: 14px;
            }

            .main-container {
                padding: 24px 16px;
            }

            .page-title {
                font-size: 28px;
            }

            .bet-cards-grid {
                grid-template-columns: 1fr;
                gap: 16px;
            }

            .user-avatar span {
                display: none;
            }
        }
    </style>
</head>
<body>
<!-- Header -->
<header class="header">
    <a href="/" class="header-brand">StudBet</a>
    <div class="header-left">

        <nav class="header-nav">
            <a href="leaderboards">Рейтинги</a>
            <a href="rules">Правила</a>
            <a href="faq">FAQ</a>
        </nav>
    </div>

    <nav class="header-nav">
        <#if user??>
            <!-- Authenticated User -->
            <a href="profile" class="user-avatar">
                <img src="${user.avatar!'/static/img/avatar-placeholder.png'}" alt="Avatar" hidden="hidden">
                <span>${user.username}</span>
            </a>
            <a href="logout">Выход</a>
        <#else>
            <!-- Guest User -->
            <a href="sign_up">Регистрация</a>
            <a href="login">Войти</a>
        </#if>
    </nav>
</header>

<!-- Main Content -->
<main class="main-container">
    <h1 class="page-title">Доступные ставки</h1>
    <p class="page-subtitle">Делай ставки на результаты экзаменов и зарабатывай StudCoins!</p>

    <#if events?? && (events?size > 0)>
        <!-- Bet Cards Grid -->
        <div class="bet-cards-grid">
            <#list events as event>
                <div class="bet-card">
                    <!-- Card Header with User Info and Odds -->
                    <div class="bet-card-header">
                        <div class="user-info">
                            <img src="${event.targetUser.avatar!'/static/img/avatar-placeholder.png'}" alt="Avatar">
                            <div>
                                <h4>${event.targetUser.firstName} ${event.targetUser.lastName}</h4>
                                <small>@${event.targetUser.username}</small>
                            </div>
                        </div>
                        <div class="bet-card-odds">${event.odds?string["0.00"]}</div>
                    </div>

                    <!-- Subject Info -->
                    <div class="subject-info">
                        <h4>${event.subject.name}</h4>
                        <small>Семестр ${event.semester} • ${event.academicYear}</small>
                    </div>

                    <!-- Event Details -->
                    <div class="bet-card-body">
                        <p><strong>Тип события:</strong> ${event.eventType}</p>
                        <p><strong>Дата:</strong> ${event.eventDate?string["dd.MM.yyyy HH:mm"]}</p>
                        <#if event.status == "OPEN">
                            <span class="badge badge-open">Открыто</span>
                        <#else>
                            <span class="badge badge-closed">Закрыто</span>
                        </#if>
                    </div>

                    <!-- Action Buttons -->
                    <#if event.status == "OPEN" && user??>
                        <div class="bet-card-action">
                            <form method="GET" action="${ctx!''}/bet/create" style="flex: 1; margin: 0;">
                                <input type="hidden" name="eventId" value="${event.eventId}">
                                <button type="submit" class="btn-primary">Сделать ставку</button>
                            </form>
                            <form method="GET" action="${ctx!''}/bet/view" style="flex: 1; margin: 0;">
                                <input type="hidden" name="eventId" value="${event.eventId}">
                                <button type="submit" class="btn-secondary">Подробнее</button>
                            </form>
                        </div>
                    <#elseif event.status == "OPEN">
                        <div class="bet-card-action">
                            <a href="login" class="btn-primary" style="text-align: center; display: block; text-decoration: none; line-height: 16px;">
                                Войти для ставки
                            </a>
                        </div>
                    </#if>
                </div>
            </#list>
        </div>
    <#else>
        <!-- Empty State -->
        <div class="empty-state">
            <h3>Нет доступных ставок</h3>
            <p>В данный момент нет открытых событий для ставок. Заходи позже!</p>
            <#if !user??>
                <a href="sign_up" class="btn-primary" style="display: inline-block; text-decoration: none;">
                    Зарегистрироваться
                </a>
            </#if>
        </div>
    </#if>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
