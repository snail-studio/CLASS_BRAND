package com.upu.classbrand.listener;

import java.io.File;

public interface DownloadImageListener {
    void onStart(String filename);
    void onProgress(String filename,int currentLength);
    void onFinish(int faceinfoid,String oldpath,String newpath);
    void onFailure(File file, String erroInfo, int faceinfoid);
}
