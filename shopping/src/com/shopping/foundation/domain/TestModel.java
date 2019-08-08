package com.shopping.foundation.domain;


public class TestModel implements Comparable<TestModel> {

	
	public String name = "";
	
	public double val = 0.0;
	  public TestModel(String name,double val){
		          this.val = val;
		          this.name = name;
		       }


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}
	public String toString() {
		
		return "'"+this.getName()+"'"+":"+this.getVal();
	}
	public int compareTo(TestModel o) {
		  //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
	         if(this.val >= o.getVal()){
	             return 1;
	        }
		        return -1;
	}
}
