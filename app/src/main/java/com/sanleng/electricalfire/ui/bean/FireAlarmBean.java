package com.sanleng.electricalfire.ui.bean;

import java.util.List;

/**
 * 
 * @author qiaoshi
 *
 */
public class FireAlarmBean{
	/**
	 * msg : 获取成功
	 * code : 0
	 * data : {"total":1,"nextPage":-1,"list":[{"unit_name":"三棱科技","device_name":"火灾2号","alarm_type":"1","receive_time":"2019-01-09 17:12:08","ids":"aae5a4b27f234727a5c0cfb8e287e5f4","owned_building_name":null,"floor_number":null,"position":"","state":"待处理火警"}]}
	 */

	private String msg;
	private String code;
	private DataBean data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * total : 1
		 * nextPage : -1
		 * list : [{"unit_name":"三棱科技","device_name":"火灾2号","alarm_type":"1","receive_time":"2019-01-09 17:12:08","ids":"aae5a4b27f234727a5c0cfb8e287e5f4","owned_building_name":null,"floor_number":null,"position":"","state":"待处理火警"}]
		 */

		private int total;
		private int nextPage;
		private List<ListBean> list;

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getNextPage() {
			return nextPage;
		}

		public void setNextPage(int nextPage) {
			this.nextPage = nextPage;
		}

		public List<ListBean> getList() {
			return list;
		}

		public void setList(List<ListBean> list) {
			this.list = list;
		}

		public static class ListBean {
			/**
			 * unit_name : 三棱科技
			 * device_name : 火灾2号
			 * alarm_type : 1
			 * receive_time : 2019-01-09 17:12:08
			 * ids : aae5a4b27f234727a5c0cfb8e287e5f4
			 * owned_building_name : null
			 * floor_number : null
			 * position :
			 * state : 待处理火警
			 */

			private String unit_name;
			private String device_name;
			private String alarm_type;
			private String receive_time;
			private String ids;
			private Object owned_building_name;
			private Object floor_number;
			private String position;
			private String state;

			public String getUnit_name() {
				return unit_name;
			}

			public void setUnit_name(String unit_name) {
				this.unit_name = unit_name;
			}

			public String getDevice_name() {
				return device_name;
			}

			public void setDevice_name(String device_name) {
				this.device_name = device_name;
			}

			public String getAlarm_type() {
				return alarm_type;
			}

			public void setAlarm_type(String alarm_type) {
				this.alarm_type = alarm_type;
			}

			public String getReceive_time() {
				return receive_time;
			}

			public void setReceive_time(String receive_time) {
				this.receive_time = receive_time;
			}

			public String getIds() {
				return ids;
			}

			public void setIds(String ids) {
				this.ids = ids;
			}

			public Object getOwned_building_name() {
				return owned_building_name;
			}

			public void setOwned_building_name(Object owned_building_name) {
				this.owned_building_name = owned_building_name;
			}

			public Object getFloor_number() {
				return floor_number;
			}

			public void setFloor_number(Object floor_number) {
				this.floor_number = floor_number;
			}

			public String getPosition() {
				return position;
			}

			public void setPosition(String position) {
				this.position = position;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}
		}
	}
}
