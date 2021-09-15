// IProcessIPC.aidl
package cn.funcoding.sample.multiprocess;
import cn.funcoding.sample.multiprocess.IProcessCall;

// Declare any non-default types here with import statements

interface IProcessIPC {
    void registerProcessCall(IProcessCall call);
    void unRegisterProcessCall(IProcessCall call);
    void sendToMain(String data);
}