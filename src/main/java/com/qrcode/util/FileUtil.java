package com.qrcode.util;

import java.io.File;
import java.io.IOException;

/**
 * Class Name : FileUtil<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/1015:56<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public class FileUtil {

    public static File createNewFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }
}
