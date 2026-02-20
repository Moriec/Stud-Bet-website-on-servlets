<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Мои ставки - StudBet</title>
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
            max-width: 1200px;
            margin: 32px auto;
            padding: 0 24px;
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

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 16px;
            margin-bottom: 32px;
        }

        .stat-card {
            background: linear-gradient(135deg, var(--primary-color) 0%, #2563eb 100%);
            color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .stat-label {
            font-size: 12px;
            font-weight: 500;
            opacity: 0.9;
            margin-bottom: 6px;
        }

        .stat-value {
            font-size: 28px;
            font-weight: 700;
            font-family: 'Courier New', monospace;
        }

        .bets-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }

        .bet-card {
            background-color: var(--bg-white);
            border: 1px solid var(--border-color);
            border-radius: 8px;
            padding: 16px;
            transition: all 0.3s;
        }

        .bet-card:hover {
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transform: translateY(-2px);
        }

        .bet-header {
            display: flex;
            justify-content: space-between;
            align-items: start;
            margin-bottom: 12px;
        }

        .bet-title {
            font-size: 16px;
            font-weight: 600;
            color: var(--text-primary);
        }

        .badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 12px;
            font-size: 11px;
            font-weight: 600;
        }

        .badge-active {
            background-color: #3b82f6;
            color: #ffffff;
        }

        .badge-won {
            background-color: #10b981;
            color: #ffffff;
        }

        .badge-lost {
            background-color: #ef4444;
            color: #ffffff;
        }

        .bet-info {
            font-size: 13px;
            color: var(--text-secondary);
            margin-bottom: 8px;
        }

        .bet-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 8px;
            padding: 8px 0;
            border-bottom: 1px solid var(--border-color);
        }

        .bet-row label {
            font-weight: 500;
            color: var(--text-primary);
        }

        .bet-row value {
            color: var(--text-secondary);
        }

        .amount-positive {
            color: var(--success-color);
            font-weight: 700;
        }

        .amount-negative {
            color: var(--error-color);
            font-weight: 700;
        }

        .bet-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 12px;
            padding-top: 12px;
            border-top: 1px solid var(--border-color);
        }

        .bet-amount {
            font-size: 18px;
            font-weight: 700;
        }

        .empty-state {
            text-align: center;
            padding: 60px 24px;
        }

        .empty-state-icon {
            font-size: 64px;
            color: var(--border-color);
            margin-bottom: 24px;
        }

        .empty-state-title {
            font-size: 24px;
            font-weight: 700;
            color: var(--text-primary);
            margin-bottom: 8px;
        }

        .empty-state-text {
            color: var(--text-secondary);
            margin-bottom: 24px;
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
            margin-top: 32px;
            padding-top: 24px;
            border-top: 1px solid var(--border-color);
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

            .stats-grid {
                grid-template-columns: repeat(2, 1fr);
            }

            .stat-value {
                font-size: 20px;
            }

            .bets-grid {
                grid-template-columns: 1fr;
            }

            .card-header {
                padding: 16px;
            }

            .card-header h2 {
                font-size: 20px;
            }

            .card-body {
                padding: 16px;
            }
        }
    </style>
</head>
<body>
<header class="header">
    <a href=".." class="header-logo">StudBet</a>
    <div class="header-title">Мои ставки</div>
</header>

<div class="container-main">
    <#if stats??>
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-label">Всего ставок</div>
                <div class="stat-value">${stats.totalBets}</div>
            </div>
            <div class="stat-card" style="background: linear-gradient(135deg, #10b981 0%, #059669 100%);">
                <div class="stat-label">Выиграно</div>
                <div class="stat-value">${stats.wonBets}</div>
            </div>
            <div class="stat-card" style="background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);">
                <div class="stat-label">Проиграно</div>
                <div class="stat-value">${stats.lostBets}</div>
            </div>
            <div class="stat-card" style="background: linear-gradient(135deg, #ff8c00 0%, #ea580c 100%);">
                <div class="stat-label">Активных</div>
                <div class="stat-value">${stats.activeBets}</div>
            </div>
        </div>
    </#if>

    <div class="card">
        <div class="card-header">
            <h2><i class="fas fa-dice"></i> Все мои ставки</h2>
        </div>
        <div class="card-body">
            <#if bets?? && bets?size gt 0>
                <div class="bets-grid">
                    <#list bets as betData>
                        <#assign bet = betData.bet>
                        <#assign event = betData.event>
                        <div class="bet-card">
                            <div class="bet-header">
                                <div class="bet-title">${event.description}</div>
                                <#if bet.status == "WON">
                                    <span class="badge badge-won">Выигрыш</span>
                                <#elseif bet.status == "LOST">
                                    <span class="badge badge-lost">Проигрыш</span>
                                <#elseif bet.status == "CANCELLED">
                                    <span class="badge badge-lost">Отменена</span>
                                <#else>
                                    <span class="badge badge-active">⏳ Активна</span>
                                </#if>
                            </div>

                            <div class="bet-info">
                                <strong>${event.eventType}</strong> • ${event.academicYear}
                            </div>

                            <div class="bet-row">
                                <label>Сумма ставки:</label>
                                <value class="amount-negative">${bet.betAmount}</value>
                            </div>

                            <div class="bet-row">
                                <label>Выплата:</label>
                                <value class="amount-positive">+${bet.payoutAmount}</value>
                            </div>

                            <div class="bet-row">
                                <label>Прибыль:</label>
                                <#if (bet.payoutAmount - bet.betAmount) gt 0>
                                    <value class="amount-positive">+${bet.payoutAmount - bet.betAmount}</value>
                                <#else>
                                    <value class="amount-negative">${bet.payoutAmount - bet.betAmount}</value>
                                </#if>
                            </div>

                            <div class="bet-row">
                                <label>Прогноз:</label>
                                <value>${bet.predictedScoreMin}-${bet.predictedScoreMax}</value>
                            </div>

                            <div class="bet-row">
                                <label>Создана:</label>
                                <value>${bet.createdAt?substring(0, 10)}</value>
                            </div>

                            <#if bet.resolvedAt??>
                                <div class="bet-row">
                                    <label>Разрешена:</label>
                                    <value>${bet.resolvedAt?substring(0, 10)}</value>
                                </div>
                            </#if>

                            <div class="bet-footer">
                                <div class="bet-amount">${bet.payoutAmount - bet.betAmount}</div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <!-- Empty State -->
                <div class="empty-state">
                    <div class="empty-state-icon">
                        <i class="fas fa-inbox"></i>
                    </div>
                    <div class="empty-state-title">Нет ставок</div>
                    <p class="empty-state-text">
                        Вы ещё не делали ставок. Начните ставить на события!
                    </p>
                    <a href=".." class="btn btn-back">
                        <i class="fas fa-arrow-left"></i> Перейти на главную
                    </a>
                </div>
            </#if>
        </div>
    </div>

    <div class="footer-nav">
        <a href=".." class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> На главную
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
