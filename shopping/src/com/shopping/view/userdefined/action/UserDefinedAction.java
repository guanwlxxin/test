package com.shopping.view.userdefined.action;

import com.shopping.core.mv.JModelAndView;
import com.shopping.core.security.support.SecurityUserHolder;
import com.shopping.core.tools.CommUtil;
import com.shopping.core.tools.Md5Encrypt;
import com.shopping.foundation.domain.Goods;
import com.shopping.foundation.domain.GoodsCart;
import com.shopping.foundation.domain.Group;
import com.shopping.foundation.domain.Store;
import com.shopping.foundation.domain.StoreCart;
import com.shopping.foundation.domain.SysConfig;
import com.shopping.foundation.domain.User;
import com.shopping.foundation.service.IAccessoryService;
import com.shopping.foundation.service.IArticleClassService;
import com.shopping.foundation.service.IArticleService;
import com.shopping.foundation.service.IBargainGoodsService;
import com.shopping.foundation.service.IDeliveryGoodsService;
import com.shopping.foundation.service.IGoodsBrandService;
import com.shopping.foundation.service.IGoodsCartService;
import com.shopping.foundation.service.IGoodsClassService;
import com.shopping.foundation.service.IGoodsFloorService;
import com.shopping.foundation.service.IGoodsService;
import com.shopping.foundation.service.IGroupGoodsService;
import com.shopping.foundation.service.IGroupService;
import com.shopping.foundation.service.IMessageService;
import com.shopping.foundation.service.INavigationService;
import com.shopping.foundation.service.IPartnerService;
import com.shopping.foundation.service.IRoleService;
import com.shopping.foundation.service.IStoreCartService;
import com.shopping.foundation.service.IStoreService;
import com.shopping.foundation.service.ISysConfigService;
import com.shopping.foundation.service.IUserAppBrowseLogService;
import com.shopping.foundation.service.IUserCommodityPushService;
import com.shopping.foundation.service.IUserConfigService;
import com.shopping.foundation.service.IUserService;
import com.shopping.manage.admin.tools.MsgTools;
import com.shopping.view.web.tools.GoodsFloorViewTools;
import com.shopping.view.web.tools.GoodsViewTools;
import com.shopping.view.web.tools.NavViewTools;
import com.shopping.view.web.tools.StoreViewTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class UserDefinedAction {

	@Autowired
	private ISysConfigService configService;

	@Autowired
	private IUserConfigService userConfigService;

	@Autowired
	private IGoodsClassService goodsClassService;

	@Autowired
	private IGoodsBrandService goodsBrandService;

	@Autowired
	private IPartnerService partnerService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IArticleClassService articleClassService;

	@Autowired
	private IArticleService articleService;

	@Autowired
	private IAccessoryService accessoryService;

	@Autowired
	private IMessageService messageService;

	@Autowired
	private IStoreService storeService;

	@Autowired
	private IGoodsService goodsService;

	@Autowired
	private INavigationService navigationService;

	@Autowired
	private IGroupGoodsService groupGoodsService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IGoodsFloorService goodsFloorService;

	@Autowired
	private IBargainGoodsService bargainGoodsService;

	@Autowired
	private IDeliveryGoodsService deliveryGoodsService;

	@Autowired
	private IStoreCartService storeCartService;

	@Autowired
	private IGoodsCartService goodsCartService;

	@Autowired
	private NavViewTools navTools;

	@Autowired
	private GoodsViewTools goodsViewTools;

	@Autowired
	private StoreViewTools storeViewTools;

	@Autowired
	private MsgTools msgTools;

	@Autowired
	private GoodsFloorViewTools gf_tools;
	
	@Autowired
	private IUserCommodityPushService userCommodityPushService;
	
	
	@RequestMapping( { "/good_push.vok" } )
	public  void good_push( HttpServletRequest request, HttpServletResponse response,String type ) {
		Map<String,Object> map = new HashMap<String, Object>();
		Map params = new HashMap();
		params.put( "store_recommend", Boolean.valueOf( true ) );
		params.put( "goods_status", Integer.valueOf( 0 ) );
		List store_reommend_goods_list = this.goodsService.query( "select obj from Goods obj where obj.store_recommend=:store_recommend and obj.goods_status=:goods_status order by obj.store_recommend_time desc", params, -1, -1 );

		map.put("1", "111");
		map.put("2", "222");
		map.put("3", "333");
		map.put("Goods", store_reommend_goods_list);
		try {
			response.getWriter().write("aaa");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		if(type.equals("html")) {
//			ModelAndView mv = new JModelAndView( "switch_recommend_goods.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 1, request, response );
//			Map params = new HashMap();
//			params.put( "store_recommend", Boolean.valueOf( true ) );
//			params.put( "goods_status", Integer.valueOf( 0 ) );
//			List store_reommend_goods_list = this.goodsService.query( "select obj from Goods obj where obj.store_recommend=:store_recommend and obj.goods_status=:goods_status order by obj.store_recommend_time desc", params, -1, -1 );
//			List store_reommend_goods = new ArrayList();
//			int begin = 3 * 5;
//			if( begin > store_reommend_goods_list.size() - 1 ) {
//				begin = 0;
//			}
//			int max = begin + 5;
//			if( max > store_reommend_goods_list.size() ) {
//				begin -= max - store_reommend_goods_list.size();
//				max--;
//			}
//			for( int i = 0; i < store_reommend_goods_list.size(); i++ ) {
//				if( (i >= begin) && (i < max) ) {
//					store_reommend_goods.add( (Goods)store_reommend_goods_list.get( i ) );
//				}
//			}
//			mv.addObject( "store_reommend_goods", store_reommend_goods );
//			return mv;
//		}else {
////			ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());  
////			
//////            mav.addObject("result", "failed");  
//////            return mav; 
//			if(SecurityUserHolder.getCurrentUser()!=null) {
//				Long userId =  SecurityUserHolder.getCurrentUser().getId();
//				System.out.println(userId);
//			}else {
//				System.out.println("未登录啊");
//			}
//			return new ModelAndView().addObject("cc","aa");
//		}
		
		
	
}


//	@RequestMapping( { "/index.htm" } )
//	public ModelAndView index( HttpServletRequest request, HttpServletResponse response ) {
//		ModelAndView mv = new JModelAndView( "index.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 1, request, response );
//	
//		List uAp =this.userAppBrowseLogService.query("select obj from UserAppBrowseLog obj", null, -1, -1);
//		Map params = new HashMap();
//		params.put( "display", Boolean.valueOf( true ) );
//		List gcs = this.goodsClassService.query( "select obj from GoodsClass obj where obj.parent.id is null and obj.display=:display order by obj.sequence asc", params, 0, 15 );
//		mv.addObject( "gcs", gcs );
//		params.clear();
//		params.put( "audit", Integer.valueOf( 1 ) );
//		params.put( "recommend", Boolean.valueOf( true ) );
//		List gbs = this.goodsBrandService.query( "select obj from GoodsBrand obj where obj.audit=:audit and obj.recommend=:recommend order by obj.sequence", params, -1, -1 );
//		mv.addObject( "gbs", gbs );
//		params.clear();
//		List img_partners = this.partnerService.query( "select obj from Partner obj where obj.image.id is not null order by obj.sequence asc", params, -1, -1 );
//		mv.addObject( "img_partners", img_partners );
//		List text_partners = this.partnerService.query( "select obj from Partner obj where obj.image.id is null order by obj.sequence asc", params, -1, -1 );
//		mv.addObject( "text_partners", text_partners );
//		params.clear();
//		params.put( "mark", "news" );
//		List acs = this.articleClassService.query( "select obj from ArticleClass obj where obj.parent.id is null and obj.mark!=:mark order by obj.sequence asc", params, 0, 9 );
//		mv.addObject( "acs", acs );
//		params.clear();
//		params.put( "class_mark", "news" );
//		params.put( "display", Boolean.valueOf( true ) );
//		List articles = this.articleService.query( "select obj from Article obj where obj.articleClass.mark=:class_mark and obj.display=:display order by obj.addTime desc", params, 0, 5 );
//		mv.addObject( "articles", articles );
//		params.clear();
//		params.put( "store_recommend", Boolean.valueOf( true ) );
//		params.put( "goods_status", Integer.valueOf( 0 ) );
//		List store_reommend_goods_list = this.goodsService.query( "select obj from Goods obj where obj.store_recommend=:store_recommend and obj.goods_status=:goods_status order by obj.store_recommend_time desc", params, -1, -1 );
//		List store_reommend_goods = new ArrayList();
//		int max = store_reommend_goods_list.size() >= 5 ? 4 : store_reommend_goods_list.size() - 1;
//		for( int i = 0; i <= max; i++ ) {
//			store_reommend_goods.add( (Goods)store_reommend_goods_list.get( i ) );
//		}
//		mv.addObject( "store_reommend_goods", store_reommend_goods );
//		mv.addObject( "store_reommend_goods_count", Double.valueOf( Math.ceil( CommUtil.div( Integer.valueOf( store_reommend_goods_list.size() ), Integer.valueOf( 5 ) ) ) ) );
//		mv.addObject( "goodsViewTools", this.goodsViewTools );
//		mv.addObject( "storeViewTools", this.storeViewTools );
//		if( SecurityUserHolder.getCurrentUser() != null ) {
//			mv.addObject( "user", this.userService.getObjById( SecurityUserHolder.getCurrentUser().getId() ) );
//		}
//		params.clear();
//		params.put( "beginTime", new Date() );
//		params.put( "endTime", new Date() );
//		List groups = this.groupService.query( "select obj from Group obj where obj.beginTime<=:beginTime and obj.endTime>=:endTime", params, -1, -1 );
//		if( groups.size() > 0 ) {
//			params.clear();
//			params.put( "gg_status", Integer.valueOf( 1 ) );
//			params.put( "gg_recommend", Integer.valueOf( 1 ) );
//			params.put( "group_id", ((Group)groups.get( 0 )).getId() );
//			List ggs = this.groupGoodsService.query( "select obj from GroupGoods obj where obj.gg_status=:gg_status and obj.gg_recommend=:gg_recommend and obj.group.id=:group_id order by obj.gg_recommend_time desc", params, 0, 1 );
//			if( ggs.size() > 0 )
//				mv.addObject( "group", ggs.get( 0 ) );
//		}
//		params.clear();
//		params.put( "bg_time", CommUtil.formatDate( CommUtil.formatShortDate( new Date() ) ) );
//		params.put( "bg_status", Integer.valueOf( 1 ) );
//		List bgs = this.bargainGoodsService.query( "select obj from BargainGoods obj where obj.bg_time=:bg_time and obj.bg_status=:bg_status", params, 0, 5 );
//		mv.addObject( "bgs", bgs );
//		params.clear();
//		params.put( "d_status", Integer.valueOf( 1 ) );
//		params.put( "d_begin_time", new Date() );
//		params.put( "d_end_time", new Date() );
//		List dgs = this.deliveryGoodsService.query( "select obj from DeliveryGoods obj where obj.d_status=:d_status and obj.d_begin_time<=:d_begin_time and obj.d_end_time>=:d_end_time order by obj.d_audit_time desc", params, 0, 5 );
//		mv.addObject( "dgs", dgs );
//		return mv;
//	}

	
}
