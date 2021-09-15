package cn.funcoding.sample.multiprocess;

import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.funcoding.sample.multiprocess.databinding.ActivityMainBinding;

public class MainProcessActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected String getActivityTitle() {
        return "MainProcessActivity";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.startBt.setOnClickListener(view -> {
            startToUnityActivity();
        });
    }

    private void startToUnityActivity() {
        Intent intent = new Intent(this, UnityProcessActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnityProcessEvent(UnityProcessEvent event) {
        LogUtil.i("MainProcessActivity onUnityProcessEvent: " + event.getData());
        UnityIPCService.sendToUnity("UnityProcessEvent Received");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainProcessEvent(MainProcessEvent event) {
        LogUtil.i("MainProcessActivity onMainProcessEvent: " + event.getData());

    }
}