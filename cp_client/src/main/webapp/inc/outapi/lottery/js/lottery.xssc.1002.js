var SortUtil = function(){};
SortUtil.prototype = {
	// 利用sort进行排序
	systemSort : function(array) {
		return array.sort(function(a, b) {
			return a - b;
		});
	},

	// 冒泡排序
	bubbleSort : function(array) {
		var i = 0, len = array.length, j, d;
		for (; i < len; i++) {
			for (j = 0; j < len; j++) {
				if (array[i] < array[j]) {
					d = array[j];
					array[j] = array[i];
					array[i] = d;
				}
			}
		}
		return array;
	},

	// 快速排序
	quickSort : function(array) {
		var i = 0;
		var j = array.length - 1;
		var Sort = function(i, j) {
			// 结束条件
			if (i == j) {
				return
			}
			;
			var key = array[i];
			var tempi = i; // 记录开始位置
			var tempj = j; // 记录结束位置

			while (j > i) {
				// j <<-------------- 向前查找
				if (array[j] >= key) {
					j--;
				} else {
					array[i] = array[j];
					// i++ ------------>>向后查找
					while (j > ++i) {
						if (array[i] > key) {
							array[j] = array[i];
							break;
						}
					}
				}
			}
			// 如果第一个取出的 key 是最小的数
			if (tempi == i) {
				Sort(++i, tempj);
				return;
			}
			// 最后一个空位留给 key
			array[i] = key;
			// 递归
			Sort(tempi, i);
			Sort(j, tempj);
		};
		Sort(i, j);
		return array;
	},

	// 插入排序
	insertSort : function(array) {
		// http://baike.baidu.com/image/d57e99942da24e5dd21b7080
		// http://baike.baidu.com/view/396887.htm
		// var array =
		// [0,1,2,44,4,324,5,65,6,6,34,4,5,6,2,43,5,6,62,43,5,1,4,51,56,76,7,7,2,1,45,4,6,7];
		var i = 1, j, temp, key, len = array.length;
		for (; i < len; i++) {
			temp = j = i;
			key = array[j];
			while (--j > -1) {
				if (array[j] > key) {
					array[j + 1] = array[j];
				} else {
					break;
				}
			}
			array[j + 1] = key;
		}
		return array;
	},

	// 希尔排序
	// Jun.array.shellSort(Jun.array.df(10000));
	shellSort : function(array) {
		// http://zh.wikipedia.org/zh/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F
		// var array = [13,14,94,33,82,25,59,94,65,23,45,27,73,25,39,10];
		// var tempArr = [1750, 701, 301, 132, 57, 23, 10, 4, 1];
		// reverse() 在维基上看到这个最优的步长 较小数组
		var tempArr = [ 1031612713, 217378076, 45806244, 9651787, 2034035,
				428481, 90358, 19001, 4025, 836, 182, 34, 9, 1 ];
		// 针对大数组的步长选择
		var i = 0;
		var tempArrtempArrLength = tempArr.length;
		var len = array.length;
		var len2 = parseInt(len / 2);

		for (; i < tempArrLength; i++) {
			if (tempArr[i] > len2) {
				continue;
			}
			tempSort(tempArr[i]);
		}
		// 排序一个步长
		function tempSort(temp) {
			// console.log(temp) 使用的步长统计
			var i = 0, j = 0, f, tem, key;
			var tempLen = len % temp > 0 ? parseInt(len / temp) + 1 : len
					/ temp;

			for (; i < temp; i++) {// 依次循环列

				for (j = 1; /* j < tempLen && */temp * j + i < len; j++) {
					// 依次循环每列的每行
					tem = f = temp * j + i;
					key = array[f];

					while ((tem -= temp) >= 0) {
						// 依次向上查找
						if (array[tem] > key) {
							array[tem + temp] = array[tem];
						} else {
							break;
						}
					}
					array[tem + temp] = key;
				}
			}
		}
		return array;
	}
};





/**
 * 彩票名字空间
 */
var Lottery1002 = {};
/**
 * 新时时彩对象
 */
Lottery1002.Xssc = function() {

};

/**
 * 彩票基本信息
 */
Lottery1002.Xssc.info = function(balls) {

	this.generateUtil = new Lottery1002.Xssc.Generate();

	// 个位
	this.balls1 = balls.balls1 == null ? [] : balls.balls1;
	// 十位
	this.balls2 = balls.balls2 == null ? [] : balls.balls2;
	// 百位
	this.balls3 = balls.balls3 == null ? [] : balls.balls3;
	// 千位
	this.balls4 = balls.balls4 == null ? [] : balls.balls4;
	// 万位
	this.balls5 = balls.balls5 == null ? [] : balls.balls5;
	// 组选
	this.balls0 = balls.balls0 == null ? [] : balls.balls0;
	// 彩票类型
	this.type = balls.type == null ? null : balls.type;
	// 注数
	this.count = Lottery1002.Xssc.Count.count(this);
	// 价格
	this.price = this.count * 2;
	// 倍数
	this.multi = 1;
};
Lottery1002.Xssc.info.prototype = {

	clearBalls : function() {
		this.balls1 = [];
		// 十位
		this.balls2 = [];
		// 百位
		this.balls3 = [];
		// 千位
		this.balls4 = [];
		// 万位
		this.balls5 = [];
		// 组选
		this.balls0 = [];
		// 注数
		this.count = 0;
		// 价格
		this.price = 0;
	},

	/**
	 * 机选一注
	 */
	generateAll : function() {

		var num = 1;
		var nums = null;

		if (this.type == 1) {
			// 一星直选
			nums = this.generateUtil.generate1z(num);
			this.balls1[0] = nums[0][0];
		} else if (this.type == 2) {
			// 二星直选
			nums = this.generateUtil.generate2z(num);
			this.balls2[0] = nums[0][0];
			this.balls1[0] = nums[0][1];
		} else if (this.type == 3) {
			// 三星直选
			nums = this.generateUtil.generate3z(num);
			this.balls3[0] = nums[0][0];
			this.balls2[0] = nums[0][1];
			this.balls1[0] = nums[0][2];
		} else if (this.type == 4) {
			// 四星直选
			nums = this.generateUtil.generate4z(num);
			this.balls4[0] = nums[0][0];
			this.balls3[0] = nums[0][1];
			this.balls2[0] = nums[0][2];
			this.balls1[0] = nums[0][3];
		} else if (this.type == 5) {
			// 五星直选
			nums = this.generateUtil.generate5z(num);
			this.balls5[0] = nums[0][0];
			this.balls4[0] = nums[0][1];
			this.balls3[0] = nums[0][2];
			this.balls2[0] = nums[0][3];
			this.balls1[0] = nums[0][4];
		} else if (this.type == 10) {
			// 二星组选
			nums = this.generateUtil.generate2z2(num);
			this.balls0[0] = nums[0][0];
			this.balls0[1] = nums[0][1];
		} else if (this.type == 11) {
			// 大小单双
			nums = this.generateUtil.generateDxds(num);
			this.balls2[0] = nums[0][0];
			this.balls1[0] = nums[0][1];
		} else if (this.type == 12) {
			// 五星通选
			nums = this.generateUtil.generate5t(num);
			this.balls5[0] = nums[0][0];
			this.balls4[0] = nums[0][1];
			this.balls3[0] = nums[0][2];
			this.balls2[0] = nums[0][3];
			this.balls1[0] = nums[0][4];
		} else if (this.type == 13) {
			// 任选一
			nums = this.generateUtil.generateR1(num);
			if(nums[0][0] != null) {
				this.balls5[0] = nums[0][0];
			}
			if(nums[0][1] != null) {
				this.balls4[0] = nums[0][1];
			}
			if(nums[0][2] != null) {
				this.balls3[0] = nums[0][2];
			}
			if(nums[0][3] != null) {
				this.balls2[0] = nums[0][3];
			}
			if(nums[0][4] != null) {
				this.balls1[0] = nums[0][4];
			}
		} else if (this.type == 14) {
			// 任选二
			nums = this.generateUtil.generateR2(num);
			if(nums[0][0] != null) {
				this.balls5[0] = nums[0][0];
			}
			if(nums[0][1] != null) {
				this.balls4[0] = nums[0][1];
			}
			if(nums[0][2] != null) {
				this.balls3[0] = nums[0][2];
			}
			if(nums[0][3] != null) {
				this.balls2[0] = nums[0][3];
			}
			if(nums[0][4] != null) {
				this.balls1[0] = nums[0][4];
			}
		} else if (this.type == 15) {
			// 三星组三
			nums = this.generateUtil.generate3z3(num);
			this.balls0[0] = nums[0][0];
			if (nums[0][0] == nums[0][1]) {
				this.balls0[1] = nums[0][2];
			} else {
				this.balls0[1] = nums[0][1];
			}
		} else if (this.type == 16) {
			// 三星组六
			nums = this.generateUtil.generate3z6(num);
			this.balls0[0] = nums[0][0];
			this.balls0[1] = nums[0][1];
			this.balls0[2] = nums[0][2];
		}

		// 注数
		this.count = Lottery1002.Xssc.Count.count(this);
		// 价格
		this.price = this.count * 2;

	}
};

/**
 * 生成彩票号码对象
 */
Lottery1002.Xssc.Generate = function() {

};
Lottery1002.Xssc.Generate.prototype = {

	/**
	 * 随机生成单个数字
	 */
	generate : function(min, max) {
		var Range = max - min + 1;
		var Rand = Math.random();
		return (min + Math.floor(Rand * Range));
	},

	/**
	 * 随机生成多个数字
	 */
	generateNum : function(num, min, max) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generate(min, max);
		}

		return nums;
	},

	/**
	 * 生成5星直选号码 num : 生成注数
	 */
	generate5z : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generateNum(5, 0, 9);
		}

		return nums;
	},

	/**
	 * 生成5星通选号码 num : 生成注数
	 */
	generate5t : function(num) {
		return this.generate5z(num);
	},

	/**
	 * 生成4星直选号码 num : 生成注数
	 */
	generate4z : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generateNum(4, 0, 9);
		}

		return nums;
	},

	/**
	 * 生成3星直选号码 num : 生成注数
	 */
	generate3z : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generateNum(3, 0, 9);
		}

		return nums;
	},

	/**
	 * 生成3星组三号码 num : 生成注数
	 */
	generate3z3 : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			var tmpNum = [];
			var flag = true;
			tmpNum[0] = this.generate(0, 9);
			var position = this.generate(1, 2);
			while (flag) {
				tmpNum[position] = this.generate(0, 9);
				if (tmpNum[0] != tmpNum[position]) {
					flag = false;
				}
			}

			if (position == 1) {
				tmpNum[2] = tmpNum[1];
			} else {
				tmpNum[1] = tmpNum[0];
			}

			nums[i] = tmpNum;
		}

		return nums;
	},

	/**
	 * 生成3星组六号码 num : 生成注数
	 */
	generate3z6 : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			var flag = true;
			while (flag) {
				nums[i] = this.generateNum(3, 0, 9);
				if (nums[i][0] != nums[i][1] && nums[i][0] != nums[i][2]
						&& nums[i][1] != nums[i][2]) {
					flag = false;
				}
			}
		}

		return nums;
	},

	/**
	 * 生成2星直选号码 num : 生成注数
	 */
	generate2z : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generateNum(2, 0, 9);
		}

		return nums;
	},

	/**
	 * 生成2星组选号码 num : 生成注数
	 */
	generate2z2 : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			var flag = true;
			while (flag) {
				nums[i] = this.generateNum(2, 0, 9);
				if (nums[i][0] != nums[i][1]) {
					flag = false;
				}
			}
		}

		return nums;
	},

	/**
	 * 生成1星直选号码 num : 生成注数
	 */
	generate1z : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generateNum(1, 0, 9);
		}
		return nums;
	},

	/**
	 * 生成任选一号码 num : 生成注数
	 */
	generateR1 : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			var tmpNum = [];
			var position = this.generate(0, 4);
			for ( var j = 0; j < 5; j++) {
				if (j == position) {
					tmpNum[j] = this.generate(0, 9);
				} else {
					tmpNum[j] = null;
				}
			}
			nums[i] = tmpNum;
		}

		return nums;
	},

	/**
	 * 生成任选二号码 num : 生成注数
	 */
	generateR2 : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			var tmpNum = [];
			var position1 = this.generate(0, 4);
			var position2 = 0;
			var flag = true;
			while (flag) {
				position2 = this.generate(0, 4);
				if (position1 != position2) {
					flag = false;
				}
			}
			for ( var j = 0; j < 5; j++) {
				if (j == position1 || j == position2) {
					tmpNum[j] = this.generate(0, 9);
				} else {
					tmpNum[j] = null;
				}
			}
			nums[i] = tmpNum;
		}

		return nums;
	},

	/**
	 * 生成大小单双号码 num : 生成注数 生成的大小单双由数字代替，0：大、1：小、2：单、3：双 如0、3则为大双
	 */
	generateDxds : function(num) {
		var nums = [];
		for ( var i = 0; i < num; i++) {
			nums[i] = this.generateNum(2, 0, 3);
		}

		return nums;
	}

};

/**
 * 新时时彩复式投注统计注数类
 */
Lottery1002.Xssc.Count = function() {

};
Lottery1002.Xssc.Count = {

	count : function(info) {
		var type = info.type;
		var count = null;

		if (type == 1) {
			// 一星直选
			count = this.count1z(info);
		} else if (type == 2) {
			// 二星直选
			count = this.count2z(info);
		} else if (type == 3) {
			// 三星直选
			count = this.count3z(info);
		} else if (type == 4) {
			// 四星直选
			count = this.count4z(info);
		} else if (type == 5) {
			// 五星直选
			count = this.count5z(info);
		} else if (type == 10) {
			// 二星组选
			count = this.count2z2(info);
		} else if (type == 11) {
			// 大小单双
			count = this.countDxds(info);
		} else if (type == 12) {
			// 五星通选
			count = this.count5t(info);
		} else if (type == 13) {
			// 任选一
			count = this.countR1(info);
		} else if (type == 14) {
			// 任选二
			count = this.countR2(info);
		} else if (type == 15) {
			// 三星组三
			count = this.count3z3(info);
		} else if (type == 16) {
			// 三星组六
			count = this.count3z6(info);
		} else {
			count = 0;
		}

		return count;
	},

	/**
	 * 五星直选
	 */
	count5z : function(info) {
		if (info.balls1.length == null || info.balls1.length <= 0) {
			return 0;
		}
		if (info.balls2.length == null || info.balls2.length <= 0) {
			return 0;
		}
		if (info.balls3.length == null || info.balls3.length <= 0) {
			return 0;
		}
		if (info.balls4.length == null || info.balls4.length <= 0) {
			return 0;
		}
		if (info.balls5.length == null || info.balls5.length <= 0) {
			return 0;
		}

		return info.balls1.length * info.balls2.length * info.balls3.length
				* info.balls4.length * info.balls5.length;
	},

	/**
	 * 五星通选
	 */
	count5t : function(info) {
		return info.balls1.length * info.balls2.length * info.balls3.length
				* info.balls4.length * info.balls5.length;
	},

	/**
	 * 四星直选
	 */
	count4z : function(info) {
		return info.balls1.length * info.balls2.length * info.balls3.length
				* info.balls4.length;
	},

	/**
	 * 三星直选
	 */
	count3z : function(info) {
		return info.balls1.length * info.balls2.length * info.balls3.length;
	},

	/**
	 * 三星组三
	 */
	count3z3 : function(info) {
		// n*(n-1)
		var n_hz = [ 0, 0, 2, 6, 12, 20, 30, 42, 56, 72, 90 ];
		return n_hz[info.balls0.length];
	},

	/**
	 * 三星组六
	 */
	count3z6 : function(info) {
		// n*(n-1)*(n-2)/6
		var n_hz = [ 0, 0, 0, 1, 4, 10, 20, 35, 56, 84, 120 ];
		return n_hz[info.balls0.length];
	},

	/**
	 * 二星直选
	 */
	count2z : function(info) {
		return info.balls1.length * info.balls2.length;
	},

	/**
	 * 二星组选
	 */
	count2z2 : function(info) {
		// n*(n-1)
		var n_hz = [ 0, 0, 1, 3, 6, 10, 15, 21, 0, 0, 0 ];
		return n_hz[info.balls0.length];
	},

	/**
	 * 一星直选
	 */
	count1z : function(info) {
		// 选几个号就几注
		return info.balls1.length;
	},

	/**
	 * 任选一
	 */
	countR1 : function(info) {
		// 所有选择的球数
		return info.balls1.length + info.balls2.length + info.balls3.length
				+ info.balls4.length + info.balls5.length;
	},

	/**
	 * 任选二
	 */
	countR2 : function(info) {
		// 所有不同位数的两位数组合
		return info.balls1.length
				* (info.balls2.length + info.balls3.length + info.balls4.length + info.balls5.length)
				+ info.balls2.length
				* (info.balls3.length + info.balls4.length + info.balls5.length)
				+ info.balls3.length
				* (info.balls4.length + info.balls5.length)
				+ info.balls4.length * info.balls5.length;
	},

	/**
	 * 大小单双
	 */
	countDxds : function(info) {
		return info.balls1.length * info.balls2.length;
	}

};



/**
 * 获取选择的球
 * 
 */
function getBalls(info, ball) {

	var type = info.type;
	var ballName = $(ball).attr("name").replace("_" + type, "");
	var infoBall = info[ballName];
	var txtValue = null;
	
	if(type == 11) {
		// 大小单双特别处理
		txtValue = changeDxdsToNum($(ball).text());
	} else {
		txtValue = Number($(ball).text());
	}
	if($(ball).attr("class") == "on") {
		if($.inArray(txtValue, infoBall) < 0) {
			// 如果不存在当前数字则添加
			infoBall[infoBall.length] = txtValue;
		}
	} else {
		if($.inArray(txtValue, infoBall) >= 0) {
			// 如果存在当前数字则删除
			infoBall.splice($.inArray(txtValue, infoBall), 1);
		}
	}
	
	info.count = Lottery1002.Xssc.Count.count(info);
	info.price = info.count * 2;
	
}

/**
 * 把大小单双转换成数字代码
 * 
 * @param num
 */
function changeDxdsToNum(num){
	if(num == "大") {
		return 0;
	} else if(num == "小") {
		return 1;
	} else if(num == "单") {
		return 2;
	} else if(num == "双") {
		return 3;
	} else {
		return null;
	}
}

/**
 * 把数字代码转换成大小单双
 * 
 * @param num
 */
function changeNumToDxds(num){
	if(num == 0) {
		return "大";
	} else if(num == 1) {
		return "小";
	} else if(num == 2) {
		return "单";
	} else if(num == 3) {
		return "双";
	} else {
		return null;
	}
}


var sortUtil = new SortUtil();


