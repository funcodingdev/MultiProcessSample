package cn.funcoding.sample.multiprocess;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.funcoding.sample.multiprocess.databinding.ActivityMain2Binding;

public class MainProcessActivity2 extends BaseActivity {
    private ActivityMain2Binding binding;

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected String getActivityTitle() {
        return "MainProcessActivity2";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.normalBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MainProcessEvent("Normal Main Event From MainProcessActivity2"));
            }
        });
        binding.aidlBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnityIPCService.sendToUnity("AIDL Main Event From MainProcessActivity2");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnityEvent(UnityProcessEvent event) {
        LogUtil.i("MainProcessActivity2 onUnityEvent: " + event.getData());

    }
}