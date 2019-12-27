package com.houyikj.myapplication;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsDBUtils {
    public static final String packageBame = "com.houyikj.myapplication";
    public static final String db_name = "updb.db";
    public static final String db_name2 = "jiaKao.db";
    public static final String filePath = "data/data/" + packageBame + "/databases/" + db_name2;
    public static final String pathStr = "data/data/" + packageBame + "/databases";

    public static String getPath(Context context) {
        File dbFile = new File(filePath);
        if (dbFile.exists()) {
            return filePath;
        } else {
            File path = new File(pathStr);
            path.mkdir();
            try {
                InputStream is = context.getClass().getClassLoader().getResourceAsStream("assets/" + db_name);

                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[10240];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return filePath;
        }
    }
}
