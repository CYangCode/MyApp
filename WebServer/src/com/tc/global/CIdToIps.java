package com.tc.global;

import java.net.Socket;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class CIdToIps {
	/**
	 *  用于保存客户端接收信息的socket，用于服务器端进行广播
	 */
	public static Multimap<String, Socket> RECV_MMAP = ArrayListMultimap.create();
	
}
