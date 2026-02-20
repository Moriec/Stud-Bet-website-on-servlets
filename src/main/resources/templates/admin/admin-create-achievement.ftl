<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавить новое достижение</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        input[type="number"] {
            width: 100%;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn-submit {
            background-color: #4CAF50;
            color: white;
            flex: 1;
        }
        .btn-submit:hover {
            background-color: #45a049;
        }
        .btn-back {
            background-color: #808080;
            color: white;
        }
        .btn-back:hover {
            background-color: #696969;
        }
        .message {
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
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
    </style>
</head>
<body>
<div class="container">
    <h1>Добавить новое достижение</h1>

    <#if message??>
        <div class="message <#if messageType?? && messageType == 'success'>success<#else>error</#if>">
            ${message}
        </div>
    </#if>

    <form method="POST" accept-charset="UTF-8">
        <div class="form-group">
            <label for="name">Название достижения *</label>
            <input type="text" id="name" name="name" required maxlength="255">
        </div>

        <div class="form-group">
            <label for="description">Описание</label>
            <textarea id="description" name="description" maxlength="1000"></textarea>
        </div>

        <div class="form-group">
            <label for="iconUrl">URL иконки</label>
            <input type="text" id="iconUrl" name="iconUrl" maxlength="500">
        </div>

        <div class="form-group">
            <label for="pointsReward">Награда в баллах *</label>
            <input type="number" id="pointsReward" name="pointsReward" required min="0" max="9999">
        </div>

        <div class="form-group">
            <label for="achievementType">Тип достижения</label>
            <input type="text" id="achievementType" name="achievementType" maxlength="100">
        </div>

        <div class="form-group">
            <label for="criteriaJson">Критерии (JSON)</label>
            <textarea id="criteriaJson" name="criteriaJson" maxlength="2000"></textarea>
        </div>

        <div class="button-group">
            <button type="submit" class="btn-submit">Добавить достижение</button>
            <button type="button" class="btn-back" onclick="goBack()">Вернуться</button>
        </div>
    </form>
</div>

<script>
    function goBack() {
        window.location.href = "..";
    }
</script>
</body>
</html>

