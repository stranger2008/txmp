
function bindTesting(obj){
	for(var i=0; i<obj.length;i++){
		var v = new valid(obj[i]);
		$(obj[i].target).blur(v.myevent);
	}
};
function valid(ele){	
	this.myevent = function(){
	    for(var p=0; p < ele.arr.length; p++){	
	      var value = '"'+$(ele.target).val() + '"';
		  var t = eval(ele.arr[p][0].replace(/#/g, value));//将#号转换为元素并验证
		  if(t){
       		   $(ele.error+"_1").hide();
			   $(ele.error+"_2").show();
       		   $(ele.error+"_2").html(ele.arr[p][1]);//没通过验证
       		   return false;
			}
		}
 	    $(ele.error+"_2").hide();
	    $(ele.error+"_1").show();
	    $(ele.error+"_1").html("<font color='green'>√</font>");//通过验证
        return true;
	}
}


function submitCheck(obj){
	for(var i=0; i<obj.length;i++){
		var v = new valid(obj[i]);
		if(!v.myevent()){
			return false;
		}
	}
	return true;
}

var checkRule={
	ChineseName:/^([\u4E00-\u9FA5]|[a-zA-Z]){2,10}$/,//中文姓名
	Tel:/^\(?0?(10|2[0-57-9]|[3-9]\d{2}|1(3\d|59|58))\)?-?\d{8}(-\d{4})?$/,//手机$固定电话
	Mobile:/^(?:13|18|15)\d{9}$/,//手机
	BankCard : /^\d{16,19}$/, //银行帐号
    Number : /^\d+$/ //数字
};


/*精确校验身份证*/
function onCheckRegIDno(id_card) {
	var obj = $("#"+id_card).get(0);
    if (!isIDno(obj)) {
        return 1;
    }
    else {
        return 0;
    }
}

function isIDno(obj) {
 //aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
    var aCity = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";
    var iSum = 0;
    var info = "";
    var idCardLength = obj.value.length;
    if(!/^\d{17}(\d|x)$/i.test(obj.value)&&!/^\d{15}$/i.test(obj.value)&&!/^\d{8}$/i.test(obj.value)) {
        return false;
    }

    //在后面的运算中x相当于数字10,所以转换成a
    var objvalue = obj.value.replace(/x$/i,"a");

    var curCity = objvalue.substr(0,2);

    if(!(aCity.indexOf(curCity) >= 0) ) {
        return false;
    }

    if (idCardLength==18) {
        sBirthday=objvalue.substr(6,4)+"-"+Number(objvalue.substr(10,2))+"-"+Number(objvalue.substr(12,2));
        var d = new Date(sBirthday.replace(/-/g,"/"));
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())) {
            return false;
        }
        for(var i = 17;i>=0;i --)
            iSum += (Math.pow(2,i) % 11) * parseInt(objvalue.charAt(17 - i),11);

        if(iSum%11!=1) {
            return false;
        }

    }
    else if (idCardLength==15) {
        sBirthday = "19" + objvalue.substr(6,2) + "-" + Number(objvalue.substr(8,2)) + "-" + Number(objvalue.substr(10,2));
        var d = new Date(sBirthday.replace(/-/g,"/"));
        var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();

        if(sBirthday != dd) {
            return false;
        }
    }
    else if (idCardLength==8) {
    }
    return true;
}

/*校验18岁*/
function onCheckIDnoYoung(id_card) {
	var obj = $("#"+id_card).get(0);
    if (!isIDnoYoung(obj)) {
        return 1;
    }
    else {
        return 0;
    }
}

function isIDnoYoung(obj) {
    var idCardLength = obj.value.length;
    //在后面的运算中x相当于数字10,所以转换成a
    var objvalue = obj.value.replace(/x$/i,"a");
    if (idCardLength==18) {
        sBirthday=objvalue.substr(6,4)+"-"+Number(objvalue.substr(10,2))+"-"+Number(objvalue.substr(12,2));
        var d = new Date(sBirthday.replace(/-/g,"/"));
        var year = new Date().getFullYear();
        if (year - d.getFullYear() < 18) {
            return false;
        }
    }
    else if (idCardLength==15) {
        sBirthday = "19" + objvalue.substr(6,2) + "-" + Number(objvalue.substr(8,2)) + "-" + Number(objvalue.substr(10,2));
        var d = new Date(sBirthday.replace(/-/g,"/"));
        var year = new Date().getFullYear();
        if (year - d.getFullYear() < 18) {
            return false;
        }
    }
    else if (idCardLength==8) {
    }
    return true;
}
