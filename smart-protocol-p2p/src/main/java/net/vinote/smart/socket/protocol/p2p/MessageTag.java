
package net.vinote.smart.socket.protocol.p2p;

/**
 * 定义消息体各字段区分Tag通用标准值 <br/>
 * 若该标准与某些特定消息tag冲突,则优先使用特定tag值
 * 
 * @author Administrator
 * 
 */
public interface MessageTag
{
	/**
	 * 鉴权用户名
	 */
	byte USERNAME = 0x01;

	/**
	 * 鉴权用户名
	 */
	byte PASSWORD = 0x02;

	/**
	 * 加密方式
	 */
	byte ENCRYPT = 0x03;

	/**
	 * 返回码
	 */
	byte RESULT_CODE = (byte) 0xff;

}