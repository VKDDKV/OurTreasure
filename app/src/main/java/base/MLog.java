package base;

import android.util.Log;

import com.mannermakethman.vkd.ourtreasure.BuildConfig;

import java.text.DecimalFormat;

/**
 * Created by nguyentviet3513 on 12/17/2017.
 */

public class MLog {
    /**
     * ログ出力ターゲット：なし / no logging.
     */
    public static final int LOG_KIND_NONE = 0;
    /**
     * ログ出力ターゲット：logcatのみ / logcat.
     */
    public static final int LOG_KIND_LOGCAT = 1;

    /**
     * ログ出力種別 / Default logging type.
     */
    private static int mLogKind = BuildConfig.mLogKind;

    /**
     * ヒープを出力するかどうか / The flag which decide outputting the heap.
     */
    private static boolean dispHeap = false;

    /**
     * ログタグ / The tag into the log.
     */
    private static String logTag = "ALCO";

    /**
     * HEAP出力用フォーマット / The formatter for heap.
     */
    private static final DecimalFormat df = new DecimalFormat("[heap:00,000kB]");
    private static final DecimalFormat sdf = new DecimalFormat("0000000");

    private static final int MAX_CHECK_TIME = 16;
    private static final long startTime[] = new long[MAX_CHECK_TIME];

    static {
        for (int i = 0; i < MAX_CHECK_TIME; i++) {
            startTime[i] = -1;
        }
    }

    /**
     * デバッグレベルログ出力
     * The Logger for DEBUG.
     *
     * @param format 文字フォーマット / The format of string.
     * @param args   パラメタ / arguments
     */
    public final static void d(final String format, final Object... args) {
        writeLog(Log.DEBUG, format, null, args);
    }

    /**
     * INFOレベルログ出力
     * The Logger for INFO.
     *
     * @param format 文字フォーマット / The format of string.
     * @param args   パラメタ / arguments.
     */
    public final static void i(final String format, final Object... args) {
        writeLog(Log.INFO, format, null, args);
    }

    /**
     * INFOレベルログ出力
     * The Logger for INFO.
     *
     * @param format 文字フォーマット / The format of string.
     * @param tr     例外。不要な場合は null を指定 / Exception.
     * @param args   パラメタ / arguments.
     */
    public final static void i(final String format, final Throwable tr, final Object... args) {
        writeLog(Log.INFO, format, tr, args);
    }

    /**
     * WARNレベルログ出力
     * The Logger for WARN.
     *
     * @param format 文字フォーマット / The format of string.
     * @param args   パラメタ / arguments.
     */
    public final static void w(final String format, final Object... args) {
        writeLog(Log.WARN, format, null, args);
    }

    /**
     * WARNレベルログ出力
     * The Logger for WARN.
     *
     * @param format 文字フォーマット / The format of string.
     * @param tr     例外。不要な場合は null を指定 / Exception.
     * @param args   パラメタ / arguments.
     */
    public final static void w(final String format, final Throwable tr, final Object... args) {
        writeLog(Log.WARN, format, tr, args);
    }

    /**
     * ERRORレベルログ出力
     * The Logger for ERROR.
     *
     * @param format 文字フォーマット / The format of string.
     * @param args   パラメタ / arguments.
     */
    public final static void e(final String format, final Object... args) {
        writeLog(Log.ERROR, format, null, args);
    }

    /**
     * ERRORレベルログ出力
     * The Logger for ERROR.
     *
     * @param format 文字フォーマット / The format of string.
     * @param tr     例外。不要な場合は null を指定 / Exception.
     * @param args   パラメタ / arguments.
     */
    public final static void e(final String format, final Throwable tr, final Object... args) {
        writeLog(Log.ERROR, format, tr, args);
    }

    /**
     * ログ種別を設定する。
     * The setter for the logging type.
     *
     * @param logKind ログ種別 / the logging type.
     */
    public final static void setLogKind(final int logKind) {
        mLogKind = logKind;
    }

    /**
     * ヒープを出力するかどうかを設定する。
     * The setter for outputting the heap.
     *
     * @param dispHeap 出力するならtrueを設定する。 / This is the flag which decide for outputting or not.
     */
    public final static void setDispHeap(final boolean dispHeap) {
        MLog.dispHeap = dispHeap;
    }

    public final static void setTag(final String tag) {
        MLog.logTag = tag;
    }

    /**
     * 処理時間を測定する。<br>
     * 処理時間測定を行う開始時間を設定し、ログに所定のメッセージ+msgを出力する。
     * Logging the measurement start with the prescribed message.
     *
     * @param msg メッセージ / message.
     */
    public final static void iStart(final String msg) {
        if (mLogKind == LOG_KIND_NONE) {
            return;
        }
        for (int i = 0; i < MAX_CHECK_TIME; i++) {
            if (startTime[i] == -1) {
                startTime[i] = System.currentTimeMillis();
                break;
            }
        }
        final String headStr = "==start checkTime             == ";
        i(headStr + msg);
    }

    /**
     * 処理時間を測定する。<br>
     * iStartで設定した開始時間からiEndまでの時間とmsgをログに出力する。
     * Logging the measurement time through to do the iStart method with the prescribed message.
     *
     * @param msg メッセージ / message.
     */
    public final static void iEnd(final String msg) {
        if (mLogKind == LOG_KIND_NONE) {
            return;
        }
        long interval = 0;
        for (int i = MAX_CHECK_TIME - 1; i >= 0; i--) {
            if (startTime[i] != -1) {
                interval = System.currentTimeMillis() - startTime[i];
                startTime[i] = -1;
                break;
            }
        }

        final String headStr = "==end   checkTime " + sdf.format(interval) + "(ms) == ";
        i(headStr + msg);
    }

    /**
     * ログ出力メソッド。
     * The method for logging.
     *
     * @param priority プライオリティ / priority.
     * @param format   フォーマット / format.
     * @param tr       例外 / exception.
     * @param args     パラメタ / arguments.
     */
    private final static void writeLog(final int priority, final String format, final Throwable tr, final Object... args) {
        if (mLogKind == LOG_KIND_NONE) {
            return;
        }
        try {
            String msg = null;
            if (args == null || args.length == 0) {
                msg = format;
            } else {
                msg = String.format(format, args);
            }

            if (dispHeap == true) {
                final long heap = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                msg = df.format(heap / 1024) + msg;
            }

            if (tr != null) {
                msg += "\n" + Log.getStackTraceString(tr);
            }

            if ((mLogKind & LOG_KIND_LOGCAT) != 0) {
                Log.println(priority, logTag, msg);
            }

        } catch (final Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}

