package com.kingwin.utils;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * @author KingWin
 * @since 2021/11/5 4:10 下午
 */

public class KPathUtils {

    private static final char SEP = File.separatorChar;
    
    /**
     * Join the path.
     *
     * @param parent The parent of path.
     * @param child  The child path.
     * @return the path
     */
    public static String join(String parent, String child) {
        if (TextUtils.isEmpty(child)) return parent;
        if (parent == null) {
            parent = "";
        }
        int len = parent.length();
        String legalSegment = getLegalSegment(child);
        String newPath;
        if (len == 0) {
            newPath = SEP + legalSegment;
        } else if (parent.charAt(len - 1) == SEP) {
            newPath = parent + legalSegment;
        } else {
            newPath = parent + SEP + legalSegment;
        }
        return newPath;
    }

    private static String getLegalSegment(String segment) {
        int st = -1, end = -1;
        char[] charArray = segment.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c != SEP) {
                if (st == -1) {
                    st = i;
                }
                end = i;
            }
        }
        if (st >= 0 && end >= st) {
            return segment.substring(st, end + 1);
        }
        throw new IllegalArgumentException("segment of <" + segment + "> is illegal");
    }

    /**
     * Return the path of /system.
     *
     * @return the path of /system
     */
    public static String getRootPath() {
        return getAbsolutePath(Environment.getRootDirectory());
    }

    /**
     * Return the path of /data.
     *
     * @return the path of /data
     */
    public static String getDataPath() {
        return getAbsolutePath(Environment.getDataDirectory());
    }

    /**
     * Return the path of /cache.
     *
     * @return the path of /cache
     */
    public static String getDownloadCachePath() {
        return getAbsolutePath(Environment.getDownloadCacheDirectory());
    }

    /**
     * Return the path of /data/data/package.
     *
     * @return the path of /data/data/package
     */
    public static String getInternalAppDataPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return KContextManage.getInstance().getApp().getApplicationInfo().dataDir;
        }
        return getAbsolutePath(KContextManage.getInstance().getApp().getDataDir());
    }

    /**
     * Return the path of /data/data/package/code_cache.
     *
     * @return the path of /data/data/package/code_cache
     */
    public static String getInternalAppCodeCacheDir() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return KContextManage.getInstance().getApp().getApplicationInfo().dataDir + "/code_cache";
        }
        return getAbsolutePath(KContextManage.getInstance().getApp().getCodeCacheDir());
    }

    /**
     * Return the path of /data/data/package/cache.
     *
     * @return the path of /data/data/package/cache
     */
    public static String getInternalAppCachePath() {
        return getAbsolutePath(KContextManage.getInstance().getApp().getCacheDir());
    }

    /**
     * Return the path of /data/data/package/databases.
     *
     * @return the path of /data/data/package/databases
     */
    public static String getInternalAppDbsPath() {
        return KContextManage.getInstance().getApp().getApplicationInfo().dataDir + "/databases";
    }

    /**
     * Return the path of /data/data/package/databases/name.
     *
     * @param name The name of database.
     * @return the path of /data/data/package/databases/name
     */
    public static String getInternalAppDbPath(String name) {
        return getAbsolutePath(KContextManage.getInstance().getApp().getDatabasePath(name));
    }

    /**
     * Return the path of /data/data/package/files.
     *
     * @return the path of /data/data/package/files
     */
    public static String getInternalAppFilesPath() {
        return getAbsolutePath(KContextManage.getInstance().getApp().getFilesDir());
    }

    /**
     * Return the path of /data/data/package/shared_prefs.
     *
     * @return the path of /data/data/package/shared_prefs
     */
    public static String getInternalAppSpPath() {
        return KContextManage.getInstance().getApp().getApplicationInfo().dataDir + "/shared_prefs";
    }

    /**
     * Return the path of /data/data/package/no_backup.
     *
     * @return the path of /data/data/package/no_backup
     */
    public static String getInternalAppNoBackupFilesPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return KContextManage.getInstance().getApp().getApplicationInfo().dataDir + "/no_backup";
        }
        return getAbsolutePath(KContextManage.getInstance().getApp().getNoBackupFilesDir());
    }

    /**
     * Return the path of /storage/emulated/0.
     *
     * @return the path of /storage/emulated/0
     */
    public static String getExternalStoragePath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStorageDirectory());
    }

    /**
     * Return the path of /storage/emulated/0/Music.
     *
     * @return the path of /storage/emulated/0/Music
     */
    public static String getExternalMusicPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
    }

    /**
     * Return the path of /storage/emulated/0/Podcasts.
     *
     * @return the path of /storage/emulated/0/Podcasts
     */
    public static String getExternalPodcastsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
    }

    /**
     * Return the path of /storage/emulated/0/Ringtones.
     *
     * @return the path of /storage/emulated/0/Ringtones
     */
    public static String getExternalRingtonesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    /**
     * Return the path of /storage/emulated/0/Alarms.
     *
     * @return the path of /storage/emulated/0/Alarms
     */
    public static String getExternalAlarmsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
    }

    /**
     * Return the path of /storage/emulated/0/Notifications.
     *
     * @return the path of /storage/emulated/0/Notifications
     */
    public static String getExternalNotificationsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /**
     * Return the path of /storage/emulated/0/Pictures.
     *
     * @return the path of /storage/emulated/0/Pictures
     */
    public static String getExternalPicturesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    /**
     * Return the path of /storage/emulated/0/Movies.
     *
     * @return the path of /storage/emulated/0/Movies
     */
    public static String getExternalMoviesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
    }

    /**
     * Return the path of /storage/emulated/0/Download.
     *
     * @return the path of /storage/emulated/0/Download
     */
    public static String getExternalDownloadsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    /**
     * Return the path of /storage/emulated/0/DCIM.
     *
     * @return the path of /storage/emulated/0/DCIM
     */
    public static String getExternalDcimPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    /**
     * Return the path of /storage/emulated/0/Documents.
     *
     * @return the path of /storage/emulated/0/Documents
     */
    public static String getExternalDocumentsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getAbsolutePath(Environment.getExternalStorageDirectory()) + "/Documents";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package.
     *
     * @return the path of /storage/emulated/0/Android/data/package
     */
    public static String getExternalAppDataPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        File externalCacheDir = KContextManage.getInstance().getApp().getExternalCacheDir();
        if (externalCacheDir == null) return "";
        return getAbsolutePath(externalCacheDir.getParentFile());
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/cache.
     *
     * @return the path of /storage/emulated/0/Android/data/package/cache
     */
    public static String getExternalAppCachePath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalCacheDir());
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files
     */
    public static String getExternalAppFilesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(null));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Music.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Music
     */
    public static String getExternalAppMusicPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Podcasts.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Podcasts
     */
    public static String getExternalAppPodcastsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Ringtones.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Ringtones
     */
    public static String getExternalAppRingtonesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Alarms.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Alarms
     */
    public static String getExternalAppAlarmsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_ALARMS));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Notifications.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Notifications
     */
    public static String getExternalAppNotificationsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Pictures.
     *
     * @return path of /storage/emulated/0/Android/data/package/files/Pictures
     */
    public static String getExternalAppPicturesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Movies.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Movies
     */
    public static String getExternalAppMoviesPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Download.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Download
     */
    public static String getExternalAppDownloadPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/DCIM.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/DCIM
     */
    public static String getExternalAppDcimPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM));
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Documents.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Documents
     */
    public static String getExternalAppDocumentsPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(null)) + "/Documents";
        }
        return getAbsolutePath(KContextManage.getInstance().getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
    }

    /**
     * Return the path of /storage/emulated/0/Android/obb/package.
     *
     * @return the path of /storage/emulated/0/Android/obb/package
     */
    public static String getExternalAppObbPath() {
        if (!isSDCardEnableByEnvironment()) return "";
        return getAbsolutePath(KContextManage.getInstance().getApp().getObbDir());
    }

    public static String getRootPathExternalFirst() {
        String rootPath = getExternalStoragePath();
        if (TextUtils.isEmpty(rootPath)) {
            rootPath = getRootPath();
        }
        return rootPath;
    }

    public static String getAppDataPathExternalFirst() {
        String appDataPath = getExternalAppDataPath();
        if (TextUtils.isEmpty(appDataPath)) {
            appDataPath = getInternalAppDataPath();
        }
        return appDataPath;
    }

    public static String getFilesPathExternalFirst() {
        String filePath = getExternalAppFilesPath();
        if (TextUtils.isEmpty(filePath)) {
            filePath = getInternalAppFilesPath();
        }
        return filePath;
    }

    public static String getCachePathExternalFirst() {
        String appPath = getExternalAppCachePath();
        if (TextUtils.isEmpty(appPath)) {
            appPath = getInternalAppCachePath();
        }
        return appPath;
    }

    private static String getAbsolutePath(final File file) {
        if (file == null) return "";
        return file.getAbsolutePath();
    }

    /**
     * Return whether sdcard is enabled by environment.
     *
     * @return {@code true}: enabled<br>{@code false}: disabled
     */
    public static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
