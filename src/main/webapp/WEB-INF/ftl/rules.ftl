<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Правила - StudBet</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #1e3a8a;
            --accent-color: #ff8c00;
            --success-color: #10b981;
            --error-color: #ef4444;
            --text-primary: #1f2937;
            --text-secondary: #6b7280;
            --border-color: #e5e7eb;
            --bg-light: #f3f4f6;
            --bg-white: #ffffff;
        }

        * {
            box-sizing: border-box;
        }

        html {
            font-family: 'Inter', 'Segoe UI', sans-serif;
        }

        body {
            background-color: var(--bg-light);
            color: var(--text-primary);
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: var(--primary-color);
            height: 64px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 24px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            color: #ffffff;
        }

        .header-logo {
            font-size: 24px;
            font-weight: 700;
            color: #ffffff;
            text-decoration: none;
        }

        .header-title {
            flex: 1;
            margin-left: 32px;
            font-size: 18px;
            font-weight: 600;
        }

        .header-nav a {
            color: #ffffff;
            text-decoration: none;
            font-weight: 500;
            transition: opacity 0.3s;
        }

        .header-nav a:hover {
            opacity: 0.8;
        }

        .container-main {
            max-width: 1000px;
            margin: 32px auto;
            padding: 0 24px;
        }

        .hero {
            background: linear-gradient(135deg, var(--primary-color) 0%, #2563eb 100%);
            color: white;
            padding: 60px 24px;
            border-radius: 12px;
            margin-bottom: 40px;
            text-align: center;
        }

        .hero h1 {
            font-size: 40px;
            font-weight: 700;
            margin: 0 0 16px 0;
        }

        .hero p {
            font-size: 18px;
            opacity: 0.95;
            margin: 0;
        }

        .card {
            background-color: var(--bg-white);
            border: 1px solid var(--border-color);
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            margin-bottom: 24px;
        }

        .card-header {
            background-color: var(--bg-light);
            border-bottom: 2px solid var(--border-color);
            padding: 20px;
        }

        .card-header h2 {
            margin: 0;
            font-size: 24px;
            font-weight: 700;
            color: var(--text-primary);
        }

        .card-body {
            padding: 24px;
        }

        .rule-section {
            margin-bottom: 32px;
        }

        .rule-section h3 {
            font-size: 20px;
            font-weight: 600;
            color: var(--primary-color);
            margin-bottom: 16px;
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .rule-section h3 i {
            font-size: 24px;
            color: var(--accent-color);
        }

        .rule-content {
            color: var(--text-secondary);
            line-height: 1.8;
            margin-bottom: 16px;
        }

        .rule-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .rule-list li {
            padding: 10px 0;
            padding-left: 32px;
            position: relative;
            color: var(--text-secondary);
            line-height: 1.7;
        }

        .rule-list li:before {
            content: "✓";
            position: absolute;
            left: 0;
            color: var(--success-color);
            font-weight: 700;
            font-size: 18px;
        }

        .highlight-box {
            background-color: rgba(255, 140, 0, 0.1);
            border-left: 4px solid var(--accent-color);
            padding: 16px;
            border-radius: 4px;
            margin: 16px 0;
        }

        .highlight-box strong {
            color: var(--accent-color);
        }

        .warning-box {
            background-color: rgba(239, 68, 68, 0.1);
            border-left: 4px solid var(--error-color);
            padding: 16px;
            border-radius: 4px;
            margin: 16px 0;
        }

        .warning-box strong {
            color: var(--error-color);
        }

        .success-box {
            background-color: rgba(16, 185, 129, 0.1);
            border-left: 4px solid var(--success-color);
            padding: 16px;
            border-radius: 4px;
            margin: 16px 0;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 12px 24px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            text-decoration: none;
            gap: 8px;
        }

        .btn-back {
            background-color: var(--accent-color);
            color: #ffffff;
        }

        .btn-back:hover {
            background-color: #e67e00;
            transform: translateY(-2px);
        }

        .btn-secondary {
            background-color: var(--bg-light);
            color: var(--text-primary);
            border: 1px solid var(--border-color);
        }

        .btn-secondary:hover {
            background-color: #e5e7eb;
        }

        .footer-nav {
            text-align: center;
            margin-top: 40px;
            padding-top: 24px;
            border-top: 1px solid var(--border-color);
        }

        .toc {
            background-color: var(--bg-light);
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 30px;
        }

        .toc h3 {
            margin: 0 0 16px 0;
            font-size: 18px;
            font-weight: 600;
        }

        .toc ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .toc li {
            margin-bottom: 8px;
        }

        .toc a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        .toc a:hover {
            color: var(--accent-color);
        }

        @media (max-width: 768px) {
            .header {
                height: 56px;
                padding: 0 16px;
            }

            .header-title {
                display: none;
            }

            .container-main {
                margin: 16px auto;
                padding: 0 16px;
            }

            .hero {
                padding: 40px 20px;
            }

            .hero h1 {
                font-size: 28px;
            }

            .card-header {
                padding: 16px;
            }

            .card-body {
                padding: 16px;
            }

            .rule-section h3 {
                font-size: 18px;
            }
        }
    </style>
</head>
<body>
    <header class="header">
        <a href="." class="header-logo">StudBet</a>
        <div class="header-title">Правила платформы</div>
    </header>

    <div class="container-main">
        <div class="hero">
            <h1>Правила StudBet</h1>
            <p>Внимательно прочитай правила перед тем, как начать делать ставки</p>
        </div>

        <div class="toc">
            <h3><i class="fas fa-list"></i> Содержание</h3>
            <ul>
                <li><a href="#general">1. Общие положения</a></li>
                <li><a href="#registration">2. Регистрация и аккаунт</a></li>
                <li><a href="#balance">3. Баланс и валюта</a></li>
                <li><a href="#betting">4. Ставки</a></li>
                <li><a href="#winners">5. Выигрыши и выплаты</a></li>
                <li><a href="#behavior">6. Правила поведения</a></li>
                <li><a href="#sanctions">7. Санкции</a></li>
                <li><a href="#disputes">8. Разрешение конфликтов</a></li>
            </ul>
        </div>

        <div class="card">
            <div class="card-header">
                <h2><i class="fas fa-scroll"></i> Полный текст правил</h2>
            </div>
            <div class="card-body">
                <div class="rule-section" id="general">
                    <h3><i class="fas fa-info-circle"></i> 1. Общие положения</h3>
                    <div class="rule-content">
                        StudBet — это букмекерская платформа для студентов, где вы можете делать ставки на академические результаты с использованием виртуальной валюты (StudCoins).
                    </div>
                    <ul class="rule-list">
                        <li>StudBet является образовательным проектом</li>
                        <li>Все ставки производятся виртуальной валютой без реальной денежной стоимости</li>
                        <li>Платформа доступна только для студентов учебных заведений</li>
                        <li>Использование платформы подразумевает согласие со всеми правилами</li>
                        <li>StudBet оставляет право изменять правила с предварительным уведомлением</li>
                    </ul>
                </div>

                <div class="rule-section" id="registration">
                    <h3><i class="fas fa-user-check"></i> 2. Регистрация и аккаунт</h3>
                    <div class="rule-content">
                        Для начала работы необходимо создать аккаунт, указав верные данные.
                    </div>
                    <ul class="rule-list">
                        <li>Вы можете иметь только один аккаунт на платформе</li>
                        <li>При регистрации необходимо указать актуальный email</li>
                        <li>Ответственность за сохранность пароля лежит на пользователе</li>
                        <li>Запрещается передавать доступ к аккаунту третьим лицам</li>
                        <li>StudBet может требовать верификацию личности</li>
                    </ul>
                    <div class="warning-box">
                        <strong>⚠️ Внимание:</strong> Создание нескольких аккаунтов приводит к их блокировке.
                    </div>
                </div>

                <div class="rule-section" id="balance">
                    <h3><i class="fas fa-coins"></i> 3. Баланс и валюта</h3>
                    <div class="rule-content">
                        Каждый пользователь получает начальный баланс для начала игры.
                    </div>
                    <ul class="rule-list">
                        <li>Новые пользователи получают 1000 StudCoins при регистрации</li>
                        <li>StudCoins можно получить за выигранные ставки</li>
                        <li>Купить StudCoins за реальные деньги <strong>НЕЛЬЗЯ</strong> (noadminStudbet@gmail.com) </li>
                        <li>Обмен StudCoins на реальные деньги <strong>ЗАПРЕЩЕН</strong> (noadminStudbet@gmail.com)</li>
                        <li>StudCoins теряются при: проигранных ставках, блокировке аккаунта</li>
                    </ul>
                    <div class="highlight-box">
                        <strong>💡 Совет:</strong> Распределяй свой баланс грамотно и делай ставки, которые ты можешь себе позволить.
                    </div>
                </div>

                <div class="rule-section" id="betting">
                    <h3><i class="fas fa-dice"></i> 4. Ставки</h3>
                    <div class="rule-content">
                        Ставки можно делать на различные академические события: оценки, результаты тестов.
                    </div>
                    <ul class="rule-list">
                        <li>Минимальная ставка: 1 StudCoin</li>
                        <li>Максимальная ставка: ваш текущий баланс</li>
                        <li>Коэффициенты устанавливаются автоматически на основе аналитики</li>
                        <li>После размещения ставки она <strong>не может быть отменена вами</strong></li>
                    </ul>
                    <div class="warning-box">
                        <strong>🚫 Запрещено:</strong> Договариваться с другими пользователями об исходах событий. Это считается манипуляцией рынка.
                    </div>
                </div>

                <div class="rule-section" id="winners">
                    <h3><i class="fas fa-trophy"></i> 5. Выигрыши и выплаты</h3>
                    <div class="rule-content">
                        Выигрыши рассчитываются и выплачиваются автоматически при подтверждении результатов.
                    </div>
                    <ul class="rule-list">
                        <li>Результат = Ставка × Коэффициент</li>
                        <li>Выплата происходит в течение 24 часов после подтверждения результатов события</li>
                        <li>У платформы нет комиссии за выигрыш, но стандартная маржа включена в коэффициент победы (7%)</li>
                    </ul>
                    <div class="success-box">
                        <strong>✓ Поздравляем:</strong> Если ты выиграл, StudCoins сразу попадут на твой баланс!
                    </div>
                </div>

                <div class="rule-section" id="behavior">
                    <h3><i class="fas fa-handshake"></i> 6. Правила поведения</h3>
                    <ul class="rule-list"></li>
                        <li>Распространение личной информации других пользователей запрещено</li>
                        <li>Попытки взлома или обхода защиты системы строго запрещены</li>
                        <li>Запрещено использовать ботов или автоматизировать действия на платформе</li>
                        <li>Честная игра — основной принцип StudBet</li>
                    </ul>
                </div>

                <div class="rule-section" id="sanctions">
                    <h3><i class="fas fa-ban"></i> 7. Санкции</h3>
                    <div class="rule-content">
                        Нарушение правил приводит к санкциям вплоть до полной блокировки аккаунта.
                    </div>
                    <ul class="rule-list">
                        <li><strong>Предупреждение</strong> — за первое нарушение</li>
                        <li><strong>Штраф StudCoins</strong> — за повторное нарушение</li>
                        <li><strong>Постоянная блокировка</strong> — за попытку взлома, манипуляцию системой, мошенничество</li>
                        <li>При блокировке баланс на аккаунте <strong>конфискуется</strong></li>
                    </ul>
                </div>

                <div class="rule-section" id="disputes">
                    <h3><i class="fas fa-gavel"></i> 8. Разрешение конфликтов</h3>
                    <div class="rule-content">
                        Если у вас есть спор или вопрос, есть процедура разрешения конфликта.
                    </div>
                    <ul class="rule-list">
                        <li>Пишите в Telegram бот @studbetbot</li>
                        <li>Время ответа: 24-48 часов</li>
                        <li>Предоставь всю необходимую информацию и скриншоты</li>
                        <li>Решение поддержки является окончательным</li>
                        <li>Апелляция возможна в течение 7 дней после решения</li>
                        <li>При подтверждении ошибки системы баланс восстанавливается</li>
                    </ul>
                </div>

                <div style="background-color: var(--bg-light); padding: 20px; border-radius: 8px; margin-top: 32px;">
                    <p style="margin: 0; color: var(--text-secondary);">
                        <strong>Последнее обновление:</strong> 21 ноября 2025 г. <br>
                    </p>
                </div>
            </div>
        </div>

        <div class="footer-nav">
            <a href="." class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> На главную
            </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
