package com.aguo.wxpush.test;

import java.io.File;

public class Test {
    public static void main(String[] args) {
    }

    //递归复制文件夹到指定目录
    public static void copyFolder(String oldPath, String newPath) {
        File file = new File(oldPath);
        String[] filePath = file.list();
        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }
        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + File.separator + filePath[i])).isDirectory()) {
                copyFolder(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
            }
            if (new File(oldPath + File.separator + filePath[i]).isFile()) {
                copyFolder(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
            }

        }
        String pageRows = "1";


        String sql = "SELECT a. mrl_code as mrlCodea.lotcode as lotCode from UWM STOCK a " +
                "join pmbf_work_center b on aWORK_CENTER GID = b.gid" +
                "where RONUM <= " + pageRows;
    }


}
