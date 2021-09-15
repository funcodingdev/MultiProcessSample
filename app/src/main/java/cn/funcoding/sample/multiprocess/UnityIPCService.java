package cn.funcoding.sample.multiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

public class UnityIPCService extends Service {
    private static IProcessCall processCall;

    public static void sendToUnity(String data) {
        try {
            if (processCall != null) {
                processCall.sendToUnity(data);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ipc;
    }

    private IProcessIPC.Stub ipc = new IProcessIPC.Stub() {

        @Override
        public void registerProcessCall(IProcessCall call) throws RemoteException {
            processCall = call;
        }

        @Override
        public void unRegisterProcessCall(IProcessCall call) throws RemoteException {
            processCall = null;
        }

        @Override
        public void sendToMain(String data) throws RemoteException {
            EventBus.getDefault().post(new UnityProcessEvent(data));
        }
    };
}
