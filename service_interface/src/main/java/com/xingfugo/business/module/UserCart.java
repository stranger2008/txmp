package com.xingfugo.business.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class UserCart {
	private String user_id = null;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	public UserCart(String user_id){
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public List<CartItem> getCartItems() {
		return Collections.unmodifiableList(cartItems);
	}
	
	public CartItem getCartItem(int cartItemHashCode) {
		for(CartItem cartItem : cartItems){
			if(cartItem.hashCode() == cartItemHashCode){
				return cartItem;
			}
		}
		
		return null;
	}
	
	public List<CartItem> getCartItem(List<Integer> cartItemHashCodeList){
		if(CollectionUtils.isEmpty(cartItemHashCodeList)){
			return Collections.emptyList();
		}
		
		int size = cartItemHashCodeList.size();
		List<CartItem> cartItemList = new ArrayList<CartItem>(size);
		for(Integer cartItemHashCode : cartItemHashCodeList){
			CartItem cartItem = getCartItem(cartItemHashCode);
			if(cartItem != null){
				cartItemList.add(cartItem);
			}
		}
		
		return cartItemList;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		if(cartItems == null){
			this.cartItems.clear();
			return;
		}
		
		this.cartItems = cartItems;
	}
	
	/**
	 * 向购物车中添加商品条目
	 * @param addCartItem 欲添加的购物车条目，包含商品ID，商品参数和商品数量
	 * @return 添加后的购物车条目
	 */
	public CartItem add(CartItem addCartItem){
		CartItem cartItem = find(addCartItem);
		if(cartItem == null){
			cartItem = new CartItem();
			
			BeanUtils.copyProperties(addCartItem, cartItem);
			
			cartItem.setAmount(addCartItem.getAmount() < 1 ? 1 : addCartItem.getAmount());
			cartItems.add(cartItem);
		}
		else{
			int curAmount = cartItem.getAmount() + addCartItem.getAmount();
			cartItem.setAmount(curAmount);
		}
		
		return cartItem;
	}
	
	/**
	 * 删除购物车中的商品条目
	 * @param removeCartItem 欲删除的购物车条目，包含商品ID，商品参数和商品数量
	 * @return 删除前的购物车原始条目，为空时表示在删除前购物车中不存在此条目
	 */
	public CartItem remove(CartItem removeCartItem){
		int index = cartItems.indexOf(removeCartItem);
		if(index > -1){
			return cartItems.remove(index);
		}
		
		return null;
	}
	
	public CartItem remove(int cartItemHashCode){
		int cartItemIndex = find(cartItemHashCode);
		if(cartItemIndex >= 0){
			return cartItems.remove(cartItemIndex);
		}
		
		return null;
	}
	
	public List<CartItem> removeAll(List<Integer> cartItemHashCodeList){
		if(CollectionUtils.isEmpty(cartItemHashCodeList)){
			return Collections.emptyList();
		}
		
		int size = cartItemHashCodeList.size();
		//按索引值倒序排列
		Collections.sort(cartItemHashCodeList, new Comparator<Integer>(){
			public int compare(Integer i, Integer j){
				return j - i;
			}
		});
		
		List<CartItem> cartItemList = new ArrayList<CartItem>(size);
		int preCartItemHashCode = -1;
		for(Integer curCartItemHashCode : cartItemHashCodeList){
			//忽略重复的索引值
			if(preCartItemHashCode == curCartItemHashCode){
				continue;
			}
			
			preCartItemHashCode = curCartItemHashCode;
			//顺序删除购物车条目
			CartItem cartItem = remove(curCartItemHashCode);
			if(cartItem != null){
				cartItemList.add(cartItem);
			}
		}
		
		return cartItemList;
	}
	
	/**
	 * 批量删除购物车中的商品条目，商品不在购物车中时忽略
	 * @param removeCartItemList 欲删除的购物车条目列表，每个元素包含商品ID，商品参数和商品数量
	 * @return 删除前的购物车原始条目列表
	 */
	public List<CartItem> remove(List<CartItem> removeCartItemList){
		if(CollectionUtils.isEmpty(removeCartItemList)){
			return Collections.emptyList();
		}
		
		int size = removeCartItemList.size();
		List<CartItem> cartItemList = new ArrayList<CartItem>(size);
		for(CartItem cartItem : removeCartItemList){
			int index = cartItems.indexOf(cartItem);
			if(index > -1){
				cartItemList.add(cartItems.remove(index));
			}
		}
		
		return cartItemList;
	}
	
	public void empty(){
		if(!cartItems.isEmpty()){
			cartItems.clear();
		}
	}
	
	public boolean isEmpty(){
		return cartItems.isEmpty();
	}
	
	public int size(){
		return cartItems.size();
	}
	
	public int goodsNum(){
		int tatalNum = 0;
		for( CartItem item :cartItems){
			tatalNum += item.getAmount();
		}
		return tatalNum;
	}
	/**
	 * 修改购物车中商品条目
	 * @param modifyCartItem 欲修改的购物车条目，包含商品ID，商品参数和商品数量
	 * @return 修改后的购物车条目
	 */
	public CartItem modify(CartItem modifyCartItem){
		CartItem cartItem = find(modifyCartItem);
		int amount = modifyCartItem.getAmount();
		//1、购物车中不存在该商品，增加
		if(cartItem == null){
			cartItem = new CartItem();

			BeanUtils.copyProperties(modifyCartItem, cartItem);
			
			cartItem.setAmount(amount < 1 ? 1 : amount);
			cartItems.add(cartItem);			
			return cartItem;
		}
		
		//2、购物车中存在该商品，修改
		cartItem.setAmount(amount < 1 ? 1 : amount);
		return cartItem;
	}
	
	public CartItem find(CartItem cartItem){
		int index = cartItems.indexOf(cartItem);
		if(index < 0){
			return null;
		}
		
		return cartItems.get(index);
	}
	
	public int find(int cartItemHashCode){
		int size = size();
		for(int i = 0; i < size; i++){
			CartItem cartItem = cartItems.get(i);
			if(cartItem.hashCode() == cartItemHashCode){
				return i;
			}
		}
		
		return -1;
	}
	public boolean exist(CartItem cartItem){
		return (find(cartItem) != null);
	}
	
	public int getIndex(CartItem cartItem){
		return cartItems.indexOf(cartItem);
	}
}
