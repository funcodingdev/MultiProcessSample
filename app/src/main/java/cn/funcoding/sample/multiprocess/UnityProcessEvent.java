package cn.funcoding.sample.multiprocess;

public class UnityProcessEvent {
    private final String data;

    public UnityProcessEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return data;
    }
}
