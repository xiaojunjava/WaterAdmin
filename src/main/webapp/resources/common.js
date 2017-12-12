/**
 * Created by lkj on 2017/11/14 014.
 */
//from数据转为json格式
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//easyui扩展（文本框最长限制）
$.extend($.fn.validatebox.defaults.rules, {
    maxLength: {
        validator: function(value, param){
            return value.length <= param[0];
        },
        message: '输入字符不能超过{0}个'
    },
    selectValueRequired: {
        validator: function(value,param){
            console.info($(param[0]).find("option:contains('"+value+"')").val());
            return $(param[0]).find("option:contains('"+value+"')").val() != '';
        },
        message: '请选择下列列表中的某一项'
    }
});