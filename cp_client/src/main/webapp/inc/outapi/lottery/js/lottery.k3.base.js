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
var Lottery = {};
/**
 * 快三对象
 */
Lottery.K3 = function(k3) {
	this.infos = k3.infos == null ? [] :k3.infos;
	this.appendType = k3.appendType == null ? 1 : k3.appendType;
	this.append = k3.append == null ? 1 : k3.append;
	this.muitl = k3.muitl == null ? 1 : k3.muitl;
	this.tab = k3.tab == null ? "desktop" : k3.tab;
};

/**
 * 快三彩票基本信息 快三（1004）<br>
 * 子玩法编码：1、 和值 2、 二同号单选 3、 二同号复选 4、 三同号单选 5、 三同号通选 6、 二不同号 7、 三不同号 8、 三连号通选<br>
 */
Lottery.K3.info = function(info) {

	// 三同号通选、三连号通选只有一种选择，默认balls1为1
	// 三同号单选、两同号复选、和值投注、三不同号单选、两不同号单选
	this.balls1 = info.balls1 == null ? [] : info.balls1;
	// 两同号单选（同号）、三不同胆拖（胆码）、两不同胆拖（胆码）
	this.balls2 = info.balls2 == null ? [] : info.balls2;
	// 两同号单选（不同号）、三不同胆拖（拖码）、两不同胆拖（拖码）
	this.balls3 = info.balls3 == null ? [] : info.balls3;
	// 玩法
	this.type = info.type == null ? null : info.type;
	// 子玩法
	this.subType = info.subType == null ? 0 : info.subType;
	// 注数
	this.count = Lottery.K3.Count.count(this);
	// 价格
	this.price = this.count * 2;
};

/**
 * 玩法常量
 */
Lottery.K3.info.Type = {};
// 和值投注
Lottery.K3.info.Type.HZTH = 1;
// 两同号单选
Lottery.K3.info.Type.LTHDX = 2;
// 两同号复选
Lottery.K3.info.Type.LTHFX = 3;
// 三同号单选
Lottery.K3.info.Type.STHDX = 4;
// 三同号通选
Lottery.K3.info.Type.STHTX = 5;
// 两不同号
Lottery.K3.info.Type.LBTH = 6;
// 三不同号
Lottery.K3.info.Type.SBTH = 7;
// 三连号通选
Lottery.K3.info.Type.SLHTX = 8;

Lottery.K3.info.prototype = {

	clearBalls : function() {
		this.balls1 = [];
		this.balls2 = [];
		this.balls3 = [];
		this.type = null;
		this.subType = null;
		this.count = 0;
		this.price = 0;		
	},

	/**
	 * 机选一注
	 */
	generate : function() {
		var generateUtil = new Lottery.K3.Generate();
		var nums = null;

		if (this.type == 1) {
			// 和值
			this.balls1[0] =generateUtil.generateHztz();
			while(this.balls1[0] == 3 || this.balls1[0] == 18){
				this.balls1[0] = generateUtil.generateHztz();
			}
		} else if (this.type == 2) {
			// 两同号单选
			nums = generateUtil.generate2thdx();
			this.balls2[0] = nums[0];
			this.balls3[0] = nums[1];
		} else if (this.type == 3) {
			// 两同号复选
			this.balls1[0] = generateUtil.generate2thfx();
		} else if (this.type == 4) {
			// 三同号单选
			this.balls1[0] = generateUtil.generate3thdx();
		} else if (this.type == 5) {
			// 三同号通选
			this.balls1[0] = 1;
		} else if (this.type == 6) {
			// 两不同号
			this.balls1 = generateUtil.generate2bthdx();
		} else if (this.type == 7) {
			// 三不同号
			this.balls1 = generateUtil.generate3bthdx();
		} else if (this.type == 8) {
			// 三连号通选
			this.balls1[0] = 1;
		}

		// 注数
		this.count = Lottery.K3.Count.count(this);
		// 价格
		this.price = this.count * 2;

	}
};

/**
 * 生成彩票号码对象
 */
Lottery.K3.Generate = function() {

};
Lottery.K3.Generate.prototype = {

	/**
	 * 随机生成单个数字
	 */
	generate : function(min, max) {
		var Range = max - min + 1;
		var Rand = Math.random();
		return (min + Math.floor(Rand * Range));
	},

	/**
	 * 三同号单选
	 */
	generate3thdx : function() {
		var num = this.generate(1, 6);
		return num * 111;
	},

	/**
	 * 两同号复选
	 */
	generate2thfx : function() {
		var num = this.generate(1, 6);
		return num * 11;
	},

	/**
	 * 和值投注
	 */
	generateHztz : function() {
		return this.generate(3, 18);
	},

	/**
	 * 两同号单选
	 */
	generate2thdx : function() {
		var num1 = this.generate(1, 6);
		var num2 = this.generate(1, 6);
		// 按业务规则，简单避免相同的号码
		if(num1 == num2) {
			if(num2 < 6) {
				num2 += 1;
			} else {
				num2 == 0;
			}
		}
		
		return [num1 * 11, num2];
	},

	/**
	 * 三不同号单选
	 */
	generate3bthdx : function() {
		var nums = [1,2,3,4,5,6];
		for(var i=0; i<nums.length; i++) {
			var tmp = nums[i];
			var j = this.generate(0, 5);
			nums[i] = nums[j];
			nums[j] = tmp;
		}
		return nums.slice(0, 3);
	},
	
	/**
	 * 两不同号单选
	 */
	generate2bthdx : function() {
		var nums = [1,2,3,4,5,6];
		for(var i=0; i<nums.length; i++) {
			var tmp = nums[i];
			var j = this.generate(0, 5);
			nums[i] = nums[j];
			nums[j] = tmp;
		}
		return nums.slice(0, 2);
	}

};

/**
 * 快三统计注数类
 */
Lottery.K3.Count = function() {

};
Lottery.K3.Count = {

	count : function(info) {
		var type = info.type;
		var count = null;

		if (type == 1) {
			// 和值投注
			count = this.countHztz(info);
		} else if (type == 2) {
			// 二同号单选
			count = this.count2thdx(info);
		} else if (type == 3) {
			// 二同号复选
			count = this.count2thfx(info);
		} else if (type == 4) {
			// 三同号单选
			count = this.count3thdx(info);
		} else if (type == 5) {
			// 三同号通选
			count = this.count3thtx(info);
		} else if (type == 6) {
			// 二不同号
			count = this.count2bth(info);
		} else if (type == 7) {
			// 三不同号
			count = this.count3bth(info);
		} else if (type == 8) {
			// 二星组选
			count = this.count3lhtx(info);
		} else {
			count = 0;
		}

		return count;
	},

	/**
	 * 和值投注
	 */
	countHztz : function(info) {
		return info.balls1.length;
	},

	/**
	 * 二同号单选
	 */
	count2thdx : function(info) {
		return info.balls2.length * info.balls3.length;
	},

	/**
	 * 二同号复选
	 */
	count2thfx : function(info) {
		return info.balls1.length;
	},

	/**
	 * 三同号单选
	 */
	count3thdx : function(info) {
		return info.balls1.length;
	},

	/**
	 * 三同号通选
	 */
	count3thtx : function(info) {
		if(info.balls1.length == 1) {
			return 1;
		} else {
			return 0;
		}
	},

	/**
	 * 二不同号
	 */
	count2bth : function(info) {
		if(info != null && info.subType != null) {
			if(info.subType == 0) {
				var counts = [0, 0, 1, 3, 6, 10, 15];
				return counts[info.balls1.length];
			} else if(info.subType == 2) {
				return info.balls3.length;
			} else {
				return 0;
			}
		}
	},

	/**
	 * 三不同号
	 */
	count3bth : function(info) {
		if(info != null && info.subType != null) {
			if(info.subType == 0) {
				var counts = [0, 0, 0, 1, 4, 10, 20];
				return counts[info.balls1.length];
			} else if(info.subType == 2) {
				if(info.balls2.length == 1) {
					var counts = [0, 0, 1, 3, 6, 10];
					return counts[info.balls3.length];
				} else if(info.balls2.length == 2) {
					return info.balls3.length;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
	},

	/**
	 * 三连号通选
	 */
	count3lhtx : function(info) {
		if(info.balls1.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
};