package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import helper.Security;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author
 */

/*
 * SOLID - Single responsibility principle: AuthenticationController đang thực
 * hiện nhiều hơn 1 nhiệm vụ đó là xác thực người dùng, quản lý session và mã
 * hóa dữ liệu
 * Có xem xét đưa md5() vào Utils , để SessionInformation quản lý getMainUser()
 * và AuthenticationController gồm: isAnonymousSession(), login(), logout()
 */

/*
 * SOLID - Liskov substitution principle: AuthenticationController không cần
 * triển khai các phương thức khác của BaseController
 */

// Temporal cohesion: md5() không liên quan đến class chỉ thực hiện theo thứ tự
// thời gian bởi việc thực hiện login() sử dụng md5() nên để md5() ở phần khác

public class AuthenticationController extends BaseController {

    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    // Common coupling: getMainUser() sử dụng global data SessionInformation là
    // mainUser và expiredTime
    public User getMainUser() throws ExpiredSessionException {
        if (SessionInformation.mainUser == null || SessionInformation.expiredTime == null
                || SessionInformation.expiredTime.isBefore(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
        } else
            return SessionInformation.mainUser.cloneInformation();
    }

    public void login(String email, String password) throws Exception {
        try {
            User user = new UserDAO().authenticate(email, Security.encryptMD5(password));
            if (Objects.isNull(user))
                throw new FailLoginException();
            SessionInformation.mainUser = user;
            // Clean code: đang sử dụng trực tiếp 24h, nên nên đặt thành biến hằng
            int expiredTime = 24;
            SessionInformation.expiredTime = LocalDateTime.now().plusHours(expiredTime);
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    // Common coupling: logout() sử dụng global data SessionInformation là mainUser
    // và expiredTime
    public void logout() {
        SessionInformation.mainUser = null;
        SessionInformation.expiredTime = null;
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
    // Clean code: tên md5() không rõ ràng, nên đặt tên rõ ràng hơn và chuyển vào Sercurity

}
