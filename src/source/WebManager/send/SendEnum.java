package WebManager.send;

import WebManager.security.Checker;

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

    public InterfaceSend getCurrentSend() {
        return send;
    }

    public static SendEnum getInstance(String s) {
        if (Checker.isContainsWrong(s))
            return SendEnum.REDIRECT;
        return SendEnum.valueOf(s.toUpperCase());
    }
}
