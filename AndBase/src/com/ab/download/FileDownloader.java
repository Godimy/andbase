/*
 * 
 */
package com.ab.download;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.ab.db.DownFileDao;
import com.ab.global.AbAppData;
import com.ab.model.DownFile;
import com.ab.util.AbFileUtil;

// TODO: Auto-generated Javadoc
/**
 * 描述：多线程支持断点续传下载器.
 *
 * @author zhaoqp
 * @date：2013-3-14 下午5:00:52
 * @version v1.0
 */
public class FileDownloader {

	/** The Constant TAG. */
	private static final String TAG = "FileDownloader";
	
	/** The Constant D. */
	private static final boolean D = AbAppData.DEBUG;
	
	/** The context. */
	private Context context;
	
	/** The threads. */
	private DownloadThread threads;
	
	/** The m down file dao. */
	private DownFileDao mDownFileDao;
	
	/** 本地保存文件. */
	private File saveFile;
	
	/** The m down file. */
	private DownFile mDownFile = null;
	
	/** The m thread num. */
	private int mThreadNum = 1;
	
	/** The flag. */
	private boolean flag = true;
	
	/**
	 * 更新指定线程最后下载的位置.
	 *
	 * @param downFile the down file
	 */
	protected synchronized void update(DownFile downFile) {
		this.mDownFileDao.update(downFile);
	}

	/**
	 * 构建文件下载器.
	 *
	 * @param context the context
	 * @param downFile the down file
	 * @param threadNum 下载线程数
	 */
	public FileDownloader(Context context,DownFile downFile,int threadNum) {
		try {
			this.context = context;
			this.mDownFile = downFile;
			this.mThreadNum = threadNum;
			mDownFileDao = new DownFileDao(context);
			// 构建保存文件
			saveFile = new File(Environment.getExternalStorageDirectory().getPath()+File.separator+AbFileUtil.getDownPathFileDir()+AbFileUtil.getFileNameFromUrl(mDownFile.getDownUrl()));
			if (!saveFile.getParentFile().exists()){
				saveFile.getParentFile().mkdirs();
			}
			if (!saveFile.exists()){
				saveFile.createNewFile();
				//删除原来的下载数据
				mDownFileDao.delete(downFile.getDownUrl());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开始下载文件.
	 *
	 * @param listener 监听下载数量的变化,如果不需要了解实时下载的数量,可以设置为null
	 * @return 已下载文件大小
	 * @throws Exception the exception
	 */
	public void download(DownloadProgressListener listener) throws Exception {
		try {
			this.threads = new DownloadThread(this,mDownFile,saveFile);
			this.threads.setPriority(7);
			this.threads.start();
			this.mDownFileDao.save(mDownFile);
			// 循环判断所有线程是否完成下载
			while (flag && mDownFile.getDownLength() <= mDownFile.getTotalLength()) {
				Thread.sleep(2000);
				// 如果下载失败,再重新下载
				if (mDownFile.getDownLength() == -1) {
					//下载失败
					return;
				}
				
				// 没间隔几秒通知目前已经下载完成的数据长度
				if (listener != null){
					listener.onDownloadSize(mDownFile.getDownLength());
				}
				if(mDownFile.getDownLength() == mDownFile.getTotalLength()){
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Http响应头字段.
	 *
	 * @param http the http
	 * @return the http response header
	 */
	public static Map<String, String> getHttpResponseHeader(HttpURLConnection http) {
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0;; i++) {
			String mine = http.getHeaderField(i);
			if (mine == null)
				break;
			header.put(http.getHeaderFieldKey(i), mine);
		}
		return header;
	}

	/**
	 * 打印Http头字段.
	 *
	 * @param http the http
	 */
	public static void printResponseHeader(HttpURLConnection http) {
		Map<String, String> header = getHttpResponseHeader(http);
		for (Map.Entry<String, String> entry : header.entrySet()) {
			String key = entry.getKey() != null ? entry.getKey() + ":" : "";
			Log.i(TAG, key + entry.getValue());
		}
	}

	/**
	 * Gets the threads.
	 *
	 * @return the threads
	 */
	public DownloadThread getThreads() {
		return threads;
	}

	/**
	 * Gets the save file.
	 *
	 * @return the save file
	 */
	public File getSaveFile() {
		return saveFile;
	}

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
