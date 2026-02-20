<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавить новое событие ставок</title>
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
        select,
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        input[type="number"] {
            width: 100%;
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
    <h1>Добавить новое событие ставок</h1>

    <#if message??>
        <div class="message <#if messageType?? && messageType == 'success'>success<#else>error</#if>">
            ${message}
        </div>
    </#if>

    <form method="POST" accept-charset="UTF-8">
        <div class="form-group">
            <label for="targetUserId">ID целевого пользователя *</label>
            <input type="number" id="targetUserId" name="targetUserId" required min="1">
        </div>

        <div class="form-group">
            <label for="subjectId">ID предмета *</label>
            <input type="number" id="subjectId" name="subjectId" required min="1">
        </div>

        <div class="form-group">
            <label for="semestr">Семестр *</label>
            <input type="number" id="semestr" name="semestr" required min="1" max="2">
        </div>

        <div class="form-group">
            <label for="academicYear">Учебный год *</label>
            <input type="text" id="academicYear" name="academicYear" required maxlength="50" placeholder="2023-2024">
        </div>

        <div class="form-group">
            <label for="eventType">Тип события *</label>
            <select id="eventType" name="eventType" required>
                <option value="">Выберите тип</option>
                <option value="EXAM">EXAM</option>
                <option value="ASSIGNMENT">ASSIGNMENT</option>
                <option value="FINAL">FINAL</option>
            </select>
        </div>

        <div class="form-group">
            <label for="status">Статус *</label>
            <select id="status" name="status" required>
                <option value="">Выберите статус</option>
                <option value="OPEN">OPEN</option>
                <option value="CLOSED">CLOSED</option>
                <option value="RESOLVED">RESOLVED</option>
            </select>
        </div>

        <div class="form-group">
            <label for="description">Описание</label>
            <textarea id="description" name="description" maxlength="1000"></textarea>
        </div>

        <div class="button-group">
            <button type="submit" class="btn-submit">Добавить событие</button>
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

