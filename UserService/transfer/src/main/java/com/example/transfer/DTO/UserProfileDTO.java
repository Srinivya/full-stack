package com.example.transfer.DTO;

import com.example.transfer.model.User;

public class UserProfileDTO {
	 private String name;
	    private String address;
	    private String phone;
	 public   UserProfileDTO( User user){
		 this.name=user.getName();
		 this.address=user.getAddress();
		 this.phone=user.getPhone();
	    	
	    }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}

}
