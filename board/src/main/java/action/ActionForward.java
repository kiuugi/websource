package action;

public class ActionForward {
    private String path; // 경로 담을 변수
    private boolean redirect; // sendRedirect 여부(true/false)
    // 어디로 가야하는지 하는 경로와 어떤방식으로 갈지 여부

    public String getPath() {
        return path;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public ActionForward(String path, boolean redirect) {
        this.path = path;
        this.redirect = redirect;
    }

    public ActionForward() {
    }

}
