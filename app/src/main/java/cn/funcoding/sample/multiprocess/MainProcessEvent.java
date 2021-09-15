package cn.funcoding.sample.multiprocess;

public class MainProcessEvent {
    private final String data;

    public MainProcessEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
