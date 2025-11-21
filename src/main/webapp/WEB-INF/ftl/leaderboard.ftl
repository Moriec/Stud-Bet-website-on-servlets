<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Лидербод - StudBet</title>
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
            padding: 40px 24px;
            border-radius: 12px;
            margin-bottom: 32px;
            text-align: center;
        }

        .hero h1 {
            font-size: 36px;
            font-weight: 700;
            margin: 0 0 12px 0;
        }

        .hero p {
            font-size: 16px;
            opacity: 0.95;
            margin: 0;
        }

        .current-user-card {
            background: linear-gradient(135deg, var(--accent-color) 0%, #ea580c 100%);
            color: white;
            padding: 32px;
            border-radius: 12px;
            margin-bottom: 32px;
            box-shadow: 0 4px 12px rgba(255, 140, 0, 0.3);
        }

        .current-user-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 24px;
        }

        .user-stat {
            text-align: center;
        }

        .stat-label {
            font-size: 12px;
            font-weight: 500;
            opacity: 0.9;
            margin-bottom: 8px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .stat-value {
            font-size: 32px;
            font-weight: 700;
            font-family: 'Courier New', monospace;
        }

        .stat-username {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 4px;
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
            padding: 0;
        }

        .leaderboard-table {
            width: 100%;
            border-collapse: collapse;
        }

        .leaderboard-table thead {
            background-color: var(--bg-light);
        }

        .leaderboard-table th {
            padding: 16px;
            text-align: left;
            font-weight: 700;
            color: var(--text-primary);
            border-bottom: 2px solid var(--border-color);
            font-size: 14px;
        }

        .leaderboard-table td {
            padding: 16px;
            border-bottom: 1px solid var(--border-color);
            color: var(--text-secondary);
            font-size: 15px;
        }

        .leaderboard-table tbody tr {
            transition: background-color 0.3s;
        }

        .leaderboard-table tbody tr:hover {
            background-color: var(--bg-light);
        }

        .rank-number {
            font-weight: 700;
            color: var(--text-primary);
            font-size: 18px;
        }

        .rank-medal {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 32px;
            height: 32px;
            border-radius: 50%;
            font-weight: 700;
            color: white;
            font-size: 14px;
        }

        .rank-1 {
            background: linear-gradient(135deg, #fbbf24 0%, #f59e0b 100%);
        }

        .rank-2 {
            background: linear-gradient(135deg, #c7d2fe 0%, #a5b4fc 100%);
            color: #1e3a8a;
        }

        .rank-3 {
            background: linear-gradient(135deg, #fed7aa 0%, #fdba74 100%);
            color: #1e3a8a;
        }

        .rank-default {
            background-color: var(--text-secondary);
        }

        .username {
            font-weight: 600;
            color: var(--text-primary);
            text-decoration: none;
            transition: color 0.3s;
            display: inline-block;
        }

        .username:hover {
            color: var(--accent-color);
            text-decoration: underline;
        }

        .rating-points {
            font-weight: 700;
            color: var(--accent-color);
            font-size: 16px;
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
                padding: 30px 20px;
            }

            .hero h1 {
                font-size: 28px;
            }

            .current-user-card {
                padding: 24px;
            }

            .current-user-grid {
                grid-template-columns: repeat(2, 1fr);
                gap: 16px;
            }

            .stat-value {
                font-size: 24px;
            }

            .leaderboard-table {
                font-size: 14px;
            }

            .leaderboard-table th,
            .leaderboard-table td {
                padding: 12px;
            }

            .rank-medal {
                width: 28px;
                height: 28px;
                font-size: 12px;
            }
        }
    </style>
</head>
<body>
<header class="header">
    <a href="." class="header-logo">StudBet</a>
    <div class="header-title">Лидерборд</div>
</header>

<div class="container-main">
    <div class="hero">
        <h1>🏆 Лидербод StudBet</h1>
        <p>Рейтинг лучших игроков платформы</p>
    </div>

    <#if currentUser?? && currentUserRank??>
        <div class="current-user-card">
            <div class="current-user-grid">
                <div class="user-stat">
                    <div class="stat-label">Твоё место</div>
                    <div class="stat-value">#${currentUserRank.rank}</div>
                </div>
                <div class="user-stat">
                    <div class="stat-label">Рейтинг</div>
                    <div class="stat-value">${currentUserRank.ratingPoints}</div>
                </div>
                <div class="user-stat">
                    <div class="stat-label">Всего игроков</div>
                    <div class="stat-value">${currentUserRank.totalUsers}</div>
                </div>
            </div>
        </div>
    </#if>

    <div class="card">
        <div class="card-header">
            <h2><i class="fas fa-crown"></i> Топ 10 игроков</h2>
        </div>
        <div class="card-body">
            <#if topUsers?? && topUsers?size gt 0>
                <table class="leaderboard-table">
                    <thead>
                    <tr>
                        <th style="width: 60px;">Место</th>
                        <th>Игрок</th>
                        <th style="text-align: right;">Рейтинг</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list topUsers as item>
                        <#assign rank = item.rank>
                        <#assign user = item.user>
                        <tr>
                            <td>
                                <#if rank == 1>
                                    <span class="rank-medal rank-1">${rank}</span>
                                <#elseif rank == 2>
                                    <span class="rank-medal rank-2">${rank}</span>
                                <#elseif rank == 3>
                                    <span class="rank-medal rank-3">${rank}</span>
                                <#else>
                                    <span class="rank-medal rank-default">${rank}</span>
                                </#if>
                            </td>
                            <td>
                                <a href="./profile?id=${user.id}" class="username">${user.username}</a>
                            </td>
                            <td style="text-align: right;">
                                <span class="rating-points">${user.ratingPoints}</span>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            <#else>
                <div style="text-align: center; padding: 40px;">
                    <p style="color: var(--text-secondary);">Нет данных</p>
                </div>
            </#if>
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
