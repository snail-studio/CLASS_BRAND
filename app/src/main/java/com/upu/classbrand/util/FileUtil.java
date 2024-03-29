package com.upu.classbrand.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> read2list(File file){
        List<String> list=new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new FileReader(file));
            String s=null;
            while ((s=br.readLine())!=null){
                list.add(s);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return list;
    }
    public static String read2String(File file){
        String list="";
        try{
            BufferedReader br=new BufferedReader(new FileReader(file));
            String s=null;
            while ((s=br.readLine())!=null){
                list+=s+",";
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return list;
    }
    // 创建一个临时目录，用于复制临时文件，如assets目录下的离线资源文件
    public static String createTmpDir(Context context) {
        String sampleDir = "baiduTTS";
        String tmpDir = Environment.getExternalStorageDirectory().toString() + "/" + sampleDir;
        if (!FileUtil.makeDir(tmpDir)) {
            tmpDir = context.getExternalFilesDir(sampleDir).getAbsolutePath();
            if (!FileUtil.makeDir(sampleDir)) {
                throw new RuntimeException("create model resources dir failed :" + tmpDir);
            }
        }
        return tmpDir;
    }

    public static boolean fileCanRead(String filename) {
        File f = new File(filename);
        return f.canRead();
    }

    public static boolean makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return true;
        }
    }

    public static void copyFromAssets(AssetManager assets, String source, String dest, boolean isCover)
            throws IOException {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = assets.open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
            }
        }
    }
    public static long getFileSize(File file) {
        long size = 0;
        try {
            if (file == null) {
                return 0;
            }
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            }
            return size;
        } catch (Exception e) {
            return 0;
        }
    }
    public static String loadFromFile(File file){

        StringBuilder content = new StringBuilder();
        BufferedReader reader=null;
        try{
            if (file == null) {
                return "";
            }
            InputStream inputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader !=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
    public static void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
