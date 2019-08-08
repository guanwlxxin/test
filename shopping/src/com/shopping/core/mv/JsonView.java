package com.shopping.core.mv;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.alibaba.fastjson.JSONObject;

public class JsonView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map arg0, HttpServletRequest arg1, HttpServletResponse arg2)
			throws Exception {
		arg2.setContentType("text/json; charset=UTF-8");  
		PrintWriter out = arg2.getWriter();  
		JSONObject json = JSONObject.parseObject("{'32772':{'user':{'1':0.9999999999999998,'32771':0.7071067811865475,'32774':0.6123724356957945,'32768':0.5773502691896258,'32775':0.5477225575051661,'32778':0.5000000000000001},'good':'98410,98412'},'1':{'user':{'32772':0.3333333333333333,'32775':0.3333333333333333,'32778':0.30429030972509225,'32771':0.28867513459481287,'32768':0.2721655269759087,'32774':0.26352313834736496},'good':'98460,98344,98412,98331,98410,98314,98392,98406,3,98305,98304,98448,98458,32769,98459,98455,98456,98457'},'32771':{'user':{'1':0.7071067811865475,'32772':0.5,'32768':0.4082482904638631,'32774':0.35355339059327373,'32775':0.31622776601683794,'32778':0.2886751345948129},'good':'98333,98331'},'32774':{'user':{'1':1.0,'32772':0.9999999999999998,'32771':0.8164965809277259,'32768':0.7071067811865475,'32775':0.6324555320336759,'32778':0.5773502691896258},'good':'98412'},'32768':{'user':{'1':0.7071067811865475,'32772':0.5,'32771':0.4082482904638631,'32774':0.35355339059327373,'32775':0.31622776601683794,'32778':0.2886751345948129},'good':'3,1'},'32775':{'user':{'1':0.9999999999999998,'32772':0.7071067811865475,'32771':0.5773502691896257,'32768':0.4999999999999999,'32774':0.4472135954999579,'32778':0.40824829046386296},'good':'98304,98305,98458,98459,98456'},'32778':{'user':{'32775':NaN,'32774':NaN,'32768':NaN,'32771':NaN,'32772':NaN,'1':NaN},'good':'98358'}}");
		out.print(json);  
		
	}

	

}