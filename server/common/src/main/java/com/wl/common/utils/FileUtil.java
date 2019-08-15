package com.wl.common.utils;

import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-15 13:42
 **/
public class FileUtil {
    public static String fileToBase64(String fileAbsolutePath) {
        String base64 = null;
        FileInputStream inputStream = null;

        try {
            File file = new File(fileAbsolutePath);
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
            String type = getFileType(file.getName());
            if (type.equals("image")) {
                base64 = "data:image/jpeg;base64," + base64;
            } else if (type.equals("xls")) {
                base64 = "data:application/vnd.ms-excel;base64," + base64;
            } else {
                base64 = "data:application/octet-stream;base64," + base64;
            }
        } catch (Exception var14) {
            EvLog.error(var14);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var13) {
                    EvLog.error(var13);
                }
            }

        }

        return base64;
    }

    public static File base64ToFile(String base64, String fileFullName) {
        Path path = Paths.get(fileFullName);
        if (path != null && base64 != null) {
            File file = null;
            String filePath = path.getParent().toString();
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }

            BufferedOutputStream bos = null;
            FileOutputStream fos = null;

            try {
                file = new File(fileFullName);
                String type = getFileType(file.getName());
                if (base64.startsWith("data:")) {
                    int index = base64.indexOf("base64,");
                    if (index > 0) {
                        base64 = base64.substring(index + 7);
                    }
                }

                byte[] bytes = Base64.getDecoder().decode(base64);
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(bytes);
            } catch (Exception var22) {
                EvLog.error(var22);
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException var21) {
                        EvLog.error(var21);
                    }
                }

                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException var20) {
                        EvLog.error(var20);
                    }
                }

            }

            return file;
        } else {
            return null;
        }
    }

    private static boolean deleteFile(String fileAbsolutePath) {
        if (StringUtils.isEmpty(fileAbsolutePath)) {
            return true;
        } else {
            File fi = new File(fileAbsolutePath);
            if (fi.isFile()) {
                System.gc();
                boolean result = fi.delete();
                return result;
            } else {
                return false;
            }
        }
    }

    public static boolean deleteFileOrDire(String path) {
        File file = new File(path);
        if (file.isFile()) {
            deleteFile(path);
        } else if (file.isDirectory()) {
            File[] var2 = file.listFiles();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                File childFile = var2[var4];
                if (childFile.isFile()) {
                    deleteFile(childFile.getAbsolutePath());
                } else if (childFile.isDirectory() && childFile.listFiles() != null && childFile.listFiles().length > 0) {
                    deleteFileOrDire(childFile.getAbsolutePath());
                }
            }

            file.delete();
        }

        return false;
    }

    public static List<File> getFilesByPath(String path) {
        List<File> backList = new ArrayList();
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] var3 = dir.listFiles();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File childFile = var3[var5];
                if (childFile.isFile()) {
                    backList.add(childFile);
                } else if (childFile.listFiles() != null && childFile.listFiles().length > 0) {
                    backList.addAll(getFilesByPath(childFile.getAbsolutePath()));
                }
            }
        } else if (dir.isFile()) {
            backList.add(dir);
        }

        return backList;
    }

    public static File getFilesByName(String path, String fileName) {
        List<File> fileList = getFilesByPath(path);
        return (File)EvCommonTool.findObject(fileList, (o) -> {
            return o.getName().equals(fileName);
        });
    }

    public static String getFileType(String fileName) {
        String fileType = null;
        if (!StringUtils.isEmpty(fileName)) {
            int index = fileName.lastIndexOf(".");
            if (index >= 0) {
                fileType = fileName.substring(index + 1);
            }
        }

        return fileType;
    }

}
