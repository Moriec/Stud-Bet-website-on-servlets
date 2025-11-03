package com.studbet.dao;

import com.studbet.model.User;

public interface UserDao {
    User save(User user); // Возвращает добавленную сущность
    User update(User user); // Возвращает измененную сущность
    int delete(User user); // Возвращает id удаленной сущности
    User findById(int id); // Возвращает сущность с тем же id, null если не найдено
    User findByUsername(String username); // Возвращает сущность с тем же никнеймом, null если не найдено
    User findByEmail(String email); // Возвращает сущность с той же почтой, null если не найдено
}
