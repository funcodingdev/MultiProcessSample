package cn.funcoding.sample.multiprocess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.funcoding.sample.multiprocess.databinding.ActivityUnityBinding;

public class UnityProcessActivity extends BaseActivity {
    private ActivityUnityBinding binding;
    private UnityContentProvider unityContentProvider = new UnityContentProvider();

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected String getActivityTitle() {
        return "UnityProcessActivity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.normalBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new UnityProcessEvent("Normal Unity Event"));
            }
        });
        binding.bindBt.setOnClickListener(view -> {
            unityContentProvider.bind(UnityProcessActivity.this);
        });
        binding.unbindBt.setOnClickListener(view -> {
            unityContentProvider.unbind(UnityProcessActivity.this);
        });
        binding.aidlBt.setOnClickListener(view -> {
            unityContentProvider.sendToMain("AIDL Unity Event From UnityProcessActivity");
        });
        binding.startBt.setOnClickListener(view -> {
            startToMainActivity2();
        });
    }

    private void startToMainActivity2() {
        Intent intent = new Intent(this, MainProcessActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainProcessEvent(MainProcessEvent event) {
        LogUtil.i("UnityProcessActivity onMainProcessEvent: " + event.getData());
    }
}
