package com.example.nidoqueue;

public class UserControl {

    private static UserControl userControl = new UserControl();

    private UserControl(){}

    static RequestManager requestManager = RequestManager.getInstance();

    ContextManager contextManager = ContextManager.getInstance();

    public static UserControl getInstance() {
        return userControl;
    }

    public void verifyLogin() {
        if(true) {
            requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), ExperimentActivity.class);
        } else {
            requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), ExperimentActivity.class);
        }
    }

    public void signIn() {

    }

    public void signUp() {
    }
}
