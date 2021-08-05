package com.itwillbs.order.db;

import java.util.Date;

public class OrderDTO {
	// 주문 테이블 번호
	private int o_num;
	// 주문번호(주문 상품)
	private String o_trade_num;
	private int o_g_num;
	private String o_g_name; // 주문 상품 이름
	private int o_g_amount; // 주문 상품 수량 
	private String o_g_size;
	private String o_g_color;
	private String o_m_id;
	// 배송정보
	private String o_r_name;
	private String o_r_addr1;
	private String o_r_addr2;
	private String o_r_phone;
	private String o_r_msg;
	// 결제 정보
	private int o_sum_money;
	private String o_trade_type;
	private String o_trade_payer;
	private Date o_trade_date;
	// 운송장 번호(추후에 정보 추가)
	private String o_trans_num;
	private Date o_date;
	// 주문 상태(구매중,배송중,배송완료...)
	private int o_status;
	
	// alt shift s + r
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public String getO_trade_num() {
		return o_trade_num;
	}
	public void setO_trade_num(String o_trade_num) {
		this.o_trade_num = o_trade_num;
	}
	public int getO_g_num() {
		return o_g_num;
	}
	public void setO_g_num(int o_g_num) {
		this.o_g_num = o_g_num;
	}
	public String getO_g_name() {
		return o_g_name;
	}
	public void setO_g_name(String o_g_name) {
		this.o_g_name = o_g_name;
	}
	public int getO_g_amount() {
		return o_g_amount;
	}
	public void setO_g_amount(int o_g_amount) {
		this.o_g_amount = o_g_amount;
	}
	public String getO_g_size() {
		return o_g_size;
	}
	public void setO_g_size(String o_g_size) {
		this.o_g_size = o_g_size;
	}
	public String getO_g_color() {
		return o_g_color;
	}
	public void setO_g_color(String o_g_color) {
		this.o_g_color = o_g_color;
	}
	public String getO_m_id() {
		return o_m_id;
	}
	public void setO_m_id(String o_m_id) {
		this.o_m_id = o_m_id;
	}
	public String getO_r_name() {
		return o_r_name;
	}
	public void setO_r_name(String o_r_name) {
		this.o_r_name = o_r_name;
	}
	public String getO_r_addr1() {
		return o_r_addr1;
	}
	public void setO_r_addr1(String o_r_addr1) {
		this.o_r_addr1 = o_r_addr1;
	}
	public String getO_r_addr2() {
		return o_r_addr2;
	}
	public void setO_r_addr2(String o_r_addr2) {
		this.o_r_addr2 = o_r_addr2;
	}
	public String getO_r_phone() {
		return o_r_phone;
	}
	public void setO_r_phone(String o_r_phone) {
		this.o_r_phone = o_r_phone;
	}
	public String getO_r_msg() {
		return o_r_msg;
	}
	public void setO_r_msg(String o_r_msg) {
		this.o_r_msg = o_r_msg;
	}
	public int getO_sum_money() {
		return o_sum_money;
	}
	public void setO_sum_money(int o_sum_money) {
		this.o_sum_money = o_sum_money;
	}
	public String getO_trade_type() {
		return o_trade_type;
	}
	public void setO_trade_type(String o_trade_type) {
		this.o_trade_type = o_trade_type;
	}
	public String getO_trade_payer() {
		return o_trade_payer;
	}
	public void setO_trade_payer(String o_trade_payer) {
		this.o_trade_payer = o_trade_payer;
	}
	public Date getO_trade_date() {
		return o_trade_date;
	}
	public void setO_trade_date(Date o_trade_date) {
		this.o_trade_date = o_trade_date;
	}
	public String getO_trans_num() {
		return o_trans_num;
	}
	public void setO_trans_num(String o_trans_num) {
		this.o_trans_num = o_trans_num;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public int getO_status() {
		return o_status;
	}
	public void setO_status(int o_status) {
		this.o_status = o_status;
	}
	
	// @data
	// lombok 이런거 쓰면 데이터를 쓸 필요가 없다??
	
	
	// alt shift s + s
	@Override
	public String toString() {
		return "OrderDTO [o_num=" + o_num + ", o_trade_num=" + o_trade_num + ", o_g_num=" + o_g_num + ", o_g_name="
				+ o_g_name + ", o_g_amount=" + o_g_amount + ", o_g_size=" + o_g_size + ", o_g_color=" + o_g_color
				+ ", o_m_id=" + o_m_id + ", o_r_name=" + o_r_name + ", o_r_addr1=" + o_r_addr1 + ", o_r_addr2="
				+ o_r_addr2 + ", o_r_phone=" + o_r_phone + ", o_r_msg=" + o_r_msg + ", o_sum_money=" + o_sum_money
				+ ", o_trade_type=" + o_trade_type + ", o_trade_payer=" + o_trade_payer + ", o_trade_date="
				+ o_trade_date + ", o_trans_num=" + o_trans_num + ", o_date=" + o_date + ", o_status=" + o_status + "]";
	}
	
	
	
	
	
	
	
	
}
