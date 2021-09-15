package cn.funcoding.sample.multiprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

public class UnityContentProvider implements ServiceConnection {
    private IProcessIPC ipc;
    private IProcessCall.Stub processCall = new IProcessCall.Stub() {
        @Override
        public void sendToUnity(String data) throws RemoteException {
            EventBus.getDefault().post(new MainProcessEvent(data));
        }
    };

    public void bind(@NonNull Context context) {
        Intent intent = new Intent(context, UnityIPCService.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    public void unbind(@NonNull Context context) {
        context.unbindService(this);
    }

    public void sendToMain(String data) {
        try {
            if (ipc != null) {
                ipc.sendToMain(data);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ipc = IProcessIPC.Stub.asInterface(iBinder);
        try {
            if (ipc != null) {
                ipc.registerProcessCall(processCall);
            } else {
                LogUtil.i("进程没有绑定通信");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        try {
            if (ipc != null) {
                ipc.unRegisterProcessCall(processCall);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
