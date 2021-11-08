package com.kingwin.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KingWin
 * @since 2021/11/4 11:05 上午
 */

class KFileUtils {


    /**
     * 根据文件路径获取文件
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(String filePath){
        return KStringUtils.isSpace(filePath) ? null : new File(filePath);
    }


    /**
     * 文件是否存在
     * @param filePath 文件路径
     * @return 是否存在
     */
    public static boolean isFileExists(String filePath){
        return isFileExists(getFileByPath(filePath));
    }


    /**
     * 文件是否存在
     * @param file 文件
     * @return 是否存在
     */
    public static boolean isFileExists(File file){
        return null != file && file.exists();
    }

    /**
     * 是否是目录
     * @param filePath 文件路径
     * @return 是否目录
     */
    public static boolean isDir(String filePath){
        return isDir(getFileByPath(filePath));
    }

    /**
     * 是否是目录
     * @param file 文件
     * @return 是否目录
     */
    public static boolean isDir(File file){
        return isFileExists(file) && file.isDirectory();
    }

    /**
     * 是否是文件
     * @param filePath 文件路径
     * @return 是否文件
     */
    public static boolean isFile(String filePath){
        return isFile(getFileByPath(filePath));
    }

    /**
     * 是否是文件
     * @param file 文件
     * @return 是否文件
     */
    public static boolean isFile(File file){
        return isFileExists(file) && file.isFile();
    }

    /**
     * 创建文件夹
     * @param filePath 文件路径
     * @return 是否创建成功
     */
    public static boolean createDir(String filePath){
        return createDir(getFileByPath(filePath));
    }

    /**
     * 创建文件夹
     * @param file 文件
     * @return 是否创建成功
     */
    public static boolean createDir(File file){
        return null != file && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 创建文件(默认不会重置文件)
     * @param filePath 文件路径
     * @return 是否创建成功
     */
    public static boolean createFile(String filePath){
        return createFile(getFileByPath(filePath));
    }

    /**
     * 创建文件(默认不会重置文件)
     * @param file 文件
     * @return 是否创建成功
     */
    public static boolean createFile(File file){
        return createFile(file,false);
    }

    /**
     * 创建文件
     * @param filePath 文件路径
     * @param isReset 是否重置文件 即删除后重新创建
     * @return 是否创建成功
     */
    public static boolean createFile(String filePath,boolean isReset){
        return createFile(getFileByPath(filePath),isReset);
    }

    /**
     * 创建文件
     * @param file 文件
     * @param isReset 是否重置文件 即删除后重新创建
     * @return 是否创建成功
     */
    public static boolean createFile(File file,boolean isReset){
        if(null == file) return false;
        if(file.exists()){
            if(isReset){
                if(file.isFile() && !file.delete()) return false;
            }else{
                return file.isFile();
            }
        }

        if(!createDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将输入流写入文件
     * @param filePath 文件路径
     * @param is 输入流
     * @param append 是否追加在文件末
     * @return 是否写入成功
     */
    public static boolean writeFileFromIS(String filePath, InputStream is, boolean append){
        return writeFileFromIS(getFileByPath(filePath),is,append);
    }

    /**
     * 将输入流写入文件
     * @param file 文件
     * @param is 输入流
     * @param append 是否追加在文件末
     * @return 是否写入成功
     */
    public static boolean writeFileFromIS(File file, InputStream is, boolean append){
        if(null == file || null == is) return false;
        if(!createFile(file)) return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file,append));
            byte data[] = new byte[524288]; //512KB
            int len;
            while ((len = is.read(data)) != -1){
                os.write(data,0,len);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(is,os);
        }
    }



    /**
     * 将输入流写入文件
     * @param filePath 文件路径
     * @param content 字符串内容
     * @param append 是否追加在文件末
     * @return 是否写入成功
     */
    public static boolean writeFileFromString(String filePath, String content, boolean append){
        return writeFileFromString(getFileByPath(filePath),content,append);
    }


    /**
     * 将输入流写入文件
     * @param file 文件
     * @param content 字符串内容
     * @param append 是否追加在文件末
     * @return 是否写入成功
     */
    public static boolean writeFileFromString(File file, String content, boolean append){
        if(null == file || null == content) return false;
        if(!createFile(file)) return false;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file,append);
            fileWriter.write(content);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(fileWriter);
        }
    }

    /**
     * 获取文件编码格式
     * @param filePath 文件路径
     * @return 文件编码
     */
    public static String getFileCharsetSimple(String filePath){
        return getFileCharsetSimple(getFileByPath(filePath));
    }

    /**
     * 获取文件编码格式
     * @param file 文件
     * @return 文件编码
     */
    public static String getFileCharsetSimple(File file){
        int p = 0;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            p -= (is.read() << 8) + is.read();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeIO(is);
        }
        switch (p) {
            case 0xefbb:
                return "UTF-8";
            case 0xfffe:
                return "Unicode";
            case 0xfeff:
                return "UTF-16BE";
            default:
                return "GBK";
        }

    }

    /**
     * 获取文件行数
     * @param filePath 文件路径
     * @return 文件行数
     */
    public static int getFileLines(String filePath){
        return getFileLines(getFileByPath(filePath));
    }

    /**
     * 获取文件行数
     * @param file 文件
     * @return 文件行数
     */
    public static int getFileLines(File file){
        int count = 1;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[524288]; //512KB
            int readChars;
            while ((readChars = is.read(buffer,0,524288)) != -1){
                for (int i = 0 ; i < readChars; ++i){
                    if(buffer[i] == '\n') ++count;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeIO(is);
        }
        return count;
    }


    /**
     * 指定编码按行读取文件到list
     * @param filePath 文件路径
     * @param charsetName 编码格式
     * @return 包含制定行的list
     */
    public static List<String> getFile2List(String filePath, String charsetName){
        return getFile2List(getFileByPath(filePath),charsetName);
    }


    /**
     * 指定编码按行读取文件到list
     * @param file 文件
     * @param charsetName 编码格式
     * @return 包含制定行的list
     */
    public static List<String> getFile2List(File file, String charsetName){
        return getFile2List(file,0,0x7FFFFFF,charsetName);
    }


    /**
     * 指定编码按行读取文件到list
     * @param filePath 文件路径
     * @param start 读取的开始行数
     * @param end 读取的结束行数
     * @param charsetName 编码格式
     * @return 包含制定行的list
     */
    public static List<String> getFile2List(String filePath, int start, int end, String charsetName){
        return getFile2List(getFileByPath(filePath),start,end,charsetName);
    }


    /**
     * 指定编码按行读取文件到list
     * @param file 文件
     * @param start 读取的开始行数
     * @param end 读取的结束行数
     * @param charsetName 编码格式
     * @return 包含制定行的list
     */
    public static List<String> getFile2List(File file, int start, int end, String charsetName){
        if (null == file) return null;
        if (start > end) return null;
        BufferedReader reader = null;
        try {
            String line;
            int curLine = 1;
            List<String> list = new ArrayList<>();
            if(KStringUtils.isSpace(charsetName)){
                reader = new BufferedReader(new FileReader(file));
            }else{
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charsetName));
            }
            while (null != (line = reader.readLine())){
                if(curLine > end) break;
                if (start <= curLine && curLine <= end) list.add(line);
                ++curLine;
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            closeIO(reader);
        }
    }


    /**
     * 指定编码按行读取文件到字符串中
     * @param filePath 文件路径
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String readFile2String(String filePath,String charsetName){
        return readFile2String(getFileByPath(filePath),charsetName);
    }


    /**
     * 指定编码按行读取文件到字符串中
     * @param file 文件
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String readFile2String(File file,String charsetName){
        if(null == file) return null;
        BufferedReader reader = null;
        try{
            StringBuffer sb = new StringBuffer();
            if(KStringUtils.isSpace(charsetName)){
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            }else{
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charsetName));
            }
            String line;
            while (null != (line = reader.readLine())){
                sb.append(line).append("\r\n");//windows系统换行为\r\n，Linux为\n
            }
            // 要去除最后的换行符
            return sb.delete(sb.length() - 2,sb.length()).toString();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }finally {
            closeIO(reader);
        }
    }


    /**
     * 读取文件到字符串数组中
     * @param filePath 文件路径
     * @return 字符数组
     */
    public static byte[] readFile2Bytes(String filePath){
        return readFile2Bytes(getFileByPath(filePath));
    }

    /**
     * 读取文件到字符串数组中
     * @param file 文件
     * @return 字符数组
     */
    public static byte[] readFile2Bytes(File file){
        if(null == file) return null;
        try {
            return KConvertUtils.inputStream2Bytes(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取文件所属文件夹路径
     * @param file 文件
     * @return 文件夹目录
     */
    public static String getDirName(File file){
        if(null == file) return null;
        return getDirName(file.getPath());
    }


    /**
     * 获取文件所属文件夹路径
     * @param filePath 文件路径
     * @return 文件夹目录
     */
    public static String getDirName(String filePath){
        if(KStringUtils.isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? "" : filePath.substring(0,lastSep + 1);
    }

    /**
     * 获取文件名
     * @param file 文件
     * @return 文件夹目录
     */
    public static String getFileName(File file){
        if(null == file) return null;
        return getFileName(file.getPath());
    }


    /**
     * 获取文件名
     * @param filePath 文件路径
     * @return 文件夹目录
     */
    public static String getFileName(String filePath){
        if(KStringUtils.isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }



    /**
     * 获取文件名(不包含后缀)
     * @param file 文件
     * @return 文件名
     */
    public static String getFileNameNoExtension(File file){
        if(null == file) return null;
        return getFileNameNoExtension(file.getPath());
    }


    /**
     * 获取文件名(不包含后缀)
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileNameNoExtension(String filePath){
        if(KStringUtils.isSpace(filePath)) return filePath;
        String fileAllName = getFileName(filePath);
        int lastStep = fileAllName.lastIndexOf(".");
        return lastStep == -1 ? fileAllName : fileAllName.substring(0,lastStep + 1);
    }

    /**
     * 获取文件后缀名
     * @param file 文件
     * @return 文件后缀名
     */
    public static String getFileExtension(File file){
        if(null == file) return null;
        return getFileExtension(file.getPath());
    }

    /**
     * 获取文件后缀名
     * @param filePath 文件路径
     * @return 文件后缀名
     */
    public static String getFileExtension(String filePath){
        if(KStringUtils.isSpace(filePath)) return filePath;
        int lastStep = filePath.lastIndexOf(".");
        return lastStep == -1 ? filePath : filePath.substring(lastStep + 1);
    }

    public static void closeIO(Closeable... closeables){
        if(null == closeables) return;
        for (Closeable closeable:closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
