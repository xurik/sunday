/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/21/12
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
(function($){//防止$变量冲突
    $(document).ready(function(){//dom加载完后，再执行函数，防止js报错
        $(".j-button").button();
        $( "#tabs" ).tabs();
        $("#savePage").click(function(){
            var param = $('#webpage_form').serializeJSON();
            $.post('../saveTemplate/'+$("#componentCode").val()+".json",param,function(data) {
                alert(data.success);
            });
        });


        jQuery("#webpage_list").jqGrid({
            datatype: "json",
            colModel:[
                {name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true}},
                {name:'code',index:'code', label:"代码", width:105,editable:true},
                {name:'name',index:'name', label:"名字", width:105,editable:true},
                {name:'theme',index:'theme', label:"风格", width:105,editable:true,
                    edittype:'select',
                    editoptions:{
                        value:{
                            "J_Page":"默认"
                        }
                    }
                },
                {name:'owner',index:'owner', label:"负责人", width:75,editable:true},
                {name:'uri',index:'uri', label:"URI", width:155,editable:true},
                {name:'description',index:'description', label:"备注", width:105,editable:true},
                {name:'uri',index:'uri', label:"操作", width:105,
                    formatter:function(rowId, cellval , colpos, rwdat, act){
                        var url = "/"+$("#webApp").val()+"/"+rowId;
                        var veiw = "<a href='"+url+"' target='_blank'>查看</a>";
                        var edit = "<a href='"+url+"?t_e_s"+"' target='_blank'>编辑</a>";
                        return veiw+"&nbsp;&nbsp;"+edit;
                    }
                }
            ],
            viewrecords: true,
            jsonReader: {
                repeatitems : false,
                id: "0"
            },
            editurl:"save.json",
            url : "list.json",
            pager: '#webpage_pagered'
        }).jqGrid('navGrid','#webpage_pagered',{edit:true,add:true,del:true});

    });
})(jQuery);