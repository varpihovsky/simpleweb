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
    };

    InterfaceSend send;

    public InterfaceSend getCurrentSend() {
        return send;
    }
}
