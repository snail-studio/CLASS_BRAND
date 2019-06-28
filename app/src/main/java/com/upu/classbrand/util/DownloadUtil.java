package com.upu.classbrand.util;

import android.os.Environment;
import android.util.Log;

import com.upu.classbrand.helper.DownloadHelper;
import com.upu.classbrand.listener.DownloadImageListener;
import com.upu.classbrand.tools.DownloadInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadUtil {
    private static final String TAG="DownloadUtil";
    private static final String SAVE_PATH= Environment.getExternalStorageDirectory()+"/upudownload/";
    private static final String BASE_PATH=Environment.getExternalStorageDirectory()+"/zenkaface_off/temp/face/";
    //protected DownloadInterface mApi;
    //private Call<ResponseBody> mCall;
    private File mFile;
    //private Thread mThread;
    private String mDownloadPath;
    public DownloadUtil(){
//        if (mApi==null){
//            mApi=ApiHelper.getmInstance().buildRetrofit("http://www.baidu.com")
//                    .createService(DownloadInterface.class);
//        }
    }
    private String filaname="";
    private String newpath="";
    private String oldpath="";
    private int faceinfoid=-1;
    private String imageUrl="";
    public void downloadApk(String apkurl, final DownloadImageListener downloadListener){
        DownloadInterface mApi = DownloadHelper.getmInstance().buildRetrofit("http://www.baidu.com")
                .createService(DownloadInterface.class);
        if (mApi == null) {
            Log.e(TAG, "下载接口为空");
            return;
        }
        mFile = new File(SAVE_PATH+"temp.apk");

        Call<ResponseBody> mCall = mApi.downloadFile(apkurl);
        if (FileUtils.createOrExistsFile(mFile)){
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    Thread mThread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Log.e("MainActivity", "准备写文件" + filaname);
                            writeFile2Disk(response, mFile, faceinfoid,filaname,imageUrl,oldpath,newpath, downloadListener);
                        }
                    };
                    mThread.start();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    downloadListener.onFailure(mFile, "网络错误!",faceinfoid);
                }
            });
        }
    }
    private void writeFile2Disk(Response<ResponseBody> response, File file, int faceinfoid,String filaname,String imageUrl, String oldpath, String newpath, DownloadImageListener downloadListener) {
        OutputStream os = null;
        InputStream is = null;
        try {
            is = response.body().byteStream();
            String sss = response.raw().request().url().url().toString();
            String filename1 = "";

            File file1 = new File(SAVE_PATH+"temp.apk");

            downloadListener.onStart(filaname);
            long curentLength = 0;

            if (response.body() == null) {
                downloadListener.onFailure(file, "资源错误",faceinfoid);
                return;
            }

            long totalLength = response.body().contentLength();

            os = new FileOutputStream(file);
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                curentLength += len;
                Log.e(TAG, "当前进度：" + curentLength);
                downloadListener.onProgress(filaname, (int) (100 * curentLength / totalLength));
                if ((int) (100 * curentLength / totalLength) == 100) {
                    Log.e("MainActivity","文件真正下载完成");
                    downloadListener.onFinish(faceinfoid,oldpath,newpath);
                }
            }
        } catch (FileNotFoundException e) {
            downloadListener.onFailure(file, "未找到文件",faceinfoid);
            e.printStackTrace();
        } catch (IOException e) {
            downloadListener.onFailure(file, "IO错误",faceinfoid);
            e.printStackTrace();
        } catch (Exception e){
            downloadListener.onFailure(file, "其他错误",faceinfoid);
            e.printStackTrace();
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
