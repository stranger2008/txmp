<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>天下名品-快乐8</title>
		<Meta http-equiv="Expires" Content="Wed, 26 Feb 1997 09:21:57 GMT">
		<meta http-equiv="Last-Modified" content="Wed, 26 Feb 1997 09:21:57 GMT">
		<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate,max-age=0,post-check=0, pre-check=0,false">
		<meta http-equiv="Pragma" CONTENT="no-cache">
		<meta name="viewport"	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/mobile.css">
		<script src="<%=basePath %>inc/outapi/lottery/js/libs.js"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/klb.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/md5.js"></script>
		<script type="text/javascript">
				window.sessionStorage.setItem('klbperiod','${crep.currperiod}')
				window.sessionStorage.setItem('currendtimeklb','${crep.currendtime}')
		</script>
	</head>
	<body>
			<div class="wrap">
			  <div class="head"><h2 id="navtit">快乐8-<cite id="game">自选</cite>
			  		<span class="head-arr"><em></em></span></h2>
			  			<a href="<%=basePath %>/lottery/index.action" class="btn-qgkj" id="sourceUrl">
			  		<span>
			  			<em></em>
			  		</span>购彩大厅</a><a href="javascript:;" class="btn-menu">≡</a>
			  </div>
			  <div class="w320">
			    <div class="area1">
			      <div class="xq-tit">
			        <h2>第<cite id="actQH">${crep.currperiod}</cite>期</h2>
			        <em class="red" id="countdowm">截止时间：07-21(今日)19:57</em>
			      </div>
			    </div>
			    <div class="line-3"></div>
			    <div class="pick">
			      <div class="pick-tit">
			      	<span class="deal">
			      		<a href="javascript:;" class="btn2 del" dir="red">清 空</a>
			      	</span> 每一注最多选择<strong class="red">10</strong>个号码!
			      </div>
			      <div class="pick-box pick-red">
			        <ul id="r">
			          <li><span>01</span></li>
			          <li><span>02</span></li>
			          <li><span>03</span></li>
			          <li><span>04</span></li>
			          <li><span>05</span></li>
			          <li><span>06</span></li>
			          <li><span>07</span></li>
			          <li><span>08</span></li>
			          <li><span>09</span></li>
			          <li><span>10</span></li>
			          <li><span>11</span></li>
			          <li><span>12</span></li>
			          <li><span>13</span></li>
			          <li><span>14</span></li>
			          <li><span>15</span></li>
			          <li><span>16</span></li>
			          <li><span>17</span></li>
			          <li><span>18</span></li>
			          <li><span>19</span></li>
			          <li><span>20</span></li>
			          <li><span>21</span></li>
			          <li><span>22</span></li>
			          <li><span>23</span></li>
			          <li><span>24</span></li>
			          <li><span>25</span></li>
			          <li><span>26</span></li>
			          <li><span>27</span></li>
			          <li><span>28</span></li>
			          <li><span>29</span></li>
			          <li><span>30</span></li>
			          <li><span>31</span></li>
			          <li><span>32</span></li>
			          <li><span>33</span></li>
			          <li><span>34</span></li>
			          <li><span>35</span></li>
			          <li><span>36</span></li>
			          <li><span>37</span></li>
			          <li><span>38</span></li>
			          <li><span>39</span></li>
			          <li><span>40</span></li>
			          <li><span>41</span></li>
			          <li><span>42</span></li>
			          <li><span>43</span></li>
			          <li><span>44</span></li>
			          <li><span>45</span></li>
			          <li><span>46</span></li>
			          <li><span>47</span></li>
			          <li><span>48</span></li>
			          <li><span>49</span></li>
			          <li><span>50</span></li>
			          <li><span>51</span></li>
			          <li><span>52</span></li>
			          <li><span>53</span></li>
			          <li><span>54</span></li>
			          <li><span>55</span></li>
			          <li><span>56</span></li>
			          <li><span>57</span></li>
			          <li><span>58</span></li>
			          <li><span>59</span></li>
			          <li><span>60</span></li>
			          <li><span>61</span></li>
			          <li><span>62</span></li>
			          <li><span>63</span></li>
			          <li><span>64</span></li>
			          <li><span>65</span></li>
			          <li><span>66</span></li>
			          <li><span>67</span></li>
			          <li><span>68</span></li>
			          <li><span>69</span></li>
			          <li><span>70</span></li>
			          <li><span>71</span></li>
			          <li><span>72</span></li>
			          <li><span>73</span></li>
			          <li><span>74</span></li>
			          <li><span>75</span></li>
			          <li><span>76</span></li>
			          <li><span>77</span></li>
			          <li><span>78</span></li>
			          <li><span>79</span></li>
			          <li><span>80</span></li>
			        </ul>
			      </div>
			    </div>
			    <div class="pick pick-box2 none">
			       <div class="pick-tit2"><span><a href="javascript:;" class="btn2" id="reflash">换一组</a></span> 我要机选
				        <select class="sel-pick">
				        	<option value="1">1</option>
				        	<option value="2">2</option>
				        	<option value="3">3</option>
				        	<option value="5" selected="selected">5</option>
				        	<option value="10">10</option>
				        </select>
			        注 </div>
			      <ul id="myrnd"></ul>
			    </div>
			  </div>
			  <div class="pick-b">
			  	<div class="bet-top" id="pdeal">
			    	<p>您选择了<strong class="red">0</strong>个号码 共<strong class="red">0</strong>注,<strong class="red">0</strong>元</p>
			        <p class="none">您选择了<strong class="red">0</strong>注号码,<strong class="red">0</strong>元<em class="arr-4"><i></i></em></p>
			        <p class="none">您选择了<strong class="red">0</strong>个胆码,<strong class="red">0</strong>个拖码 共<strong class="red">0</strong>注,<strong class="red">0</strong>元<em class="arr-4"><i></i></em></p></div>
			      <div class="betting"><button class="btn-addnmu" type="button" id="addcart"><span></span>添加到号码篮<em id="group">0</em></button><button class="btn-betting" type="button" id="bets">直接投注</button></div></div>
			  <div class="pop-box2 none">
			    <div class="pop-box2-arr"></div>
			    <div class="filt-popc">
		    		<ul>
		    			<li><a href="javascript:;" class="btn-pop btn-pop-on plays" action="0" txt="自选">自选号码</a></li>
		    		</ul>
			    </div>
			  </div>
			</div>
		<script src="<%=basePath %>inc/outapi/lottery/js/saved_resource"></script>
		<script>
			monitor.setProject('360_cp_m').getTrack().getClickAndKeydown();
		</script>
  </body>
</html>