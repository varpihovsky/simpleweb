package WebManager.send;

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
    };

    InterfaceSend send;

    public InterfaceSend getCurrentSend() {
        return send;
    }
}
