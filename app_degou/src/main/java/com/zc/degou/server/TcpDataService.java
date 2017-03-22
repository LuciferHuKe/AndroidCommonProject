package com.zc.degou.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.zc.degou.server.base.GuoConstants;
import com.zc.degou.server.base.RequestData;
import com.zc.degou.server.base.ServerData;
import com.zc.degou.server.tool.ByteUtil;
import com.zc.degou.server.tool.LogUtil;
import com.zc.degou.server.tool.encapsulation.Encapsulation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @ClassName: TcpDataService
 * @Description: TODO(在首界面打开的时候，会开启这个服务，主要是向后台要数据的， 在首界面退出的时候应该将该服务关闭)
 * @author jhjiang@inforace.com.cn 蒋金豪
 * @date 2011-10-28 上午09:47:19
 * 
 */
public class TcpDataService extends Service implements GuoConstants {

	/* 用来接收从应用程序发送的广播请求 */
	private BroadcastReceiver receiver;
	/* 请求等待队列 */
	private Vector<RequestData> requestList = null;
	/* 请求等待队列 */
	private Vector<RequestData> processList = null;
	/* 请求的内容 */
	// private RequestData requestData = null;
	/* 服务线程的开关 */
	private boolean switchFlag = true;

	/* 系统属性保存 */
	private SharedPreferences systemPreference;
	// 定义处理编码和解码的字符集
	private Charset charset = Charset.forName("UTF-8");
	private Charset charset2 = Charset.forName("ASCII");
	// 定义检测SocketChannel的Selector对象
	private Selector selector = null;
	// 服务器地址

	Thread Server_Work_Thread = null;

	ClientThread nioClient = null;

	/* 从后台获得数据后，封装成手机通讯用的数据 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		LogUtil.info(this, "tcp服务在开启中。。。");

		// 初始化 请求列表
		requestList = new Vector<RequestData>();
		processList = new Vector<RequestData>();
		// 获得本应用本地数据库的参数属性，在本class中需要获取的是用户存在本地的用户名和密码
		systemPreference = PreferenceManager.getDefaultSharedPreferences(this);

		// 注册广播 监听手机应用发过来的数据请求
		LogUtil.debug(this, "注册广播");
		receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// 判断监听到的广播是不是数据请求
				if (intent.getAction().equals(SERVER_REQUEST)) {
					// 获得请求数据的参数
					RequestData rd = (RequestData) intent.getExtras().getSerializable(SERVER_REQUEST_FLAG);
					// 如果请求参数不是空，则将给请求加入到等待队列
					if (rd != null) {
						LogUtil.debug(TcpDataService.this, "将请求加入等待队列");
						requestList.add(rd);
					} else {
						LogUtil.debug(TcpDataService.this, "请求的数据是空！");
					}
				}
			}
		};
		// 注册广播并添加相应的过滤
		IntentFilter filter = new IntentFilter();
		filter.addAction(SERVER_REQUEST);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(receiver, filter);
		LogUtil.info(this, "tcp服务器创建结束");
	}

	@Override
	public void onDestroy() {
		// Log.v("LOOP", "TcpDataService onDestroy");
		unregisterReceiver(receiver);
		switchFlag = false;
		super.onDestroy();
		// 界面结束时关闭服务 注销广播监听
	}

	private class MyWorkThead implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			SocketChannel sc = null;
			while (switchFlag) {
				// 如果请求队列是空，则继续等待
				if (requestList.isEmpty()) {
					try {
						Thread.sleep(MOBIL_SERVICE_TCP_WAITTIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				LogUtil.debug(TcpDataService.this, "数据开始处理");
				try {
					RequestData requestDataTemp = null;
					try {
						requestDataTemp = requestList.firstElement();
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					if (requestDataTemp == null) {
						continue;
					}
					final RequestData requestData = requestDataTemp;
					int tempType = requestData.getType();
					LogUtil.info(TcpDataService.this, "TCP REMOVE RD");
					if (!requestList.remove(requestData)) {
						continue;
					}
					processList.add(requestData);
					if (MOBILE_REQUEST_TYPE_DEFAULT == tempType) {
						try {
							if (selector == null) {
								selector = Selector.open();
							}
						} catch (Exception e) {
							e.printStackTrace();
							selector.close();
							selector = Selector.open();
						}
						long a = 0;
						try {
							InetSocketAddress sa = new InetSocketAddress(PCSERVER_IP, PCSERVER_PORT);
							sc = SocketChannel.open(sa);
						} catch (Exception e) {
							retry(requestData);
							long b = System.currentTimeMillis();
							LogUtil.info(TcpDataService.this, "TCP RD RETRY R2 " + (b - a));
							myRestart();
						}
						LogUtil.info(TcpDataService.this, "TCP SC CONFIG");
						// 设置该sc以非阻塞方式工作
						sc.configureBlocking(false);
						// 将SocketChannel对象注册到指定Selector
						selector.wakeup();
						SelectionKey sk = sc.register(selector, SelectionKey.OP_READ);
						requestData.setSelectionKey(sk.toString());
						LogUtil.info(TcpDataService.this, "TCP SelectionKey:" + sk);
						// 清空已经开始操作的请求
						LogUtil.info(TcpDataService.this, "TCP ST DATA");
						// 获得请求中的参数
						HashMap<String, String> paras = requestData.getParas();
						// 获得当前的用户信息
						String userName = systemPreference.getString(MOBIL_USER_NAME, MOBIL_USER_NAME_DEFAULT);
						String userPassword = systemPreference.getString(MOBIL_USER_PASSWORD,
								MOBIL_USER_PASSWORD_DEFAULT);
						// 在这里获取数据请求
						StringBuffer sb = new StringBuffer();

						// 添加请求类型
						sb.append("00001");

						// 添加数据库的类型
						sb.append("00");

						// 添加用户名
						sb.append(userName);
						for (int i = (50 - userName.length()); i > 0; i--) {
							sb.append(" ");
						}

						// 添加用户密码
						sb.append(userPassword);
						for (int i = (50 - userPassword.length()); i > 0; i--) {
							sb.append(" ");
						}

						// 添加请求的业务处理类的名称
						String className = requestData.getClassName();
						LogUtil.info(TcpDataService.this, "TCP CLASSNAME:" + className);
						if (className == null || className.length() != BUSINESS_CLASSNAME_DIGIT_LENGTH) {
							LogUtil.info(TcpDataService.this, "TCP CLASSNAME ERROR:" + className);
							continue;
						}
						sb.append(className);

						// 添加参数
						// 向后台请求数据需要的参数
						LogUtil.debug(TcpDataService.this, "TCP PARA：");
						if (paras != null) {
							Set<String> keys = paras.keySet();
							for (String key : keys) {
								LogUtil.debug(TcpDataService.this, key + "\t--->" + paras.get(key));
								sb.append(key + PARA_EQUALS + paras.get(key));
								sb.append(PARA_SEPERATOR);
							}
						}

						sb.append(MAP_SEPERATOR);
						sb.append(SOCKET_END_FLAG);

						byte[] tempb = Encapsulation.encapsulate(sb.toString());
						LogUtil.error(TcpDataService.this, "业务请求参数为:" + sb.toString());
						// 数据开始标志
						if (nioClient == null || !nioClient.isAlive()) {
							nioClient = new ClientThread();
							nioClient.start();
						}
						LogUtil.debug(TcpDataService.this, "TCP ST START");
						sc.write(ByteBuffer.wrap(SOCKET_START_FLAG.getBytes()));
						// 数据长度
						sc.write(ByteBuffer.wrap(ByteUtil.integerToBytes(tempb.length, 4)));
						// 加密数据
						sc.write(ByteBuffer.wrap(tempb));
					}
					// 上传图片的标志
					else if (MOBILE_REQUEST_TYPE_PICUPLOAD == tempType) {

						Socket socket = new Socket(PCSERVER_IP, PCSERVER_PORT);
						BufferedInputStream is = new BufferedInputStream(socket.getInputStream());
						BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());

						// 开启接受数据的线程
						new Thread(new getServerDataTask(socket, is, os, requestData.getFlag(), requestData)).start();

						File tempFile = requestData.getPicLocation();
						int size = (int) tempFile.length();
						StringBuffer strSize = new StringBuffer();
						strSize.append("" + size);
						for (int i = (50 - strSize.length()); i > 0; i--) {
							strSize.append(" ");
						}
						if (tempFile.exists()) {
							FileInputStream fis = new FileInputStream(tempFile);
							os.write(SOCKET_START_FLAG_PICTURE_UPLOAD.getBytes("UTF8"));
							os.write(BUSINESS_DB_TYPE_DEFAULT.getBytes("UTF8"));
							os.write(ByteUtil.integerToBytes(size, 4));
							byte[] buffer = new byte[size];
							int readLength;
							while ((readLength = fis.read(buffer)) > 0) {
								os.write(buffer, 0, readLength);
							}
							os.flush();
						}
					}
				} catch (Exception e) {
					LogUtil.error(TcpDataService.this, "TCP ST ERROR:");
					e.printStackTrace();
					LogUtil.error(TcpDataService.this, "TCP ST THREAD RESTART:" + Thread.currentThread().getId());
					try {
						if (sc != null) {
							LogUtil.error(TcpDataService.this, "TCP ST SC CLOSE");
							sc.socket().close();
							try {
								sc.socket().getOutputStream().write("test".getBytes());
							} catch (Exception e1) {
								LogUtil.error(TcpDataService.this, "TCP ST SC CLOSE ERROR");
								e1.printStackTrace();
							}
							sc.close();
						}
						if (selector != null) {
							LogUtil.error(TcpDataService.this, "TCP ST SELECTOR CLOSE");
							selector.close();
							selector = null;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e2) {
						// TODO: handle exception
						LogUtil.error(TcpDataService.this, "TCP SOCKET CLOSE " + e2.getMessage());
					}
					LogUtil.error(TcpDataService.this, "TCP ST RESTART 1");
					myRestart();
					continue;
				}
			}
		}

		private void retry(RequestData requestData) {
			int countRetry = requestData.getRetry();
			long timeout = requestData.getTimeout();
			long currentTime = System.currentTimeMillis();
			boolean idRetry = (timeout - currentTime) > 0 ? true : false;
			if (countRetry-- > 0 && idRetry) {
				requestData.setRetry(countRetry);
				requestList.add(requestData);
			} else {
				Intent errorIntent = new Intent();
				errorIntent.putExtra("isError", true);
				errorIntent.setAction(requestData.getActionName());
				errorIntent.putExtra("flag", requestData.getFlag());
				sendBroadcast(errorIntent);
			}
		}
	}

	public void myRestart() {
		try {
			LogUtil.error(TcpDataService.this, "TCP RESTART");
			Server_Work_Thread.interrupt();
			Server_Work_Thread = null;
			Server_Work_Thread = new Thread(new MyWorkThead());
			Server_Work_Thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Server_Work_Thread = new Thread(new MyWorkThead());
		Server_Work_Thread.start();
		LogUtil.info(TcpDataService.this, "TCP START");
	}

	public void returnErrorMessage() {
		LogUtil.info(TcpDataService.this, "TCP RETURN ERROR MESSAGE");
	}

	// 定义读取服务器数据的线程
	private class ClientThread extends Thread {
		private boolean switchCase = true;

		public ClientThread() {
		}

		public void run() {
			LogUtil.info(TcpDataService.this, "TCP CT RUN");
			int countRetry = 0;
			long timeout = 0;
			RequestData rdTemp = null;
			switchCase = true;
			try {
				// 从后台数据库返回的数据
				while (switchCase) {
					if (selector.select(1) < 1) {
						Thread.sleep(100);
						continue;
					}
					RequestData requestData = null;
					// 遍历每个有可用IO操作Channel对应的SelectionKey
					// List<SelectionKey> skTemp = new
					// ArrayList<SelectionKey>();
					for (SelectionKey sk : selector.selectedKeys()) {
						LogUtil.info(TcpDataService.this, "TCP CT GET KEY:" + sk);
						for (int i = 0; i < processList.size(); i++) {
							if (processList.get(i).getSelectionKey().equalsIgnoreCase(sk.toString())) {
								requestData = processList.get(i);
								break;
							}
						}
						if (processList == null || requestData == null) {
							continue;
						}
						ServerData returnData = new ServerData();
						processList.remove(requestData);
						returnData.setFlag(requestData.getFlag());
						rdTemp = requestData;
						countRetry = requestData.getRetry();
						timeout = requestData.getTimeout();
						// 删除正在处理的SelectionKey
						// selector.selectedKeys().remove(sk);
						// skTemp.add(sk);

						// 如果该SelectionKey对应的Channel中有可读的数据
						if (!sk.isReadable()) {
							continue;
						}
						selector.selectedKeys().remove(sk);
						// // 使用NIO读取Channel中的数据
						LogUtil.info(TcpDataService.this, "TCP CT GET CHANNEL");
						SocketChannel sc = (SocketChannel) sk.channel();
						int temp = 0;

						// 读取后台传输过来的开始标志
						// 读取到的标志位的数据长度不对，则结束接受数据的进程
						ByteBuffer buff = ByteBuffer.allocate(MOBIL_SOCKET_START_FLAG_DIGIT_LENGTH);
						temp = sc.read(buff);
						String flag = null;
						if (temp != MOBIL_SOCKET_START_FLAG_DIGIT_LENGTH) {
							sc.close();
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 1");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 1");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						buff.flip();
						flag = "" + charset.decode(buff);

						LogUtil.info(TcpDataService.this, "TCP CT ST READ");
						// 如果是结束的标志，则结束接受数据的进程
						if (MOBIL_SOCKET_END_FLAG.equals(flag)) {
							LogUtil.info(TcpDataService.this, "TCP CT OV FLAG");
							sc.close();
							sc = null;
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 2");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 2");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						// 如果不是开始的标志，也结束接受数据的进程
						else if (!MOBIL_SOCKET_START_FLAG.equals(flag)) {
							sc.close();
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 3");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 3");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						LogUtil.info(TcpDataService.this, "TCP CT TYPE READ");
						// 获得后台传输过来的数据长度
						buff = ByteBuffer.allocate(MOBIL_DATA_TYPE_DIGIT_LENGTH);
						temp = sc.read(buff);
						String type = null;
						if (temp == MOBIL_SOCKET_START_FLAG_DIGIT_LENGTH) {
							buff.flip();
							type = "" + charset.decode(buff);
						} else {
							sc.close();
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 4");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 4");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						LogUtil.info(TcpDataService.this, "TCP CT TYPE READED END:" + type);

						// 判断数据类型
						if (MOBIL_DATA_TYPE_01.equals(type)) {
							LogUtil.info(TcpDataService.this, "TCP CT TYPE 01");
						} else if (MOBIL_DATA_TYPE_02.equals(type)) {
							LogUtil.info(TcpDataService.this, "TCP CT TYPE 02");
						} else if ("10".equalsIgnoreCase(type)) {
							LogUtil.info(TcpDataService.this, "TCP CT TYPE 10 ERROR");

							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 5");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 5");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						} else {
							LogUtil.info(TcpDataService.this, "TCP CT TYPE 11 ERROR");
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 6");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 6");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						LogUtil.info(TcpDataService.this, "TCP CT DATA READ");
						// 获得数据
						buff = ByteBuffer.allocate(MOBIL_SOCKET_LENGTH_DIGIT_LENGTH);
						temp = sc.read(buff);
						String tempLength = null;
						if (temp == MOBIL_SOCKET_LENGTH_DIGIT_LENGTH) {
							buff.flip();
							tempLength = "" + charset2.decode(buff);
							LogUtil.info(TcpDataService.this, "TCP CT LENGTH READ:" + tempLength);
						} else {
							sc.close();
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 7");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 7");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						int length = ByteUtil.bytesToInteger(buff.array());
						LogUtil.info(TcpDataService.this, "TCP CT LENGTH READ2:" + length);
						if (length > 1024 * 1024 || length < 0) {
							LogUtil.debug(TcpDataService.this, "TCP CT LENGTH TOO LONG");
							throw new Exception();
						}
						buff.clear();
						buff = ByteBuffer.allocate(length);
						ByteBuffer buffTemp = ByteBuffer.allocate(length);
						temp = sc.read(buffTemp);
						buffTemp.flip();
						buff.put(buffTemp);
						LogUtil.info(TcpDataService.this, "TCP CT DATA READ:" + temp + " :0");
						int intRun = 0;

						while (temp < length && intRun < (length / 10)) {
							Thread.sleep(50);
							intRun++;
							buffTemp.clear();
							int intLen = 0;
							buffTemp = ByteBuffer.allocate(length - temp);
							intLen = sc.read(buffTemp);
							if (intLen > 0) {
								buffTemp.flip();
								buff.put(buffTemp);
								temp += intLen;
								LogUtil.info(TcpDataService.this, "TCP CT DATA READ :" + temp + " :" + intRun);
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT DATA READ  END:" + temp + " :" + intRun);
							}
						}
						buff.flip();
						String data = "" + charset.decode(buff);
						LogUtil.info(TcpDataService.this, "TCP CT DATA TOTAL LENGTH:" + temp + " :" + intRun);
						if (temp < length) {
							sc.close();
							long currentTime = System.currentTimeMillis();
							boolean idRetry = (timeout - currentTime) > 0 ? true : false;
							if (countRetry-- > 0 && idRetry) {
								requestData.setRetry(countRetry);
								requestList.add(requestData);
								LogUtil.info(TcpDataService.this, "TCP CT RESTART 8");
							} else {
								LogUtil.info(TcpDataService.this, "TCP CT RETRY END 8");
								Intent errorIntent = new Intent();
								errorIntent.putExtra("isError", true);
								errorIntent.setAction(requestData.getActionName());
								errorIntent.putExtra("flag", requestData.getFlag());
								sendBroadcast(errorIntent);
							}
							continue;
						}
						sc.close();
						// 对数据进行解密
						data = Encapsulation.antiEncapsulation(data.getBytes());

						// 分析数据，并将数据封装
						LogUtil.info(TcpDataService.this, "TCP CT ENDCAPSULATION END");
						String[] allData = data.split(MAP_SEPERATOR);
						for (int i = 1; i <= allData.length; i++) {
							String tempMap = allData[i - 1];
							HashMap<String, String> dataMap = new HashMap<String, String>();
							if (tempMap == null || "".equalsIgnoreCase(tempMap.trim())) {
								returnData.addData(dataMap);
								continue;
							}
							StringTokenizer stMap = new StringTokenizer(tempMap, PARA_SEPERATOR);
							while (stMap.hasMoreTokens()) {
								String tempValue = stMap.nextToken();
								if (tempValue == null || "".equalsIgnoreCase(tempValue.trim())) {
									continue;
								}
								if (tempValue.indexOf(PARA_EQUALS) < 0) {
									LogUtil.debug(TcpDataService.this, "没有参数分割符！");
									continue;
								}
								String dataKey = tempValue.substring(0, tempValue.indexOf(PARA_EQUALS)).trim();
								String dataValue = tempValue.substring(tempValue.indexOf(PARA_EQUALS) + 1).trim();
								dataMap.put(dataKey, dataValue);
							}
							returnData.addData(dataMap);
						}

						LogUtil.info(TcpDataService.this, "TCP CT BROADCAST");
						// 获取数据后将数据广播出去
						String actionName = requestData.getActionName();
						LogUtil.error(TcpDataService.this, actionName + "页面有数据，发送广播");
						Intent sendIntent = new Intent();
						sendIntent.setAction(actionName);
						Bundle tempBundle = new Bundle();
						tempBundle.putSerializable(SERVER_RESPONSE_DATA_FLAG, returnData);
						sendIntent.putExtras(tempBundle);
						sendBroadcast(sendIntent);
						LogUtil.info(TcpDataService.this, "TCP CT BROADCAST END");
						continue;
					}
					// selector.keys().removeAll(skTemp);
				}
				LogUtil.info(TcpDataService.this, "TCP CT THREAD END");
			} catch (Exception ex) {
				ex.printStackTrace();
				LogUtil.info(TcpDataService.this, "TCP CT THREAD ERROR:" + rdTemp.getActionName());
				long currentTime = System.currentTimeMillis();
				boolean idRetry = (timeout - currentTime) > 0 ? true : false;
				if (countRetry-- > 0 && idRetry) {
					rdTemp.setRetry(countRetry);
					requestList.add(rdTemp);
					LogUtil.info(TcpDataService.this, "TCP CT RESTART 9");
				} else {
					LogUtil.info(TcpDataService.this, "TCP CT RETRY END 9");
					Intent errorIntent = new Intent();
					errorIntent.putExtra("isError", true);
					errorIntent.setAction(rdTemp.getActionName());
					errorIntent.putExtra("flag", rdTemp.getFlag());
					sendBroadcast(errorIntent);
				}
				myRestart();
			}
		}
	}

	/**
	 * 
	 * @ClassName: getServerDataTask
	 * @Description: TODO(只用于上传图片的接受线程)
	 * @author jhjiang@inforace.com.cn 蒋金豪
	 * @date 2011-11-9 下午02:36:41
	 * 
	 */
	class getServerDataTask implements Runnable {
		private Socket socket = null;
		private BufferedInputStream is = null;
		private BufferedOutputStream os = null;
		private int flag;
		private RequestData requestData = null;

		public getServerDataTask(Socket socket, BufferedInputStream is, BufferedOutputStream os, int flag,
				RequestData requestData) {
			this.socket = socket;
			this.is = is;
			this.os = os;
			this.flag = flag;
			this.requestData = requestData;
		}

		@Override
		public void run() {
			int countRetry = requestData.getRetry();
			long timeout = requestData.getTimeout();
			// TODO Auto-generated method stub
			Intent sendErrorIntent = new Intent();
			// sendIntent.setAction(actionName);
			Bundle tempErrorBundle = new Bundle();
			ServerData returnErrorData = new ServerData();
			returnErrorData.setDataType(2);
			tempErrorBundle.putSerializable(SERVER_RESPONSE_DATA_FLAG, returnErrorData);
			sendErrorIntent.putExtras(tempErrorBundle);
			try {
				LogUtil.info(TcpDataService.this, "获得数据线程：tcp数据接受服务开启");
				int temp = 0;
				byte[] buffer = new byte[MOBIL_SOCKET_START_FLAG_DIGIT_LENGTH];

				// 从后台数据库返回的数据
				ServerData returnData = new ServerData();
				returnData.setFlag(flag);
				LogUtil.debug(TcpDataService.this, "获得数据线程：页面请求索引>>" + flag);
				String data = null;

				while ((temp = is.read(buffer)) > 0) {
					// 读取后台传输过来的开始标志
					// 读取到的标志位的数据长度不对，则结束接受数据的进程
					LogUtil.debug(TcpDataService.this, "获得数据线程：读取数据长度>>" + temp);
					if (!(temp == MOBIL_SOCKET_START_FLAG_DIGIT_LENGTH)) {
						LogUtil.debug(TcpDataService.this, "获得数据线程：读取错误出现");
						sendErrorIntent.setAction(requestData.getActionName());
						break;
					}
					String flag = new String(buffer, "UTF8");
					LogUtil.debug(TcpDataService.this, "获得数据线程：读取数据标志>>" + flag);
					// 如果是结束的标志，则结束接受数据的进程
					if (MOBIL_SOCKET_END_FLAG.equals(flag)) {
						LogUtil.debug(TcpDataService.this, "获得数据线程：接受到了结束的标志！！！！");
						os.write("OV".getBytes("UTF8"));
						os.flush();
						break;
					}
					// 如果不是开始的标志，也结束接受数据的进程
					else if (!MOBIL_SOCKET_START_FLAG.equals(flag)) {
						break;
					}

					// 获得后台传输过来的数据长度
					byte[] tempbuffer = new byte[MOBIL_DATA_TYPE_DIGIT_LENGTH];
					is.read(tempbuffer);
					String type = new String(tempbuffer, "UTF8");
					LogUtil.debug(TcpDataService.this, "获得数据线程：读取数据类型>>" + type);

					// 判断数据类型
					if (MOBIL_DATA_TYPE_01.equals(type)) {
						LogUtil.debug(TcpDataService.this, "获得数据线程：数据类型1111");
					} else if (MOBIL_DATA_TYPE_02.equals(type)) {
						LogUtil.debug(TcpDataService.this, "获得数据线程：数据类型2222");
					} else if ("10".equalsIgnoreCase(type)) {
						LogUtil.debug(TcpDataService.this, "获得数据线程：出现错误！！！");

						long currentTime = System.currentTimeMillis();
						boolean idRetry = (timeout - currentTime) > 0 ? true : false;
						if (countRetry-- > 0 && idRetry) {
							requestData.setRetry(countRetry);
							requestList.add(requestData);
						} else {
							Intent errorIntent = new Intent();
							errorIntent.putExtra("isError", true);
							errorIntent.setAction(requestData.getActionName());
							errorIntent.putExtra("flag", requestData.getFlag());
							sendBroadcast(errorIntent);
						}
						return;

					}

					// 获得数据
					tempbuffer = new byte[MOBIL_SOCKET_LENGTH_DIGIT_LENGTH];
					is.read(tempbuffer);
					String tempLength = new String(tempbuffer, "UTF8");

					int length = ByteUtil.bytesToInteger(tempbuffer);

					if (length > 10000000 || length < 0) {
						LogUtil.debug(TcpDataService.this, "获得数据线程：数据总长度超长");
						throw new Exception();
					}
					LogUtil.debug(TcpDataService.this, "获得数据线程：数据总长度：" + length);
					tempbuffer = new byte[length];
					is.read(tempbuffer);

					data = new String(tempbuffer, "UTF8");
					LogUtil.debug(TcpDataService.this, "获得数据线程：从后台服务器获得数据的长度：" + tempbuffer.length);
					LogUtil.debug(TcpDataService.this, "获得数据线程：从后台服务器获得数据：" + data);

					// 对数据进行解密
					data = Encapsulation.antiEncapsulation(tempbuffer);
					LogUtil.debug(TcpDataService.this, "获得数据线程：解密后的数据：" + data);

					// 分析数据，并将数据封装
					LogUtil.debug(TcpDataService.this, "获得数据线程：开始对数据进行封装");
					int mapsIndex = 0;

					String[] allData = data.split(MAP_SEPERATOR);

					LogUtil.debug(TcpDataService.this, "全部数据的循环个数是：" + allData.length);
					for (int i = 1; i <= allData.length; i++) {
						String tempMap = allData[i - 1];
						LogUtil.debug(TcpDataService.this, "循环个数：" + i + ">>" + tempMap);
						if (tempMap == null || "".equalsIgnoreCase(tempMap.trim())) {
							continue;
						}
						LogUtil.debug(TcpDataService.this, "获得数据线程：参数集合" + mapsIndex + "-->" + tempMap);
						HashMap<String, String> dataMap = new HashMap<String, String>();
						StringTokenizer stMap = new StringTokenizer(tempMap, PARA_SEPERATOR);
						while (stMap.hasMoreTokens()) {
							String tempValue = stMap.nextToken();
							LogUtil.debug(TcpDataService.this, "参数：" + tempValue);

							if (tempValue == null || "".equalsIgnoreCase(tempValue.trim())) {
								continue;
							}
							if (tempValue.indexOf(PARA_EQUALS) < 0) {
								LogUtil.debug(TcpDataService.this, "没有参数分割符！");
								continue;
							}
							String dataKey = tempValue.substring(0, tempValue.indexOf(PARA_EQUALS)).trim();
							String dataValue = tempValue.substring(tempValue.indexOf(PARA_EQUALS) + 1).trim();
							LogUtil.debug(TcpDataService.this, "获得数据线程：参数（" + dataKey + "）的值是：<" + dataValue + ">");
							dataMap.put(dataKey, dataValue);
						}
						returnData.addData(dataMap);

					}

					// 获取数据后将数据广播出去
					String actionName = requestData.getActionName();
					Intent sendIntent = new Intent();
					sendIntent.setAction(actionName);
					Bundle tempBundle = new Bundle();
					tempBundle.putSerializable(SERVER_RESPONSE_DATA_FLAG, returnData);
					sendIntent.putExtras(tempBundle);
					sendBroadcast(sendIntent);
				}
				LogUtil.info(TcpDataService.this, "获得数据线程：tcp数据接受服务结束");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.debug(TcpDataService.this, "获得数据线程：异常出现！！");
				LogUtil.debug(TcpDataService.this, "获得数据线程：广播名称是：" + requestData.getActionName());

				long currentTime = System.currentTimeMillis();
				boolean idRetry = (timeout - currentTime) > 0 ? true : false;
				if (countRetry-- > 0 && idRetry) {
					requestData.setRetry(countRetry);
					requestList.add(requestData);
				} else {
					Intent errorIntent = new Intent();
					errorIntent.putExtra("isError", true);
					errorIntent.setAction(requestData.getActionName());
					errorIntent.putExtra("flag", requestData.getFlag());
					sendBroadcast(errorIntent);
				}

			} finally {
				try {
					if (is != null) {
						socket.shutdownInput();
						is.close();
					}
					if (os != null) {
						os.close();
						// socket.shutdownOutput();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
