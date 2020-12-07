package webmanager.sender;

import webmanager.security.Checker;
import webmanager.sender.sends.*;

public enum SendEnum {
    REDIRECT {
        {
            this.send = new RedirectSend();
        }
    },
    REGISTER {
        {
            this.send = new RegisterSend();
        }
    },
    LOGIN {
        {
            this.send = new LoginSend();
        }
    },
    LOGOUT {
        {
            this.send = new LogoutSend();
        }
    },
    CHANGE {
        {
            this.send = new ChangeSend();
        }
    };

    InterfaceSend send;

    public static SendEnum getInstance(String s) {
        if (Checker.isContainsWrong(s))
            return SendEnum.REDIRECT;
        return SendEnum.valueOf(s.toUpperCase());
    }

    public InterfaceSend getCurrentSend() {
        return send;
    }
}
