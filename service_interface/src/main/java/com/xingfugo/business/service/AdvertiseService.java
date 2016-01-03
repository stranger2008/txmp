package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AdvertiseDao;
import com.xingfugo.business.module.Advertise;
import com.xingfugo.util.ImgPathUitls;

@Service
public class AdvertiseService {
	//APP首页广告位ID列表
	private static String AD_POS_TYPE_APP = "6254777855";
	
	//触屏版天下名酒栏目首页广告位ID列表
	private static String AD_POS_TYPE_TXMJ = "8661746442";
	
	//App版天下名酒栏目首页广告位ID列表
	private static String AD_POS_TYPE_TXMJ_APP = "4356252478";
	
	//触屏版天下名茶栏目首页广告位ID列表
	private static String AD_POS_TYPE_TXMC = "1174468185";
	
	//App版天下名茶栏目首页广告位ID列表
	private static String AD_POS_TYPE_TXMC_APP = "7171282276";
	
	//触屏版天下珠宝栏目首页广告位ID列表
	private static String AD_POS_TYPE_TXZB = "1482821838";
	
	//App版天下珠宝栏目首页广告位ID列表
	private static String AD_POS_TYPE_TXZB_APP = "6317331223";
	
	@Autowired
	private AdvertiseDao advertiseDao;
	
	public AdvertiseService() {}
	
	//根据广告类型找出广告
	public List<Advertise> getAllAdvertise(String posType){
		List<Advertise> adList = advertiseDao.getAllAdvertiseList(posType);
		for(Advertise ad : adList){
			String imgPath = ImgPathUitls.filterImagePath(ad.getImg_path());
			ad.setImg_path(imgPath);
		}
		
		return adList;
	}
	
	//根据广告位ID找出对应的广告list，放入map，key是广告ID，键是广告list
	public Map<Integer,List<Advertise>> getAdvMapByPosid(List<Advertise> advList){
		Map<Integer,List<Advertise>> resultMap = new HashMap<Integer,List<Advertise>>();
		Iterator<Advertise> itr = advList.iterator();
		while(itr.hasNext()){
			Advertise advertise = itr.next();
			Integer _pos_id = advertise.getPos_id();
			listAddMap(_pos_id,resultMap,advertise);
		}
		return resultMap;
	}
	
	//pos_id作为map的key，value是advertise组成的list
	//如果存在的话，取出list，把advertise放进去
	public void listAddMap(Integer pos_id,Map retMap,Advertise advertise){
		List tempList = null;
		if(retMap.get(pos_id) != null){
			tempList = (List)retMap.get(pos_id);
		}else{
			tempList = new ArrayList();
		}
		tempList.add(advertise);
		retMap.put(pos_id, tempList);
	}
	
	//天下珠宝栏目广告App版
	public List<Advertise> getAllTxzbAdvertiseApp(){
		return getAdvertiseByType(AD_POS_TYPE_TXZB_APP);
	}
	
	//天下珠宝栏目广告触屏版
	public List<Advertise> getAllTxzbAdvertise(){
		return getAdvertiseByType(AD_POS_TYPE_TXZB);
	}
	
	//天下名茶栏目广告App版
	public List<Advertise> getAllTxmcAdvertiseApp(){
		return getAdvertiseByType(AD_POS_TYPE_TXMC_APP);
	}
	
	//天下名茶栏目广告触屏版
	public List<Advertise> getAllTxmcAdvertise(){
		return getAdvertiseByType(AD_POS_TYPE_TXMC);
	}
	
	//天下名酒栏目广告App版
	public List<Advertise> getAllTxmjAdvertiseApp(){
		return getAdvertiseByType(AD_POS_TYPE_TXMJ_APP);
	}
	
	//天下名酒栏目广告触屏版
	public List<Advertise> getAllTxmjAdvertise(){
		return getAdvertiseByType(AD_POS_TYPE_TXMJ);
	}
	
	//APP首页广告
	public List<Advertise> getAllAppAdvertise(){
		return getAdvertiseByType(AD_POS_TYPE_APP);
	}	
	
	
	public List<Advertise> getAdvertiseByType(String type){
		List<Advertise> adList = advertiseDao.getAllAdvertiseList(type);
		int index = 1;
		int prePosId = 0;
		int curPosId = 0;
		for(Advertise ad : adList){
			String imgPath = ImgPathUitls.filterImagePath(ad.getImg_path());
			ad.setImg_path(imgPath);
			
			curPosId = ad.getPos_id();
			if(prePosId == 0){
				prePosId = curPosId;
			}
			
			if(curPosId == prePosId){
				ad.setPos_id(index);
				continue;
			}
			
			index++;
			ad.setPos_id(index);
			prePosId = curPosId;
		}
		
		return adList;
	}
}
