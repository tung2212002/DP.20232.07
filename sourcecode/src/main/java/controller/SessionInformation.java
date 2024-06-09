package controller;

import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
// Common coupling: SessionInformation cung cấp global data nhưng không có thành
// phần chịu trách nhiệm quản lý dữ liệu
public class SessionInformation {
    public static User mainUser;
    public static LocalDateTime expiredTime;

}
